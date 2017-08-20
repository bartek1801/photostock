package pl.com.bottega.photostock.sales.model;

import sun.misc.Cleaner;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by bartek on 06.08.2017.
 */
public class Reservation {

    private Client owner;
    private Collection<Picture> items = new LinkedList<>();

    public Reservation(Client owner){
        this.owner = owner;
    }

    public void add(Picture picture){
        if (!picture.isAvailable())
            throw new IllegalStateException("Product is not available");
        items.add(picture);
        picture.reservedPer(owner);
    }

    public void remove(Picture picture){
        if(items.remove(picture))
            picture.unreservedPer(owner);
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
