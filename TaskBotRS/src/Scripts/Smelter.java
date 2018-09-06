package Scripts;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import Task.Task;
import helpers.mining.BarType;

public class Smelter extends Task {
	
	public Smelter(AbstractScript context, BarType targetBar) {
		super(context, "Smelter");
	}
	
	private final Area[] SmeltingFurnaces = { new Area(3221, 3256, 3230, 3251) };
	private BarType targetBar = BarType.none;

	@Override
	public int execute() {
		if (targetBar == null)
			return 0;

		//Main.log("smelting");
		Area closestFurnace = SmeltingFurnaces[0];

		if (inArea(closestFurnace) && canSmelt()) {
			GameObject furnace = Main.getGameObjects().closest(go -> go != null && go.hasAction("Smelt"));
			if (furnace != null && furnace.interact("Smelt")) {
				if (true) {
					Widget parent = Main.getWidgets().getWidget(270);
					if (parent != null) {
						int index = 0;
						switch (targetBar) {
						case bronze:
							index = 14;
							break;
						default:
							index = 0;
							break;
						}
						WidgetChild smeltOption = parent.getChild(index);
						if (smeltOption != null) {
							if (smeltOption.interact("Smelt")) {
							}
						}
					}
					Main.sleep(5000);
				}
			}
		} else {
			Main.getWalking().walk(closestFurnace.getRandomTile());
		}
		return 0;
	}

	@Override
	public boolean isComplete() {
		return targetBar == BarType.none || !canSmelt();
	}
	
	private boolean canSmelt() {
		int oreCount[] = new int[targetBar.getOres().length];
		for (int i = 0; i < targetBar.getOres().length; i++) {
			oreCount[i] = Main.getInventory().count(targetBar.getOres()[i]);
		}
		return true;
	}
	
	private boolean inArea(Area a) {
		return a.contains(Main.getLocalPlayer().getTile());
	}

}
