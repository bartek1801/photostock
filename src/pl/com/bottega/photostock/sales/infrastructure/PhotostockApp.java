package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.application.LightBoxManagment;
import pl.com.bottega.photostock.sales.application.ProductCatalog;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryLihtBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.repositories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositories.LightBoxRepository;
import pl.com.bottega.photostock.sales.model.repositories.ProductRepository;
import pl.com.bottega.photostock.sales.model.repositories.ReservationRepository;
import pl.com.bottega.photostock.sales.ui.*;

import java.util.Scanner;

/**
 * Created by bartek on 03.09.2017.
 */
public class PhotostockApp {


    public static void main(String[] args) {
        new PhotostockApp().start();
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        LightBoxRepository lightBoxRepository = new InMemoryLihtBoxRepository();
        ClientRepository clientRepository = new InMemoryClientRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();

        LightBoxManagment lightBoxManagment = new LightBoxManagment(lightBoxRepository, clientRepository,
                productRepository, reservationRepository);
        LightBoxManagmentScreen lightBoxManagmentScreen = new LightBoxManagmentScreen(scanner, lightBoxManagment);
        ProductCatalog productCatalog = new ProductCatalog(productRepository);
        AuthenticationManager authenticationManager = new AuthenticationManager(clientRepository);
        SearchScreen searchScreen = new SearchScreen(scanner,authenticationManager, productCatalog);
        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagmentScreen, searchScreen);
        AuthenticationScreen authenticationScreen = new AuthenticationScreen(scanner, authenticationManager);

        authenticationScreen.show();
        mainScreen.show();
    }

}
