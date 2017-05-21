package com.cth.codetroopers.pixelwars.serverside.Item.Armours;

import com.cth.codetroopers.pixelwars.serverside.Item.Item;

/**
 * Created by latiif on 3/22/17.
 */
public interface IArmour extends Item {

	public Integer getId();

	public String getName();

	//The value of the Armours
	public Integer getValue();



}
