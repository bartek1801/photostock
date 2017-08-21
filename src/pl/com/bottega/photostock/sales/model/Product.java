package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 21.08.2017.
 */
public interface Product {
    Money calculatePrice(Client client);

    boolean isAvailable();

    void reservedPer(Client client);

    void unreservedPer(Client client);

    void soldPer(Client client);

    Long getNumber();
}
