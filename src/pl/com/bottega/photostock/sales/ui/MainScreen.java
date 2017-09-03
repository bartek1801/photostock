package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

/**
 * Created by bartek on 03.09.2017.
 */
public class MainScreen {

    private LightBoxManagmentScreen lightBoxManagmentScreen;
    private SearchScreen searchScreen;
    private Scanner scanner;

    public MainScreen(Scanner scanner, LightBoxManagmentScreen lightBoxManagmentScreen, SearchScreen searchScreen) {
        this.scanner = scanner;
        this.lightBoxManagmentScreen = lightBoxManagmentScreen;
        this.searchScreen = searchScreen;
    }

    public void show(){
        while (true) {
            showMenu();
            int decision = scanner.nextInt();
            scanner.nextLine();
            switch (decision) {
                case 1:
                    searchScreen.show();
                    break;
                case 2:
                    lightBoxManagmentScreen.show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Sorry, ale nie rozumiem!!!");
            }
        }
    }

    private void showMenu() {
        System.out.println("Witamy w  PHOTOSTOCK!!!");
        System.out.println("1. Wyszukaj Produkty.");
        System.out.println("2. Light Boxy.");
        System.out.println("3. Zakończ.");
        System.out.print("Co chcesz zrobić Panie?");
    }
}
