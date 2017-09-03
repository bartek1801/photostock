package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.Client;

import java.util.Optional;

/**
 * Created by bartek on 02.09.2017.
 */
public interface ClientRepository {

    Client get(String clientNumber);

    void save(Client client);

    Optional<Client> getByLogin(String login);
}
