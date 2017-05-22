package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

/**
 * Created by Hugo on 2017-05-04.
 */
 class Kevlar implements IArmour {
    private final Integer id= 4;
    private static String name="Kevlar";
    private final Integer value = 250;
    private final Integer cost = 600;


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
