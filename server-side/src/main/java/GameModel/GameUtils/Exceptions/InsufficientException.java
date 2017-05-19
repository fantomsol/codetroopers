package GameModel.GameUtils.Exceptions;

/**
 * Created by latiif on 5/19/17.
 */
public class InsufficientException extends GameException {
	public InsufficientException(String message) {
		super("Insufficient Funds", message);
	}
}
