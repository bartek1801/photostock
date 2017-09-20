package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;

import javax.swing.text.html.Option;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
            return getProductFromLine(number, br);
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e); //przepakowywanie wyjątku
        }
    }

    private Optional<Product> getProductFromLine(Long number, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null){
            String[] lineSplit = line.split(",");
            if (lineSplit[0].equals(number.toString())){
                Long nr = Long.parseLong(lineSplit[0]);
                String[] tags = lineSplit[1].split(";");
                Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
                Boolean active = Boolean.parseBoolean(lineSplit[3]);
                String reservedByNumber = lineSplit[4];
                String ownerNumber = lineSplit[5];
                return Optional.of(new Picture(
                        nr,
                        tags,
                        price,
                        active,
                        findClient(reservedByNumber),
                        findClient(ownerNumber)));
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {

        return null;
    }
}
