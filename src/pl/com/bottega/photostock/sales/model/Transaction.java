package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;

/**
 * Created by bartek on 20.08.2017.
 */
public class Transaction {

    private Money amount;
    private String decription;
    private LocalDateTime dateTime = LocalDateTime.now();

    public Transaction(Money amount, String decription) {
        this.amount = amount;
        this.decription = decription;
    }

    public Money getAmount() {
        return amount;
    }
}
