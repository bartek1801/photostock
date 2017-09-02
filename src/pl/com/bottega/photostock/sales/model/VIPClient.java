package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartek on 30.08.2017.
 */
public class VIPClient extends Client {

    private Money creditLimit;


    public VIPClient(String name, Address address, ClientStatus status, Money balance, Money creditLimit) {
        super(name, address, status, balance);
        this.creditLimit = creditLimit;
    }

    public VIPClient(String name, Address address) {
        this(name, address, ClientStatus.VIP, Money.ZERO, Money.ZERO);
    }

    @Override
    public boolean canAfford(Money amount) {
        return balance().add(creditLimit).gte(amount);
    }

}
