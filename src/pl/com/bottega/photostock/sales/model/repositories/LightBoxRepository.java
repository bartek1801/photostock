package pl.com.bottega.photostock.sales.model.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;

/**
 * Created by bartek on 03.09.2017.
 */
public interface LightBoxRepository {

    LightBox get(String number);

    void save(LightBox lightBox);

    List<LightBox> getClientLightBoxes(String clientNumber);
}
