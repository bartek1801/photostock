package pl.com.bottega.photostock.sales.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartek on 06.08.2017.
 */
public class Picture {

    private Long number;
    private Set<String> tags;
    private Money price;
    private Boolean active;

    public Picture(Long number, Set<String> tags, Money price){
        this(number, tags, price, true);   //wywołanie konstruktora w konstruktorze, nie można nic wywołac przed tym
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active){
        this.number = number;
        this.tags = new HashSet<>(tags); // Set nie jest immutable
        this.price = price;  // Money musi być immutable
        this.active = active;
    }

    public Money calculatePrice(Client client){

        return null;
    }

    public boolean isAvailable(){

        return true;
    }

    public void reservedPer(Client client){

    }

    public void unreservedPer(Client client){


    }

    public void soldPer(Client client){


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
