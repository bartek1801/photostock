package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryPictureRepository;
import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by bartek on 06.08.2017.
 */
public class ConsoleAplication {


    public static void main(String[] args) {
        PictureRepository repository = new InMemoryPictureRepository();

        Picture p1 = repository.get(1L);//pobieramy produkty z repozytorium
        Picture p2 = repository.get(2L);
        Picture p3 = repository.get(3L);

        Client client = new Client("Jan Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "02-298"));
        Client clientPlatinium = new Client("Jan Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "02-298"),
                ClientStatus.PLATINUM, Money.valueOf(100), Money.valueOf(200));
        client.recharge(Money.valueOf(1000000));
        Reservation reservation = new Reservation(clientPlatinium);
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

        for (Picture item : offer.getItems()){
            System.out.println(String.format("%d. --->> %s", item.getNumber(), item.calculatePrice(client)));
        }

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
