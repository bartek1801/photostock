package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by bartek on 06.08.2017.
 */
public class LightBox {

    private String name;
    private Collection<Picture> items = new LinkedList<>();
    private Client owner;

    public LightBox(Client owner){
        this.owner = owner;
    }


    public void add(Picture picture){

    }

    public void remove(Picture picture){

    }



}
