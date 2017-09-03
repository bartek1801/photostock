package pl.com.bottega.photostock.sales.infrastructure;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import pl.com.bottega.photostock.sales.model.*;

import java.util.*;

/**
 * Created by bartek on 20.08.2017.
 */
public class InMemoryProductRepository implements ProductRepository {

    private static final Map<Long, Product> REPO = new HashMap<>();//wspólne dla wszystkich obiektów tej klasy

    //statyczny blok inicjujący, mamy w nim dostęp do zmiennych statycznych czyli REPO
    //wykona się w momencie załadowania klasy
    //konstruktor dla klasy
    static {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Product p1 = new Picture(1l, tags, Money.valueOf(10) );
        Product p2 = new Picture(2L, tags, Money.valueOf(5));
        Product p3 = new Picture(3L, tags, Money.valueOf(15));
        //Product p4 = new Clip (4L, Money.valueOf(25),120L);
        REPO.put(1L, p1);
        REPO.put(2L, p2);
        REPO.put(3L, p3);
        //REPO.put(4L, p4);
    }

    @Override
    public Product get(Long number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        if (REPO.containsKey(number))
            return Optional.of(REPO.get(number));
        else
            return Optional.empty();
    }

    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        List<Product> results = new LinkedList<>();
        for (Product product : REPO.values()){
            if (product instanceof Picture) {   // lub product.getClass().equals(Picture.class) nie zadziała w przypadku klasy otomnej po Picture
                Picture picture = (Picture) product;
                if (matchesCriteria(picture, client, tags, from, to))
                    results.add(picture);
            }
        }
      return results;
    }

    private boolean matchesCriteria(Picture picture, Client client, Set<String> tags, Money from, Money to){
        if (tags != null && !picture.hasTags(tags))
            return false;               // picture sie nie łapie wiec zwracamy false

        Money price = picture.calculatePrice(client);

        if (from != null && from.gte(price))
            return false;

        if (to != null & to.lt(price))
            return false;

        return true; //
    }

    @Override
    public void save(Product product) {
        REPO.put(product.getNumber(), product);
    }
}
