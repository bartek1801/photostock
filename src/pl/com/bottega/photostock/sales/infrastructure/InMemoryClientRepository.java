package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bartek on 02.09.2017.
 */
public class InMemoryClientRepository implements ClientRepository {

    private static final Map<String, Client> REPO = new HashMap<>();

    static {
        Address address = new Address("ul. Północna 11", "Poland", "Lublin", "02-298");
        Client c1 = new StandardClient("Jan Nowak", address, ClientStatus.STANDARD, Money.ZERO);
        Client c2 = new VIPClient("Jan Nowak", address, ClientStatus.PLATINUM, Money.ZERO, Money.valueOf(50));
        Client c3 = new StandardClient("Jan Nowak", address, ClientStatus.GOLD, Money.valueOf(50));
        Client c4 = new VIPClient("Jan Nowak", address, ClientStatus.PLATINUM, Money.valueOf(100), Money.valueOf(50));

        REPO.put(c1.getNumber(), c1);
        REPO.put(c2.getNumber(), c2);
        REPO.put(c3.getNumber(), c3);
        REPO.put(c4.getNumber(), c4);
    }


    @Override
    public Client get(String clientNumber) {
        if (!REPO.containsKey(clientNumber))
            throw new IllegalArgumentException(String.format("No client %s found", clientNumber));
        return REPO.get(clientNumber);
    }

    @Override
    public void save(Client client) {
        REPO.put(client.getNumber(), client);
    }
}
