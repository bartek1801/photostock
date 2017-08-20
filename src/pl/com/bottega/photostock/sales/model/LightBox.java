package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartek on 06.08.2017.
 */
public class LightBox {

    private String name;
    private List<Picture> items = new LinkedList<>();
    private Client owner;

    public LightBox(Client owner, String name){
        this.owner = owner;
        this.name = name;
    }


    public void add(Picture picture){
        if (items.contains(picture))
            throw new IllegalStateException("Picture is alredy added to LightBox");
        if (!picture.isAvailable())
            throw new IllegalArgumentException("Picture is not available");
        items.add(picture);
    }

    public void remove(Picture picture){
        if(!items.remove(picture))
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
}
