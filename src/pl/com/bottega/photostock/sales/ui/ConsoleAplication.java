package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartek on 06.08.2017.
 */
public class ConsoleAplication {


    public static void main(String[] args) {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Picture p1 = new Picture(1l, tags, Money.valueOf(10) );
        Picture p2 = new Picture(2L, tags, Money.valueOf(5));
        Picture p3 = new Picture(3L, tags, Money.valueOf(15));
        Client client = new Client("Jan Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "02-298"));
        client.recharge(Money.valueOf(1000000));
        Reservation reservation = new Reservation(client);
        LightBox l = new LightBox(client, "koty");
        l.add(p1);
        l.add(p2);
        l.add(p3);
        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(l);

        reservation.add(p1);
        reservation.add(p2);
        presenter.show(l);
        reservation.add(p3);


        System.out.println(String.format("W rezerwacji jest %d produktów", reservation.getItemsCount()));
        //%d oznacza liczbę dziesiętną może być np %s jak string

        Offer offer = reservation.generateOffer();

        Money cost = offer.getTotalCost();
        if (client.canAfford(cost)){
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(cost, String.format("Zakup %s", purchase));
            System.out.println(String.format("Ilośc zakupionych zdjęć: %d , całkowity koszt: %s ",
                    offer.getItemsCount(), offer.getTotalCost()));
        }
        else
            System.out.println("Sorry! Nie stać Cię ;p");


    }
}
