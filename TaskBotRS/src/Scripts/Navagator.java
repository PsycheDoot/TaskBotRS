package Scripts;

import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.script.AbstractScript;

import Task.Task;

public class Navagator extends Task {

	public Navagator(AbstractScript context, Tile t) {
		super(context, "Navagator");
		targetTile = t;
	}
	
	private Tile targetTile = null;

	@Override
	public int execute() {
		if (targetTile != null && Main.getWalking().walk(targetTile)) {
		}
		return 0;
	}

	@Override
	public boolean isComplete() {
		return ( targetTile == null || Main.getLocalPlayer().distance(targetTile) < 0);
	}
	
}
