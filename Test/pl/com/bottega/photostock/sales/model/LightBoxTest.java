package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bartek on 20.08.2017.
 */
public class LightBoxTest {

    @Test(expected = IllegalStateException.class)
    public void add() {
        //given
        Client client = new StandardClient("Jan Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "02-298"));
        LightBox newLightBox = new LightBox(client, "name of LightBox");
        Set<String> tags = new HashSet<>();
        Product p1 = new Picture(1l, tags, Money.valueOf(10));
        Product p2 = new Picture(2L, tags, Money.valueOf(5));

        //when
        newLightBox.add(p1);
        newLightBox.add(p1);
        //newLightBox.add(p2); //rzuca IllegalArgumentExeption

    }


}