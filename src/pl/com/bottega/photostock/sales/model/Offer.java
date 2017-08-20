package pl.com.bottega.photostock.sales.model;

import com.sun.corba.se.impl.interceptors.PICurrent;

import java.util.*;

/**
 * Created by bartek on 06.08.2017.
 */
public class Offer {

    private List<Picture> items; //musi być lista żeby można było ją sortować
    private Client owner;

    public Offer(Client owner, Collection<Picture> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
        this.items.sort(new Comparator<Picture>() {
            //Client owner;
            // wtedy aby dostać sie do pola w klasie Offer trzeba napisać
            //return p2.calculatePrice(Offer.this.owner).compareTo(p1.calculatePrice(Offer.this.owner));
            @Override
            public int compare(Picture p1, Picture p2) {
                return p2.calculatePrice(owner).compareTo(p1.calculatePrice(owner));
            }
        });
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
