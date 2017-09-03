package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartek on 06.08.2017.
 */
public class Picture extends AbstractProduct {

    private Set<String> tags;

    public Picture(Long number, Set<String> tags, Money price){
//        super(number, price, true);
//        this.tags = new HashSet<>(tags);
        this(number, tags, price, true);
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active){
        super(number, price, active);
        this.tags = new HashSet<>(tags); // Set nie jest immutable
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean hasTags(Set<String> tags){
        return this.tags.containsAll(tags);
    }

 /*   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractProduct abstractProduct = (AbstractProduct) o;

        return number.equals(abstractProduct.number);
    }*/

}
