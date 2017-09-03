package pl.com.bottega.photostock.sales.model;

/**
 * Created by bartek on 03.09.2017.
 */
public interface LightBoxRepository {

    LightBox get(String number);

    void save(LightBox lightBox);

}
