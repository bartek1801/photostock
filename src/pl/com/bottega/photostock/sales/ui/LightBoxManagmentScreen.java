package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagment;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

/**
 * Created by bartek on 03.09.2017.
 */
public class LightBoxManagmentScreen {

    private Scanner scanner;
    private LightBoxManagment lightBoxManagment;
    private AuthenticationManager authenticationManager;

    public LightBoxManagmentScreen(Scanner scanner, LightBoxManagment lightBoxManagment, AuthenticationManager authenticationManager) {
        this.scanner = scanner;
        this.lightBoxManagment = lightBoxManagment;
        this.authenticationManager = authenticationManager;
    }


    public void show() {
        System.out.println("Twoje lajt boxy: ");
        List<LightBox> lightBoxes = lightBoxManagment.getLightBoxes(authenticationManager.getClientNumber());
        LightBoxPresenter lightBoxPresenter = new LightBoxPresenter();
        if (lightBoxes.isEmpty()) {
            System.out.println("Nie masz aktualnie żadnych lajt boxów");
        }
        else {
            int index = 1;
            for (LightBox lightBox : lightBoxes) {
                System.out.println(String.format("%d. %s ", index, lightBox.getName()));
                lightBoxPresenter.show(lightBox);
            }
        }
    }
}
