package com.cth.codetroopers.pixelwars.serverside.GameUtils;

/**
 * Created by latiif on 3/30/17.
 */
public final class GeoDistance {
	public static Double getDistance(GeoPos pos1,GeoPos pos2){
		double theta = pos1.getLongitude() - pos2.getLongitude();
		double dist
				= Math.sin(deg2rad(pos1.getLatitude())) * Math.sin(deg2rad(pos2.getLatitude()))
				+ Math.cos(deg2rad(pos1.getLatitude())) * Math.cos(deg2rad(pos2.getLatitude()))
				* Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1609.344;

		return (dist);
	}





	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
