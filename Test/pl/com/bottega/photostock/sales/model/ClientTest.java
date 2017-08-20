package pl.com.bottega.photostock.sales.model;

import com.sun.glass.ui.ClipboardAssistance;
import org.junit.Test;
import sun.misc.Cleaner;

import static org.junit.Assert.*;

/**
 * Created by bartek on 20.08.2017.
 */
public class ClientTest {

    private final Address address = new Address("ul. Północna 11", "Poland", "Lublin", "02-298");
    private Client clientWithCredit = new Client("Jan Nowak", address, ClientStatus.VIP, Money.valueOf(100), Money.valueOf(100));
    private Client clientWithNoMoney = new Client("Jan Nowak", address);


    @Test
    public void shouldIfClientCanAfford(){

        //when
        clientWithNoMoney.recharge(Money.valueOf(100));

        //then
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(100)));
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(50)));
        assertFalse(clientWithNoMoney.canAfford(Money.valueOf(101)));
    }

    @Test
    public void shouldIfClientCanAffordWithCredit(){

        //then
        assertTrue(clientWithCredit.canAfford(Money.valueOf(200)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(201)));

    }

    @Test
    public void shouldChargeAndRechargeClient(){

        //when
        clientWithCredit.charge(Money.valueOf(200), "Testowy zakup");
        clientWithCredit.recharge(Money.valueOf(100));

        //then
        assertTrue(clientWithCredit.canAfford(Money.valueOf(100)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(101)));
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowToChargeMoreThanCanAfford(){
        clientWithCredit.charge(Money.valueOf(50), "testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "testowy zakup");
    }

}