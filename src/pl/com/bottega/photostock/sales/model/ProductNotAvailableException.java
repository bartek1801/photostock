package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 02.09.2017.
 */
public class ProductNotAvailableException extends RuntimeException {


    public ProductNotAvailableException(Product product) {
        super(String.format("Product %s is not available", product.getNumber()));
    }
}
