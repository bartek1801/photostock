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
    private Client owner;

    public Offer(Client owner, Collection<Picture> items){
        this.owner = owner;
        this.items = new LinkedList<>(items);
    }


    public boolean sameAs(Offer offer, Money tolerance){
        return false;
    }

    public int getItemsCount(){
        return items.size();
    }

    public Money getTotalCost(){
        Money totalCost = Money.ZERO;
        for (Picture item : items){
            totalCost = totalCost.add(item.calculatePrice(owner)); //metoda add() tworzu nowy obiekt typu Money
        }
        return totalCost;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items); // lub  new LinkedList<>(items)
    }
}
