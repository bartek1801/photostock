package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 02.09.2017.
 */
public class StandardClient extends Client {

    public StandardClient(String name, Address address, ClientStatus status, Money balance) {
        super(name, address, status, balance);
    }

    public StandardClient(String name, Address address) {
        this(name, address, ClientStatus.STANDARD, Money.ZERO);
    }

    @Override
    public boolean canAfford(Money amount) {
        return balance().gte(amount);
    }
}
