package com.cth.codetroopers.pixelwars.serverside.Item.Weapons;

/**
 * Created by Hugo on 2017-05-04.
 */
public class Shotgun extends Weapon{
    public Integer getId() {
        return 4;
    }

    public String getName() {
        return "Shotgun";
    }

    public Integer getDamage() {
        return 20;
    }

    public Integer getRange() {
        return 50;
    }

    public Integer getCooldown() {
        return 3;
    }

    public Integer getCost() {
        return 300;
    }
}
