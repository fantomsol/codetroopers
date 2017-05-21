package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

/**
 * Created by Hugo on 2017-05-04.
 */
public class Helmet implements IArmour {
    private final Integer id= 5;
    private static String name="Helmet";
    private final Integer value = 10;
    private final Integer cost = 50;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getCost() {
        return cost;
    }

    public String getItemType() {
        return "armour";
    }
}
