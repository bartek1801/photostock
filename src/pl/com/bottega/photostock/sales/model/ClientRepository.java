package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 02.09.2017.
 */
public interface ClientRepository {

    Client get(Long clientNumber);
}
