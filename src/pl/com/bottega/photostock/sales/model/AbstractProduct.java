package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 20.08.2017.
 */
public abstract class AbstractProduct implements Product {
    protected Long number;
    protected Money price;
    protected Boolean active;
    private Client reservedBy;
    private Client owner;

    public AbstractProduct(Long number,Money price, Boolean active) {
        this.number = number;
        this.price = price;  // Money musi być immutable
        this.active = active;
    }

    public AbstractProduct(Long number, Money price) {
        this.number = number;
        this.price = price;
    }

    @Override
    public Money calculatePrice(Client client){
        return price.percent(100 - client.discountPercent());
    }

    @Override
    public boolean isAvailable(){
        return active && reservedBy == null;
    }

    @Override
    public void reservedPer(Client client){
        if (!isAvailable())
            throw new IllegalStateException("Product is not available");
        reservedBy = client;
    }

    @Override
    public void unreservedPer(Client client){
        if (owner != null)
            throw new IllegalStateException("Product is alredy purchased");
        checkReservation(client);
        reservedBy = null;
    }

    @Override
    public void soldPer(Client client){
        checkReservation(client);
        owner = client;
    }

    private void checkReservation(Client client) {
        if (reservedBy == null || !reservedBy.equals(client))
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }



    @Override
    public Long getNumber() {
        return number;
    }
}
