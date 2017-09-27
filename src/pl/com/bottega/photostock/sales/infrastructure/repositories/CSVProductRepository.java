package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CSVProductRepository implements ProductRepository {

    private String path;
    ClientRepository clientRepository;

    public CSVProductRepository(String path, ClientRepository clientRepository) {
        this.path = path;
        this.clientRepository = clientRepository;
    }

    @Override
    public Product get(Long number) {
        return getOptional(number).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                throw new IllegalArgumentException("No such product in repo");
            }
        });
    }

    private Client findClient(String number) {
        if (number.equals("null"))
            return null;
        else
            return clientRepository.get(number);
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            while (br.readLine() != null && br.readLine().startsWith(number.toString())) { //TODO sprzawdź poprawność
                return Optional.of(readProductFromLine(br));
            }
            return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e); //przepakowywanie wyjątku
        }
    }

    private Product readProductFromLine(BufferedReader br) throws IOException {
        String line = br.readLine();
            String[] lineSplit = line.split(",");
                Long nr = Long.parseLong(lineSplit[0]);
                String[] tags = lineSplit[1].split(";");
                Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
                Boolean active = Boolean.parseBoolean(lineSplit[3]);
                String reservedByNumber = lineSplit[4];
                String ownerNumber = lineSplit[5];
                return new Picture(
                        nr,
                        tags,
                        price,
                        active,
                        findClient(reservedByNumber),
                        findClient(ownerNumber));
    }

    @Override
    public void save(Product product) throws IOException {
//1. Wszytaj wszystkie produkty z csv do Map<Long, Product> używając metody z pkt 1. Kluczem w mapie jest nr produktu,wartością produkt.
//2. Podmień w mapie produkt o numerze takim jak produkt przychodzący w parametrze do metody save, na produkt przychodzący do metody save.
//3. Otwórz plik do zapisu (append = false) i zapisz wszystkie produkty z mapy do pliku, zachowując format jaki stosujemy przy odczycie.
//   Przyda Ci się metoda prywatne String[] toLine(Product p), która z produktu produkuje tablicę Stringów
        BufferedReader br = new BufferedReader(new FileReader(path));
        Map<Long, Product> map = new HashMap<>();
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
        while (br.readLine() != null){
            if (readProductFromLine(br).getNumber().equals(product.getNumber()))
                map.put(readProductFromLine(br).getNumber(), product);
            map.put(readProductFromLine(br).getNumber(), readProductFromLine(br));
        }

        map.entrySet().forEach(new Consumer<Map.Entry<Long, Product>>() {
            @Override
            public void accept(Map.Entry<Long, Product> longProductEntry) {
                try {
                    output.writeObject(toLine(longProductEntry.getValue()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private String[] toLine(Product p) {
        Picture picture = (Picture) p;
        String[] productLine = new String[6];
        productLine[0] = p.getNumber().toString();
        productLine[1] = String.valueOf(picture.getTags()); //TODO rozdziel średnikiem przed zapisaniem do pliku
        productLine[2] = p.getPrice().toString();
        productLine[3] = String.valueOf(p.isAvailable());
        productLine[4] = p.gerReservedBy().toString();
        productLine[5] = p.getOwner().toString();
        return new String[0];
    }


    // Zaimplementuj metodę find, która czyta csva linia po lini, z każdej linii tworzy produkt używając metody z pkt. 1
    //i sprawdza czy produkt spełnia warunki wyszukiwania dokładanie tak jak robiliśmy to w InMemoryProductRepository
    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        List<Product> products = new LinkedList<>();
        while (br.readLine() != null){
            if (matchesCriteria((Picture) readProductFromLine(br), client, tags, from, to))
            products.add(readProductFromLine(br));
        }
        return products;
    }


    private boolean matchesCriteria(Picture picture, Client client, Set<String> tags, Money from, Money to) {
        if (tags != null && !picture.hasTags(tags))
            return false;

        Money price = picture.calculatePrice(client);

        if (from != null && from.gt(price))
            return false;

        if (to != null && to.lt(price))
            return false;

        return true;
    }
}
