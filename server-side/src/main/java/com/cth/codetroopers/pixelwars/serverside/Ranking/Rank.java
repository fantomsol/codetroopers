package com.cth.codetroopers.pixelwars.serverside.Ranking;

import static com.cth.codetroopers.pixelwars.serverside.Ranking.Ranks.*;

/**
 * Created by lumo on 02/05/17.
 */
public abstract class Rank {


    public static Ranks getRank(int exp) {
        if (exp < 2500) {
            return PRIVATE;
        } else if (exp < 5000) {
            return SERGEANT;
        } else if (exp < 7500) {
            return MAJOR;
        } else if (exp < 10000) {
            return GENERAL;
        } else {
            return JOACHIMVONHACHT;
        }
    }





}
