package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bartek on 30.08.2017.
 */
public class VIPClientTest {

    private final Address address = new Address("ul. Północna 11", "Poland", "Lublin", "02-298");
    private VIPClient clientVIP = new VIPClient("Jan Nowak", address, ClientStatus.VIP, Money.valueOf(50));

    @Test
    public void shouldCanAffordVIPClient(){

        //when
        clientVIP.recharge(Money.valueOf(100));
        clientVIP.charge(Money.valueOf(50), "Testowy zakup");

        //then
        assertTrue(clientVIP.canAfford(Money.valueOf(100)));
        assertFalse(clientVIP.canAfford(Money.valueOf(101)));
    }

    @Test
    public void shouldCalculate0BalanceAtFirst(){

        assertEquals(Money.ZERO, clientVIP.balance());

    }

    @Test
    public void shouldChargeCreditLimit(){

        //when
        clientVIP.charge(Money.valueOf(30), "Testowy zakup");

        //then
        assertEquals(Money.valueOf(20), clientVIP.getCreditLimit());

    }

    @Test
    public void shouldRechargeCreditLimitFirst(){

        //when
        clientVIP.charge(Money.valueOf(30), "Testowy zakup");
        clientVIP.recharge(Money.valueOf(50));

        //then
        assertEquals(Money.valueOf(50), clientVIP.getCreditLimit());
        assertEquals(Money.valueOf(20), clientVIP.balance());

    }

}
