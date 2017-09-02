package pl.com.bottega.photostock.sales.model;

import sun.plugin2.main.client.MessagePassingOneWayJSObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by bartek on 19.08.2017.
 */
public abstract class Client {

    private String name;
    private Address address;
    private ClientStatus status;
    private List<Transaction> transactions = new LinkedList<>();


    public Client(String name, Address address, ClientStatus status, Money balance) {
        this.name = name;
        this.address = address;
        this.status = status;
        if (balance.gt(Money.ZERO))
            transactions.add(new Transaction(balance, "First charge"));
    }

    public Client(String name, Address address) {
        this(name, address, ClientStatus.STANDARD, Money.ZERO);
    }

    public ClientStatus getStatus() {
        return status;
    }


    public abstract boolean canAfford(Money amount);

    protected Money balance(){
        Money balance = Money.ZERO;
        for (Transaction item : transactions){
            balance = balance.add(item.getAmount());
        }
        return balance;
    }

    public void charge(Money amount, String reason) {
        if (!canAfford(amount))
            throw new IllegalStateException("Not enough balance");
        transactions.add(new Transaction(amount.neg(), reason));
    }

    public void recharge(Money amount) {
        transactions.add(new Transaction(amount, "Reacharge acount"));
    }

    public int discountPercent() {
        return status.getDiscount();
    }

}
