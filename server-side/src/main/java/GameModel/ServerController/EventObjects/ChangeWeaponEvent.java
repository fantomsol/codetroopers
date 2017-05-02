package GameModel.ServerController.EventObjects;

/**
 * Created by latiif on 5/2/17.
 */
public class ChangeWeaponEvent {

    public String playerId;
    public Integer weaponId;

    public ChangeWeaponEvent(){
    }

    public ChangeWeaponEvent(String playerId,Integer weaponId){
        setPlayerId(playerId);
        setWeaponId(weaponId);
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
    }

    @Override
    public String toString(){
        return getPlayerId()+" wants to change weapon to "+getWeaponId();
    }
}
