/**
 * Created by latiif on 3/20/17.
 */
public class MovedEvent {

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

	double lat, lang;

	public MovedEvent(){

	}

	public MovedEvent(double latitude,double langitude){
		super();
		this.lang=langitude;
		this.lat=latitude;
	}




}
