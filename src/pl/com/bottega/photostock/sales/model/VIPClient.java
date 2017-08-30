package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 30.08.2017.
 */
public class VIPClient extends Client {

    private Money creditLimit;

    public VIPClient(String name, Address address, ClientStatus status, Money creditLimit) {
        super(name, address, status);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean canAfford(Money amount) {
        return balance().add(creditLimit).gte(amount);
    }

    @Override
    public void charge(Money amount, String reason) {
        super.charge(amount, reason);
    }

    @Override
    public void recharge(Money amount) {
        super.recharge(amount);
    }
}
