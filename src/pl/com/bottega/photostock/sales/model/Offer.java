package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by bartek on 06.08.2017.
 */
public class Offer {

    private List<Product> items; //musi być lista żeby można było ją sortować
    private Client owner;

    public Offer(Client owner, Collection<Product> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
        this.items.sort(new Comparator<Product>() {
            //Client owner;
            // wtedy aby dostać sie do pola w klasie Offer trzeba napisać
            //return p2.calculatePrice(Offer.this.owner).compareTo(p1.calculatePrice(Offer.this.owner));
            @Override
            public int compare(Product p1, Product p2) {
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
        for (Product item : items){
            totalCost = totalCost.add(item.calculatePrice(owner)); //metoda add() tworzu nowy obiekt typu Money
        }
        return totalCost;
    }

    public Collection<Product> getItems() {
        return Collections.unmodifiableCollection(items); // lub  new LinkedList<>(items)
    }

    public Client getOwner() {
        return owner;
    }

    public Purchase purchase() {
        Money cost = getTotalCost();
        Purchase purchase = new Purchase(owner, items);
        owner.charge(cost, String.format("Purchase number %s", purchase.getNumber()));
        return purchase;
    }

}
