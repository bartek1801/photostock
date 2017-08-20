package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.PictureRepository;

import java.util.*;

/**
 * Created by bartek on 20.08.2017.
 */
public class InMemoryPictureRepository implements PictureRepository {

    private static final Map<Long, Picture> REPO = new HashMap<>();//wspólne dla wszystkich obiektów tej klasy

    //statyczny blok inicjujący, mamy w nim dostęp do zmiennych statycznych czyli REPO
    //wykona się w momencie załadowania klasy
    //konstruktor dla klasy
    static {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Picture p1 = new Picture(1l, tags, Money.valueOf(10) );
        Picture p2 = new Picture(2L, tags, Money.valueOf(5));
        Picture p3 = new Picture(3L, tags, Money.valueOf(15));
        REPO.put(1L, p1);
        REPO.put(2L, p2);
        REPO.put(3L, p3);
    }

    @Override
    public Picture get(Long number) {
        if (!REPO.containsKey(number))
            throw new IllegalArgumentException("No such object in repository");
        return REPO.get(number);
    }

    @Override
    public Optional<Picture> getOptional(Long number) {
        if (REPO.containsKey(number))
            return Optional.of(REPO.get(number));
        else
            return Optional.empty();
    }

    @Override
    public void save(Picture picture) {
        REPO.put(picture.getNumber(), picture);
    }
}
