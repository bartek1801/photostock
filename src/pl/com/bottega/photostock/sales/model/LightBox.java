package pl.com.bottega.photostock.sales.model;

import java.util.*;

/**
 * Created by bartek on 06.08.2017.
 */
public class LightBox {

    private String name;
    private List<Picture> items = new LinkedList<>();
    private Client owner;
    private String number;

    public LightBox(Client owner, String name){
        this.owner = owner;
        this.name = name;
        this.number = UUID.randomUUID().toString();
    }


    public void add(Picture product){
        if (items.contains(product))
            throw new IllegalStateException("Picture is alredy added to LightBox");

        product.ensureAvailable();

        items.add(product);
    }

    public void remove(Picture product){
        if(!items.remove(product))
            throw new IllegalArgumentException("Product is not on your LightBox");
    }


    public String getName() {
        return name;
    }

    public Client getOwner() {
        return owner;
    }

    public List<Picture> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getNumber() {
        return number;
    }

    public List<Picture> getPictures(Set<Long> pictureNumbers) {
        List<Picture> results = new LinkedList<>();
        for (Picture pic : items){
            if (pictureNumbers.contains(pic.getNumber()))
                results.add(pic);
        }
        return results;
    }
}
