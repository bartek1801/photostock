package pl.com.bottega.photostock.sales.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by bartek on 24.08.2017.
 */
public class CurrencyConverterTest {


    private final Money sevenPln = Money.valueOf(7, "PLN");
    private final Money twoBucks = Money.valueOf(2, "USD");
    private final Money twoBitcoins = Money.valueOf(2, "BTC");
    private Map<String, Double> exRates = new HashMap<>();

    private CurrencyConverter sut; // skrót od system under test

    @Before
    public void setUp(){
        exRates.put("USD", 3.5);
        exRates.put("EUR", 4.2101);
        sut = new CurrencyConverter("PLN", exRates);
    }

    @Test
    public void shouldConvertToPLN(){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 3.6020); rates.put("EUR", 4.2345);
        CurrencyConverter c = new CurrencyConverter("PLN", rates);


        assertEquals(Money.valueOf(3.60, "PLN"), c.convert(Money.valueOf(1, "USD")));
        assertEquals(Money.valueOf(360.20, "PLN"), c.convert(Money.valueOf(100, "USD")));
        assertEquals(Money.valueOf(9.32, "PLN"), c.convert(Money.valueOf(2.59, "USD")));
        assertEquals(Money.valueOf(4.23, "PLN"), c.convert(Money.valueOf(1, "EUR")));
        assertEquals(Money.valueOf(423.45, "PLN"), c.convert(Money.valueOf(100, "EUR")));
        assertEquals(Money.valueOf(100, "PLN"), c.convert(Money.valueOf(100, "PLN")));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenRateIsNotDefined(){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 3.6020); rates.put("EUR", 4.2345);
        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        c.convert(Money.valueOf(100, "U"));
        c.convert(Money.valueOf(100, "EU"));
    }

    @Test
    public void shouldConvertMoneyAmount(){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 3.6020); rates.put("EUR", 4.2345);
        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        //currency jest walutą główną zwracamy convert(Money amount)
        assertEquals(c.convert(Money.valueOf(10, "PLN"), "PLN"), Money.valueOf(10, "PLN"));
        //currency nie jest walutą główną i amount jest w walucie głównej
        assertEquals(c.convert(Money.valueOf(2, "PLN"), "USD"), Money.valueOf(2 / 3.6020, "USD") );
        //currency nie jest walutą główną i amount nie jest walutą główną
//        assertEquals( c.convert(Money.valueOf(1, "EUR"), "USD"), Money.valueOf(1 * 4.2345 / 3.6020, "PLN"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnyRateIsNotDefined(){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 3.6020); rates.put("EUR", 4.2345);
        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        assertEquals( c.convert(Money.valueOf(1, "E"), "USD"), Money.valueOf(1 * 4.2345 / 3.6020, "PLN"));
        assertEquals( c.convert(Money.valueOf(1, "EUR"), "D"), Money.valueOf(1 * 4.2345 / 3.6020, "PLN"));
        assertEquals( c.convert(Money.valueOf(1, "ER"), "D"), Money.valueOf(1 * 4.2345 / 3.6020, "PLN"));

    }
}
