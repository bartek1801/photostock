package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by bartek on 20.08.2017.
 */
public interface ProductRepository {

    //pobiera obiekt o identyfikatorze
    Product get(Long number);

    //zapis nowego lub aktualizacja istiejącego
    void save(Product product);

    Optional<Product> getOptional(Long number); //TODO poczytaj o optional

    List<Product> find(Client client, Set<String> tags, Money from, Money to);
}
