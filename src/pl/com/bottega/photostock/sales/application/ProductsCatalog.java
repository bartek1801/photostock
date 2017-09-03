package pl.com.bottega.photostock.sales.application;

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

    public List<Product> find(Set<String> tags, Money from, Money to){
        return repository.find(tags, from, to);
    }

    //find(null, null, null)
    //find(jakieśTagi , null, null)

}
