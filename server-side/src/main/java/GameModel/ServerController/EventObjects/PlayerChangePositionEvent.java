package GameModel.ServerController.EventObjects;

/**
 * Created by latiif on 3/20/17.
 */
public class PlayerChangePositionEvent {

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLang() {
		return lang;
	}

	public void setLang(double lang) {
		this.lang = lang;
	}

	public String  getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	double lat, lang;

	String id;

	public PlayerChangePositionEvent(){

	}

	public PlayerChangePositionEvent(String id, double latitude, double langitude){
		super();
		this.id=id;
		this.lang=langitude;
		this.lat=latitude;
	}




}
