package pl.com.bottega.photostock.sales.model;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartek on 19.08.2017.
 */
public class Money implements Comparable<Money> {

    public static final String DEFAULT_CURRENCY = "CREDIT";
    public static final Money ZERO = new Money();

    private Long cents;
    private String currency;

    private Money() { // prywatny konstruktor, nie można wywołac go z zewnątrz, na potrzeby Money.ZERO
        this.cents = 0L;
        this.currency = DEFAULT_CURRENCY;
    }



    private Money(Long cents, String currency){//?? czy może być taki konstrunktor??
        this.cents = cents;
        this.currency = currency;
    }

    public Long getCents() {
        return cents;
    }

    public static Money valueOf(Integer value) {
        return valueOf(value, DEFAULT_CURRENCY);
    }

    public static Money valueOf(Integer value, String currency) {
        return new Money(value * 100L, currency);
    }

    public static Money valueOf(double value) {
        return new Money((long) (value * 100.0), DEFAULT_CURRENCY);
    }

    public static Money valueOf(double value, String currency) {
        return new Money((long) (value * 100.0), currency);
    }



    public Money add(Money other) {
        checkCurrency(other);
        return new Money(cents + other.cents, currency);
    }

    public Money sub(Money other) {
        return add(other.neg());
    }


    public Money neg() {
        return new Money(-cents, currency);
    }

    private void checkCurrency(Money other) {
        if(!currency.equals(other.currency))
            throw new IllegalArgumentException("Incopatible currencies");
    }


    @Override
    public String toString() {
        return String.format("%d.%d %s", cents / 100, cents % 100, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (!cents.equals(money.cents)) return false;
        return currency.equals(money.currency);
    }



    @Override
    public int hashCode() {
        int result = cents.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }


    @Override
    public int compareTo(Money other) {
        checkCurrency(other);
        return (int) (cents - other.cents);
    }

    public boolean lt(Money other) {
        return compareTo(other) < 0;
    }
    public boolean lte(Money other) {
        return compareTo(other) <= 0;
    }
    public boolean gt(Money other) {
        return compareTo(other) > 0;
    }
    public boolean gte(Money other) {
        return compareTo(other) >= 0;
    }


    public Money percent(int percent) {
        return new Money(cents * percent / 100, currency);
    }

    public String currency(){
        return currency;
    }

    public Money convert(String targetCurrency, double exRate) {
        return new Money((long)(cents * exRate), targetCurrency);
    }


}
