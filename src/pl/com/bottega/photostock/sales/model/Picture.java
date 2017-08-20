package pl.com.bottega.photostock.sales.model;

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
    private Client reservedBy;
    private Client owner;

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
        return price.percent(100 - client.discountPercent());
    }
    //zamiast tego
    /*switch (client.getStatus()) {
            case SILVER:
                return price.percent(95);
            case GOLD:
                return price.percent(90);
            case PLATINUM:
                return price.percent(85);
            }
        return price;
        */

    public boolean isAvailable(){
        return active && reservedBy == null;
    }

    public void reservedPer(Client client){
        if (!isAvailable())
            throw new IllegalStateException("Product is not available");
        reservedBy = client;
    }

    public void unreservedPer(Client client){
        if (owner != null)
            throw new IllegalStateException("Product is alredy purchased");
        checkReservation(client);
        reservedBy = null;
    }

    public void soldPer(Client client){
        checkReservation(client);
        owner = client;
    }

    private void checkReservation(Client client) {
        if (reservedBy == null || !reservedBy.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
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

    public Long getNumber() {
        return number;
    }
}
