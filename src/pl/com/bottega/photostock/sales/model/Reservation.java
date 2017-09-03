package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by bartek on 06.08.2017.
 */
public class Reservation {

    private Client owner;
    private String number;
    private Collection<Product> items = new LinkedList<>();

    public Reservation(Client owner){
        this.number = UUID.randomUUID().toString();
        this.owner = owner;
    }

    public void add(Product product){
        product.ensureAvailable();

        items.add(product);
        product.reservedPer(owner);
    }

    public void remove(Product product){
        if(items.remove(product))
            product.unreservedPer(owner);
        else
            throw new IllegalArgumentException("Product is not a part of this reservation");
    }

    public Offer generateOffer(){
        return new Offer(owner, items);
    }

    public int getItemsCount(){
        return items.size();
    }

    public String getNumber() {
        return number;
    }
}
