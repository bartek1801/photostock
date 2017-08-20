package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bartek on 19.08.2017.
 */
public enum ClientStatus{

    STANDARD(0),
    VIP(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    public int discountPercent;

    ClientStatus(int discount){
        this.discountPercent = discount;
    }

    public int getDiscount(){
        return discountPercent;
    }

}
