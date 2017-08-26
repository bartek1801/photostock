package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bartek on 19.08.2017.
 */
public class MoneyTest {

    private Money fiftyCredit = Money.valueOf(50);
    private Money seventyCredit = Money.valueOf(70);
    private Money fiftyEuro = Money.valueOf(50, "EUR");
    private Money seventyEuro = Money.valueOf(70, "EUR");
    private Money oneEuro = Money.valueOf(1, "EUR");
    private Money tenPLN = Money.valueOf(1, "EUR");
    private Money onePLN = Money.valueOf(1, "EUR");
    private Money oneDolllar = Money.valueOf(1, "USD");

    @Test
    public void shoulAddMoney(){

        //when
        Money m1PlusM2 = fiftyCredit.add(seventyCredit);

        //then
        Money expected = Money.valueOf(120);
        assertEquals(expected, m1PlusM2);
    }

    @Test
    public void shoulAddMoneyV2(){

        //when
        Money m1PlusM2 = fiftyEuro.add(seventyEuro);

        //then
        Money expected = Money.valueOf(120,"EUR");
        assertEquals(expected, m1PlusM2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddMoneyInDifferentCurrencies(){
        //given
        Money m1 = Money.valueOf(50, "USD");
        Money m2 = Money.valueOf(70, "EUR");

        //when
        m1.add(m2);

    }

    @ Test
    public void shoulSubstractMoney(){

        //when
        Money m1MinusM2 = fiftyCredit.sub(seventyCredit);

        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, m1MinusM2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSubstractMoneyInDifferentCurrencies(){
        //given
        Money m1 = Money.valueOf(70, "USD");
        Money m2 = Money.valueOf(50, "EUR");

        //when
        m1.add(m2);

    }


    @Test
    public void shouldCompareMoney(){

        assertTrue(fiftyCredit.compareTo(seventyCredit) < 0);
        assertTrue(seventyCredit.compareTo(fiftyCredit) > 0);
        assertTrue(fiftyCredit.compareTo(fiftyCredit) == 0);
    }

    @Test
    public void shouldCompareMoneyUsingBooleanMethods(){

        assertTrue(fiftyCredit.lt(seventyCredit));
        assertTrue(fiftyCredit.lte(seventyCredit));
        assertTrue(seventyCredit.gt(fiftyCredit));
        assertTrue(seventyCredit.gte(fiftyCredit));
        assertFalse(fiftyCredit.gt(seventyCredit));
        assertFalse(seventyCredit.lte(fiftyCredit));
        assertFalse(seventyCredit.lt(seventyCredit));
        assertFalse(fiftyCredit.gt(fiftyCredit));
        assertTrue(fiftyCredit.gte(fiftyCredit));
        assertTrue(fiftyCredit.lte(fiftyCredit));

        //dopisz resztę przypadków
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCompareDifferentCurrencies(){
        fiftyCredit.compareTo(fiftyEuro);
    }

    @Test
    public void shouldCalculatePercent(){
        assertEquals(Money.valueOf(5), fiftyCredit.percent(10));
        assertEquals(Money.valueOf(5.50), fiftyCredit.percent(11));
        assertEquals(Money.valueOf(75), fiftyCredit.percent(150));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.11).percent(10));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.19).percent(10));
    }

    @Test
    public void shouldGetCurrency(){
        Money m = Money.valueOf(100, "PLN");

        assertEquals("PLN", m.currency());
    }

    @Test
    public void shouldConvertToTargetCurrency(){

        assertEquals(Money.valueOf(3.6, "PLN"), Money.valueOf(1, "USD").convert("PLN", 3.6));
        assertEquals(Money.valueOf(4.2, "PLN"), Money.valueOf(1, "EUR").convert("PLN", 4.2));

    }





}
