package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by bartek on 06.08.2017.
 */
public class Purchase {

    private Collection<Picture> items;
    private Client buyer;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Client buyer, Collection<Picture> items){
        this.buyer = buyer;
        this.items = new LinkedList<>(items);
    }

}
