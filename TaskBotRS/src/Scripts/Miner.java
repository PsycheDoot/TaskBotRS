package Scripts;
import java.util.Arrays;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;

import Task.Task;
import helpers.mining.BarType;
import helpers.mining.OreType;

public class Miner extends Task{

	// Constructors
	public Miner(AbstractScript context) {
		super(context, "Miner");
	}
	public Miner(AbstractScript context, OreType targetOre) {
		super(context, "Miner");
	}
	public Miner(AbstractScript context, OreType targetOre, int n) {
		super(context, "Miner");
	}
	
	private GameObject target = null;
	private OreType targetOre = OreType.copper;
	private BarType targetBar = BarType.bronze;
	
	@Override
	public int execute() {
		targetOre = getTargetOre();
		
		if (targetOre == OreType.none)
			return 0;
		if (hasEnoughOre()) {
			//currentState = State.smelting;
			return 0;
		}

		Area closestMine = targetOre.getMines()[0];		
		
		if (inArea(closestMine)) {
			GameObject n = getNearestOre(targetOre);
			if (n != null) {
				target = n;
				if (Main.getLocalPlayer().getAnimation() == -1 && target.interact("Mine")) {
					while (Main.getLocalPlayer().getAnimation() != -1) {
						Main.sleep(1000);
					}
				}
			} else {
				Main.log("No ore found");
			}
		} else {
			Main.getWalking().walk(closestMine.getRandomTile());
		}
		return 0;
	}

	@Override
	public boolean isComplete() {
		return hasEnoughOre();
	}
	
	public OreType getTargetOre() {
		if (targetBar == BarType.none)
			return OreType.none;
		Main.log(Arrays.toString(targetBar.getOres()));
		int[] oreCount = new int[targetBar.getOres().length];
		int oreSpaces = Main.getInventory().emptySlotCount();
		for (int i = 0; i < targetBar.getOres().length; i++) { //
			oreCount[i] = Main.getInventory().count(targetBar.getOres()[i]);
			oreSpaces += oreCount[i];
		}
		for (int i = 0; i < targetBar.getOres().length; i++) {
			if (oreCount[i] < (oreSpaces / targetBar.getOres().length)) {
				return OreType.getOreType(targetBar.getOres()[i]);
			}
		}
		return OreType.getOreType(targetBar.getOres()[0]);
	}


	public boolean inArea(Area a) {
		return a.contains(Main.getLocalPlayer().getTile());
	}

	private GameObject getNearestOre(OreType type) {
		GameObject ore = Main.getGameObjects().closest(gameObject -> gameObject != null
				&& gameObject.getName().equals("Rocks") && gameObject.getModelColors() != null
				&& gameObject.getModelColors()[0] == type.getModelColors()[0] && gameObject.hasAction("Mine"));
		Main.log(Integer.toString(type.getModelColors()[0]));
		return ore;
	}

	private boolean hasEnoughOre() {
		return Main.getInventory().isFull();
	}
	
	private boolean canSmelt() {
		int oreCount[] = new int[targetBar.getOres().length];
		for (int i = 0; i < targetBar.getOres().length; i++) {
			oreCount[i] = Main.getInventory().count(targetBar.getOres()[i]);
		}
		return true;
	}
	
}
