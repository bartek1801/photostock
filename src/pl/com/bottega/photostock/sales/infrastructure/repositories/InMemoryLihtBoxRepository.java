package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositories.LightBoxRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartek on 03.09.2017.
 */
public class InMemoryLihtBoxRepository implements LightBoxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();


    @Override
    public LightBox get(String number) {
        if(!REPO.containsKey(number))
            throw new IllegalArgumentException(String.format("LihgtBox %s not found", number));
        return REPO.get(number);
    }

    @Override
    public void save(LightBox lightBox) {
        REPO.put(lightBox.getNumber(), lightBox);
    }
}
