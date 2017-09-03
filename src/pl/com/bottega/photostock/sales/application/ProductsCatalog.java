package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by bartek on 02.09.2017.
 */
public class ProductsCatalog {

    private ProductRepository repository;

    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        return repository.find(client, tags, from, to);
    }

}
