package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {

    private Client owner;
    private List<Product> items;

    public Offer(Client owner, Collection<Product> items) {
        this.owner = owner;
        this.items = new LinkedList<>(items);
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.calculatePrice(owner).compareTo(o1.calculatePrice(owner));
            }
        });
    }

    public boolean sameAs(Offer offer, Money tolerance) {
        return false;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money total = Money.ZERO;
        for (Product item : items)
            total = total.add(item.calculatePrice(owner));
        return total;
    }

    public Collection<Product> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public Purchase purchase() {
        Money cost = getTotalCost();
        Purchase purchase = new Purchase(owner, items);
        owner.charge(cost, String.format("Purchase number %s", purchase.getNumber()));
        return purchase;
    }

    public Client getOwner() {
        return owner;
    }
}
