package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

public class Picture extends AbstractProduct {

    private Set<String> tags;

    public Picture(Long number, Set<String> tags, Money price) {
        this(number, tags, price, true);
    }

    public Picture(Long number, Set<String> tags, Money price, Boolean active) {
        super(price, active, number);
        this.tags = new HashSet<>(tags);
    }

    public boolean hasTags(Set<String> tags) {
        return this.tags.containsAll(tags);
    }

    public Set<String> getTags() {
        return tags;
    }
}
