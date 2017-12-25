package com.cth.codetroopers.pixelwars.serverside.NPC;

import com.cth.codetroopers.pixelwars.serverside.GameUtils.GeoPos;
import com.cth.codetroopers.pixelwars.serverside.Player.Avatar.Avatar;

public interface INPC {

	void updatePos(GeoPos newPos);

	Avatar getAvatar();

	void setAvatar(Avatar a);

	GeoPos getGeoPos();

}
