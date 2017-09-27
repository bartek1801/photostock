package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LightBoxManagementScreen {

    private Scanner scanner;
    private LightBoxManagement lightBoxManagement;
    private AuthenticationManager authenticationManager;
    private List<LightBox> lightBoxes;
    private LightBox lightBox;
    private AddProductToLightBoxScreen addProductToLightBoxScreen;
    private PurchaseLightBoxScreen purchaseLightBoxScreen;

    public LightBoxManagementScreen(Scanner scanner, LightBoxManagement lightBoxManagement,
                                    AuthenticationManager authenticationManager, AddProductToLightBoxScreen addProductToLightBoxScreen, PurchaseLightBoxScreen purchaseLightBoxScreen) {
        this.scanner = scanner;
        this.lightBoxManagement = lightBoxManagement;
        this.authenticationManager = authenticationManager;
        this.addProductToLightBoxScreen = addProductToLightBoxScreen;
        this.purchaseLightBoxScreen = purchaseLightBoxScreen;
    }

    public void show() {
        System.out.println("Twoje lajt boksy:");
        lightBoxes = lightBoxManagement.getLightBoxes(authenticationManager.getClientNumber());
        if (lightBoxes.isEmpty())
            System.out.println("Nie masz aktualnie żadnych lajt boksów");
        else {
            int index = 1;
            for (LightBox lightBox : lightBoxes)
                System.out.println(String.format("%d. %s", index++, lightBox.getName()));
        }
        lightBoxActions();
    }

    private void lightBoxActions() {
        Menu menu = new Menu(scanner);
        menu.setTitleLabel("LightBox Menu");
        menu.addItem("Dodaj nowy LightBox", () -> addNewLightBox());
        menu.addItem("Wyświetl LightBox.", () -> {
            if(lightBoxes.size() > 0) {
                showLightBox();
            }
        });
        menu.setLastItemLabel("Wróć do menu");
        menu.show();
    }

    private void showLightBox() {
        System.out.println("Podaj index Lightbox'a: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        lightBox = lightBoxes.get(index - 1);
        LightBoxPresenter presenter = new LightBoxPresenter();
        presenter.show(lightBox);
        selectedLightBoxActions();
    }

    private void selectedLightBoxActions() {
        Menu menu = new Menu(scanner);
        menu.setTitleLabel("Opcje LightBox");
        menu.addItem("Dodaj produkt do LightBox'a", new Runnable() {
            @Override
            public void run() {
                addProductToLightBoxScreen.show(lightBox);
            }
        });
        menu.addItem("Kup wszystkie produkty LightBoxa", () -> {
            try {
                purchaseLightBoxScreen.show(lightBox);
            } catch (IOException e) {
                System.out.println("Nie znaleziono pliku");
            }
        });
        menu.setLastItemLabel("Wróć do poprzedniego menu.");
        menu.show();
    }

    private void showLightBoxMenu() {
        System.out.println("1. Dodaj produkt do LightBox'a");
        System.out.println("2. Kup produkty z LightBoxa");
        System.out.println("3. Wróć do poprzedniego menu.");
        System.out.println("Co chcesz zrobić?");
    }

    private void addNewLightBox() {
        System.out.println("Podaj nazwę nowego LighBox'a: ");

        String name = scanner.nextLine();
        String clientNumber = authenticationManager.getClientNumber();

        lightBoxManagement.create(clientNumber, name);

        lightBoxes = lightBoxManagement.getLightBoxes(clientNumber);

        System.out.println(String.format("LightBox %s został dodany.", name));
    }

   /* private void showMenu() {
        System.out.println("1. Dodaj nowy LightBox.");
        if (lightBoxes.size() > 0)
            System.out.println("2. Wyświetl LightBox.");
        System.out.println("3. Wróć do menu.");
        System.out.println("Co chcesz zrobić?");
    }*/
}