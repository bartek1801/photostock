package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by bartek on 06.08.2017.
 */
public class Reservation {

    private Client owner;
    private Collection<Product> items = new LinkedList<>();

    public Reservation(Client owner){
        this.owner = owner;
    }

    public void add(Product product){
        if (!product.isAvailable())
            throw new IllegalStateException("Product is not available");
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

}
