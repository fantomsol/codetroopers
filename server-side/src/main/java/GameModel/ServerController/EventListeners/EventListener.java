package GameModel.ServerController.EventListeners;

import Mediator.IMediator;

/**
 * Created by latiif on 4/30/17.
 */
public abstract class EventListener {
	protected IMediator mediator;

	EventListener(IMediator mediator){
		this.mediator= mediator;
	}
}
