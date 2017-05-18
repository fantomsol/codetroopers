package GameModel.GameUtils.Exceptions;

/**
 * Created by latiif on 5/18/17.
 */
public class EmptyLootbox extends GameException {
	public EmptyLootbox(String message) {
		super("Empty Lootbox", message);
	}
}
