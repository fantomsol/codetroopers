package com.cth.codetroopers.pixelwars.serverside.Player.PlayerUtils;

/**
 * Created by latiif on 4/9/17.
 */
public class Score {
	private Integer kills,deaths;


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
