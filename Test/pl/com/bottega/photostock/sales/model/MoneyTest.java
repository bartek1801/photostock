package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

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
    public void sholdSayIfGreaterThanorEquals(){

        assertTrue(seventyCredit.gtev1(fiftyCredit));
        assertTrue(seventyEuro.gtev1(fiftyEuro));

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


}
