package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Picture;

/**
 * Created by bartek on 20.08.2017.
 */
public class LightBoxPresenter {

    public void show(LightBox lightBox){
        System.out.println(lightBox.getName());
        System.out.println("-------------------------------------------");
        int i = 1;
        Client client = lightBox.getOwner();
        for (Picture picture : lightBox.getItems()){
            System.out.println(String.format("%s %d. %d | %s",
                    picture.isAvailable() ? "" : "X",
                    i++, picture.getNumber(),
                    picture.calculatePrice(client)
                    )
                );
        }
    }

}
