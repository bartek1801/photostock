package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartek on 30.08.2017.
 */
public class VIPClient extends Client {

    private Money creditLimit;


    public VIPClient(String name, Address address, ClientStatus status, Money creditLimit) {
        super(name, address, status);
        this.creditLimit = creditLimit;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }


    @Override
    public boolean canAfford(Money amount) {
        return balance().add(creditLimit).gte(amount);
    }

    @Override
    public void charge(Money amount, String reason) {
        super.charge(amount, reason);
        calculateCreditLimit(balance());
    }

    private void calculateCreditLimit(Money balance) {
        if (balance().lt(Money.ZERO))
            creditLimit = creditLimit.add(balance);
    }

    @Override
    public void recharge(Money amount) {
        calculateCreditLimit(balance().neg());
        super.recharge(amount);
    }
}
