package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

import com.cth.codetroopers.pixelwars.serverside.Item.Item;

/**
 * Created by latiif and Hugo on 3/22/17.
 */
public interface IArmour extends Item {

	Integer getId();

	String getName();

	//The value of the Armours
	Integer getValue();



}
