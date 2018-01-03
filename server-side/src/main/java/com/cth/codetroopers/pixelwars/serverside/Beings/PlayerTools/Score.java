package com.cth.codetroopers.pixelwars.serverside.Beings.PlayerTools;

/**
 * Created by latiif on 4/9/17.
 */
public class Score {
	private Integer kills,deaths;

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Score(){

	}


	public void increaseDeaths(){
		this.deaths++;
	}

	public void increaseKills(){
		this.kills++;
	}

	public Score(Integer kills, Integer deaths){
		this.kills=kills;
		this.deaths= deaths;
	}


	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();

		sb.append(kills).append("/").append(deaths);

		return sb.toString();
	}
}
