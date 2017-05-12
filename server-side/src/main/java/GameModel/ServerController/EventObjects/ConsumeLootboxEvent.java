package GameModel.ServerController.EventObjects;

import GameModel.GameUtils.GeoPos;

/**
 * Created by latiif on 5/8/17.
 */
public class ConsumeLootboxEvent {

	private String id;
	private GeoPos geoPos;


	public ConsumeLootboxEvent(){

	}

	public ConsumeLootboxEvent(String id, GeoPos geoPos) {
		this.id = id;
		this.geoPos = geoPos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GeoPos getGeoPos() {
		return geoPos;
	}

	public void setGeoPos(GeoPos geoPos) {
		this.geoPos = geoPos;
	}
}
