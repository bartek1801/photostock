package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
        Money m1MinusM2 = fiftyCredit.substract(seventyCredit);

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

        assertTrue(seventyCredit.gte(fiftyCredit));
        assertTrue(seventyEuro.gte(fiftyEuro));

    }


}
