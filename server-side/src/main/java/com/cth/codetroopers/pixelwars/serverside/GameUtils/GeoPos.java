package com.cth.codetroopers.pixelwars.serverside.GameUtils;


import com.cth.codetroopers.pixelwars.serverside.GameUtils.Exceptions.GeographicalException;

import static java.lang.Math.abs;

/**
 * Created by latiif on 3/22/17.
 */
public class GeoPos {
	private Double latitude;
	private Double longitude;

	public GeoPos() {
		super();
		latitude = null;
		longitude = null;
	}

	public GeoPos(Double latitude, Double longitude) throws GeographicalException {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	void setLatitude(Double newLatitude) throws GeographicalException {
		if (abs(newLatitude) > 90) {
			throw new GeographicalException("Latitude values is not correct: " + newLatitude);
		}
		this.latitude = newLatitude;
	}

	void setLongitude(Double newLongitude) throws GeographicalException {
		if (abs(newLongitude) > 180) {
			throw new GeographicalException("Longitude values is not correct: " + newLongitude);
		}
		this.longitude = newLongitude;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof GeoPos) {
			GeoPos oGeoPos = (GeoPos) obj;

			return oGeoPos.getLatitude().equals(this.getLatitude()) && oGeoPos.getLongitude().equals(this.getLongitude());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("(lat:").append(latitude).append(",long:").append(longitude).append(")");

		return stringBuilder.toString();
	}
}
