package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by bartek on 20.08.2017.
 */
public class OfferTest {

    @Test
    public void shouldGetTotalCost(){
        //given
        Collection<Product> items = new LinkedList<>();
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Product p1 = new Picture(1l, tags, Money.valueOf(10) );
        Product p2 = new Picture(2L, tags, Money.valueOf(5));
        Product p3 = new Picture(3L, tags, Money.valueOf(15));
        Client client = new StandardClient("Jan Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "02-298"));
        client.recharge(Money.valueOf(1000000));
        items.add(p1);
        items.add(p2);
        items.add(p3);
        Reservation reservation = new Reservation(client);
        reservation.add(p1);
        reservation.add(p2);
        reservation.add(p3);

        //when
        Offer offer = reservation.generateOffer();

    }

}