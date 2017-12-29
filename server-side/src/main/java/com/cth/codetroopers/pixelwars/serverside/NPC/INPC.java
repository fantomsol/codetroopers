package com.cth.codetroopers.pixelwars.serverside.NPC;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;

public interface INPC {

	void updatePos(GeoPos newPos);

	void setAvatar(Avatar a);

	void setIsAlive(boolean life);

	Avatar getAvatar();

	GeoPos getGeoPos();

	String getID();

	Boolean getIsAlive();

}
