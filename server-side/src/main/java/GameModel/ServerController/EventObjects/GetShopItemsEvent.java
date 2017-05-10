package GameModel.ServerController.EventObjects;

/**
 * Created by Hugo on 5/10/17.
 */
public class GetShopItemsEvent {
	public String playerId;

	public GetShopItemsEvent(){

	}

	public GetShopItemsEvent(String playerId){
		this.playerId=playerId;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
}
