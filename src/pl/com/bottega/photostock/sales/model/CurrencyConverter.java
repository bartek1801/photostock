package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartek on 23.08.2017.
 */
public class CurrencyConverter {

    private String mainCurrency;
    private Map<String, Double> exchangeRates ;


    public CurrencyConverter(String mainCurrency, Map<String, Double> exchangeRates) {
        this.mainCurrency = mainCurrency;
        this.exchangeRates = new HashMap<>(exchangeRates);
    }




    public Money convert(Money amount) {
        if (mainCurrency.equals(amount.currency())) {
            return amount;
        }
        else {
            containsCurrency(amount.currency());
            return Money.valueOf(exchangeRates.get(amount.currency()) * amount.getCents() / 100, mainCurrency);
            //return new Money((long) (exchangeRates.get(amount.currency()) * amount.getCents()), mainCurrency);
        }
    }

    public Money convert(Money amount, String currency) {
        if (currency.equals(mainCurrency))
            return convert(amount);
        //currency nie jest walutą główną i amount jest w walucie głównej
        else if (amount.currency().equals(mainCurrency))
            return Money.valueOf(amount.getCents() / exchangeRates.get(currency) / 100, currency);
           // return new Money((long) (amount.getCents() / exchangeRates.get(currency)), currency);
        //currency nie jest walutą główną i amount nie jest walutą główną,
        else {
            containsCurrency(amount.currency());
            containsCurrency(currency);
            return Money.valueOf(amount.getCents() * exchangeRates.get(amount.currency()) / exchangeRates.get(currency) / 100, mainCurrency);
            //return new Money((long) (amount.getCents() * exchangeRates.get(amount.currency()) / exchangeRates.get(currency)), mainCurrency);
        }
    }

    private boolean containsCurrency(String currency) {
        //jeśli w mapie nie ma potrzebnego kursu wymiany wyrzuć IllegalArgumentException
        if (!exchangeRates.containsKey(currency))
            throw new IllegalArgumentException("Rate is not defined");
        return true;
    }
}
