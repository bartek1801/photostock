package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

/**
 * Created by bartek on 20.08.2017.
 */
public interface PictureRepository {

    //pobiera obiekt o identyfikatorze
    Picture get(Long number);

    //zapis nowego lub aktualizacja istiejącego
    void save(Picture picture);

    Optional<Picture> getOptional(Long number); //TODO poczytaj o optional

}
