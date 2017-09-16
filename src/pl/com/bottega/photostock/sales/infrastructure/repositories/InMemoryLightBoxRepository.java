package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.LightBoxRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by bartek on 03.09.2017.
 */
public class InMemoryLightBoxRepository implements LightBoxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();

    static {
        Address address = new Address("ul. Północna 11", "Poland", "Lublin", "02-298");
        Client c1 = new StandardClient("Jan Nowak", address, ClientStatus.STANDARD, Money.ZERO);
        LightBox l1 = new LightBox(c1, "LajtBox 1");
        LightBox l2 = new LightBox(c1, "kotki");

        REPO.put(l1.getNumber(), l1);
        REPO.put(l2.getNumber(), l2);
    }


    @Override
    public LightBox get(String number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException(String.format("LihgtBox %s not found", number));
        return REPO.get(number);
    }


    @Override
    public void save(LightBox lightBox) {
        REPO.put(lightBox.getNumber(), lightBox);
    }

    @Override
    public List<LightBox> getClientLightBoxes(String clientNumber) {
        List<LightBox> lboxes = new LinkedList<>();
        for (LightBox lightBox : REPO.values()) {
            if (lightBox.getOwner().getNumber().equals(clientNumber)) {
                lboxes.add(lightBox);
            }
        }
        return lboxes;
    }
}
