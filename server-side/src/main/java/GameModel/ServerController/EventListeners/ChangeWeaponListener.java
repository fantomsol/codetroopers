package GameModel.ServerController.EventListeners;

import GameModel.ServerController.EventObjects.ChangeWeaponEvent;
import Mediator.IMediator;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;

/**
 * Created by latiif on 5/2/17.
 */
public class ChangeWeaponListener extends EventListener implements DataListener<ChangeWeaponEvent> {
    public ChangeWeaponListener(IMediator mediator) {
        super(mediator);
    }

    public void onData(SocketIOClient socketIOClient, ChangeWeaponEvent changeWeaponEvent, AckRequest ackRequest) throws Exception {
        mediator.changeWeapon(changeWeaponEvent.getPlayerId(),changeWeaponEvent.getWeaponId());

        System.out.println(changeWeaponEvent);
    }
}
