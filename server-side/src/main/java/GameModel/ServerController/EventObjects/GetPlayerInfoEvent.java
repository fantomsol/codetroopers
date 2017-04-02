package GameModel.ServerController.EventObjects;

/**
 * Created by latiif on 4/1/17.
 */
public class GetPlayerInfoEvent {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public GetPlayerInfoEvent(){

	}

	public GetPlayerInfoEvent(String id) {
		this.id = id;

	}
}
