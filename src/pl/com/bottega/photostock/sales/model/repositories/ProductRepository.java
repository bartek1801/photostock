package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    // pobiera obiekt po identyfikatorze
    Product get(Long number);

    Optional<Product> getOptional(Long number);

    // zapis nowego lub aktualizacja istniejacego obiektu
    void save(Product product) throws IOException;

    List<Product> find(Client client, Set<String> tags, Money from, Money to) throws IOException;
}
