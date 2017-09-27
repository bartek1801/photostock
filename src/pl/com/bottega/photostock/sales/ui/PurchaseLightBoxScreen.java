package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.PurchaseProcess;
import pl.com.bottega.photostock.sales.application.PurchaseStatus;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Offer;
import pl.com.bottega.photostock.sales.model.Product;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PurchaseLightBoxScreen {

    private LightBoxManagement lightBoxManagement;
    private PurchaseProcess purchaseProcess;
    private Scanner scanner;

    public PurchaseLightBoxScreen(LightBoxManagement lightBoxManagement, PurchaseProcess purchaseProcess, Scanner scanner) {
        this.lightBoxManagement = lightBoxManagement;
        this.purchaseProcess = purchaseProcess;
        this.scanner = scanner;
    }

    public void show(LightBox lightBox) throws IOException {
        /*Set<Long> numbers = new HashSet<>();
        for (Product p : lightBox.getItems()){
            numbers.add(p.getNumber());
        }*/

       // Set<Long> numbers = lightBox.getItems().stream().map(product -> product.getNumber()).collect(Collectors.toSet());

        Set<Long> numbers = lightBox.getItems().stream().map(new Function<Product, Long>() {
            @Override
            public Long apply(Product product) {
                return product.getNumber();
            }
        }).collect(Collectors.toSet());


        String reservationNumber = purchaseProcess.createReservation(lightBox.getOwner().getNumber());
        lightBoxManagement.reserve(lightBox.getNumber(), numbers, reservationNumber);
        Offer offer = purchaseProcess.calculateOffer(reservationNumber);
        System.out.println(String.format("Cena wybranych zdjęć: %s", offer.getTotalCost()));
        System.out.println("Czy chcesz dokonać zakupu (t/n)? ");
        String decision = scanner.nextLine();
        if (decision.equals("t")){
            PurchaseStatus status = purchaseProcess.confirm(reservationNumber,offer);
            if (status.equals(PurchaseStatus.SUCCESS))
                System.out.println("Dziękujemy za udane zakupy");
            else if (status.equals(PurchaseStatus.NOT_ENOUGH_FOUNDS))
                System.out.println("Nie masz wystarczających środków");
            else
                System.out.println("Przykro mi oferta wygasła");
        }
        else {
            System.out.println("Przykro mi ;(");
        }
    }
}
