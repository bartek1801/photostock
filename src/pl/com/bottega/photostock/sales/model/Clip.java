package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;

/**
 * Created by bartek on 20.08.2017.
 */
public class Clip extends AbstractProduct {

    private static Long length;

    public Clip(Long number, Money price, Long length, Boolean active ) {
        super(number, price, active);
        this.length = length;
    }

    public Clip(Long number, Money price, Long length) {
        super(number, price);
        this.length = length;
        this.active = true;
    }

    public static Long getLength() {
        return length;
    }
}
