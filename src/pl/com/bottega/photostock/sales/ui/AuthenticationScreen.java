package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

/**
 * Created by bartek on 03.09.2017.
 */
public class AuthenticationScreen {

    private Scanner scanner;
    private AuthenticationManager authenticationManager;

    public AuthenticationScreen(Scanner scanner, AuthenticationManager authenticationManager) {
        this.scanner = scanner;
        this.authenticationManager = authenticationManager;
    }

    public void show(){
        while (true) {
            System.out.print("Podaj login klienta: ");
            String login = scanner.nextLine();
            if (authenticationManager.authenticate(login))
                return;
            else
                System.out.println("Nieprawidłowy login");
        }
    }
}
