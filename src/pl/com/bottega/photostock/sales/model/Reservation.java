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

    public Reservation(Client client){
        this.owner = client;
    }

    public void add(Picture picture){
        items.add(picture);
    }

    public void remove(Picture picture){
        items.remove(picture);
    }

    public Offer generateOffer(){
        return new Offer(items);
    }

    public int getItemsCount(){
        return items.size();
    }

}
