package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartek on 23.08.2017.
 */
public class CurrencyConverter {

    private String mainCurrency;
    private Map<String, Double> exRates;


    public CurrencyConverter(String mainCurrency, Map<String, Double> exRates) {
        this.mainCurrency = mainCurrency;
        this.exRates = new HashMap<>(exRates);
    }




   /* public Money convert(Money amount) {
        if (mainCurrency.equals(amount.currency())) {
            return amount;
        }
        else {
            containsCurrency(amount.currency());
            return Money.valueOf(exRates.get(amount.currency()) * amount.getCents() / 100, mainCurrency);
        }
    }

    public Money convert(Money amount, String currency) {
        if (currency.equals(mainCurrency))
            return convert(amount);
        else if (amount.currency().equals(mainCurrency))
            return Money.valueOf(amount.getCents() / exRates.get(currency) / 100, currency);
        else {
            containsCurrency(amount.currency());
            containsCurrency(currency);
            return Money.valueOf(amount.getCents() * exRates.get(amount.currency()) / exRates.get(currency) / 100, mainCurrency);
        }
    }

    private boolean containsCurrency(String currency) {
        if (!exRates.containsKey(currency))
            throw new IllegalArgumentException("Rate is not defined");
        return true;
    }*/


    public Money convert(Money amount) {
        if(mainCurrency.equals(amount.currency()))
            return amount;
        return amount.convert(mainCurrency, exRate(amount.currency()));
    }

    private Double exRate(String currency) {
        if(!exRates.containsKey(currency))
            throw new IllegalArgumentException("No ex rate for " + currency);
        return exRates.get(currency);
    }

    public Money convert(Money amount, String targetCurrency) {
        if(targetCurrency.equals(mainCurrency))
            return convert(amount);
        if(amount.currency().equals(mainCurrency))
            return amount.convert(targetCurrency, 1/exRate(targetCurrency));
        return convert(convert(amount), targetCurrency);
    }

}
