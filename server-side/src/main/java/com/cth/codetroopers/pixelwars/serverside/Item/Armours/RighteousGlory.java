package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

/**
 * Created by Hugo on 2017-04-25.
 */
 class RighteousGlory implements IArmour {
    private final Integer id= 2;
    private static String name="Righteous Glory";
    private final Integer value = 45;
    private final Integer cost = 250;


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
