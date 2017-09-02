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
    //private Client clientWithMoney = new Client("Jan Nowak", address, ClientStatus.VIP, Money.valueOf(200), Money.valueOf(50));
    private Client clientWithMoney = new StandardClient("Jan Nowak", address, ClientStatus.STANDARD, Money.ZERO);
    //private Client clientVIP = new VIPClient("Jan Nowak", address, ClientStatus.VIP, Money.valueOf(50));
    private Client clientWithNoMoney = new StandardClient("Jan Nowak", address);


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
        //when
        clientWithMoney.recharge(Money.valueOf(50));

        //then
        assertTrue(clientWithMoney.canAfford(Money.valueOf(50)));
        assertFalse(clientWithMoney.canAfford(Money.valueOf(51)));

    }

    @Test
    public void shouldChargeAndRechargeClient(){

        //when
        clientWithMoney.recharge(Money.valueOf(100));
        clientWithMoney.charge(Money.valueOf(50), "Testowy zakup");

        //then
        assertTrue(clientWithMoney.canAfford(Money.valueOf(50)));
        assertFalse(clientWithMoney.canAfford(Money.valueOf(51)));
    }



    @Test(expected = IllegalStateException.class)
    public void shouldNotAllowToChargeMoreThanCanAfford(){

        clientWithMoney.charge(Money.valueOf(251), "testowy zakup");
    }

    @Test
    public void shouldCalculateBalance(){
        //when
        clientWithMoney.recharge(Money.valueOf(50));
        clientWithMoney.recharge(Money.valueOf(200));
        clientWithMoney.charge(Money.valueOf(50), "Testowy zakup 1");
        clientWithMoney.recharge(Money.valueOf(100));
        clientWithMoney.charge(Money.valueOf(250), "Testowy zakup 2");
        clientWithMoney.charge(Money.valueOf(50), "Testowy zakup 3");

        assertEquals(Money.ZERO, clientWithMoney.balance());

    }

    @Test
    public void shouldCalculate0BalanceAtFirst(){

        assertEquals(Money.ZERO, clientWithNoMoney.balance());

    }

}