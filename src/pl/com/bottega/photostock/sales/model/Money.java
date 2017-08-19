package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 19.08.2017.
 */
public class Money {

    public static final String DEFAULT_CURRENCY = "CREDIT";
    public static final Money ZERO = new Money();

    private Long cents;
    private String currency;

    private Money() { // prywatny konstruktor, nie można wywołac go z zewnątrz
        this.cents = 0L;
        this.currency = DEFAULT_CURRENCY;
    }


    private Money (Long cents, String currency){
        this.cents = cents;
        this.currency = currency;
    }


    public static Money valueOf(Integer value) {
        return valueOf(value, DEFAULT_CURRENCY);
    }

    public static Money valueOf(Integer value, String currency) {
        return new Money(value * 100L, currency);
    }

    public Money add(Money other) {
        checkCurrency(other);
        return new Money(cents + other.cents, currency);
    }

    public Money substract(Money other) {
        return add(other.neg());
    }

    public Money neg() {
        return new Money(-cents, currency);
    }

    private void checkCurrency(Money other) {
        if(!currency.equals(other.currency))
            throw new IllegalArgumentException("Incopatible currencies");
    }

    public boolean gte(Money other){
        checkCurrency(other);
        return cents >= other.cents;
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



}
