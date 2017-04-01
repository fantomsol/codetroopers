package GameModel.Player;

import org.redisson.spring.cache.NullValue;

import static java.lang.Math.abs;

/**
 * Created by latiif on 3/22/17.
 */
public class GeoPos {
	private Double latitude;
	private Double longitude;

	public GeoPos(){
		super();
		latitude=null;
		longitude=null;
	}

	public GeoPos(Double latitude, Double longitude){
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public Double getLatitude(){
		return this.latitude;
	}

	public Double getLongitude(){
		return this.longitude;
	}

	void setLatitude(Double newLatitude){
		if (abs(newLatitude)>90){
			throw new IllegalArgumentException("Latitude values is not correct: "+newLatitude);
		}
		this.latitude=newLatitude;
	}

	void setLongitude(Double newLongitude){
		if (abs(newLongitude)>180) {
			throw new IllegalArgumentException("Longitude values is not correct: " + newLongitude);
		}
			this.longitude=newLongitude;
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder= new StringBuilder();

		stringBuilder.append("(lat:").append(latitude).append(",long:").append(longitude).append(")");

		return stringBuilder.toString();
	}
}
