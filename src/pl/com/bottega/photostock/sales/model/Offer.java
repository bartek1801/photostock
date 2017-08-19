package pl.com.bottega.photostock.sales.model;

import com.sun.corba.se.impl.interceptors.PICurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by bartek on 06.08.2017.
 */
public class Offer {

    private Collection<Picture> items;

    public Offer(Collection<Picture> items){
        this.items = new LinkedList<>(items);
    }


    public boolean sameAs(Offer offer, Money tolerance){
        return false;
    }

    public int getItemsCount(){

        return -1;
    }

    public Money getTotalCost(){
        return null;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items); // lub  new LinkedList<>(items)
    }
}
