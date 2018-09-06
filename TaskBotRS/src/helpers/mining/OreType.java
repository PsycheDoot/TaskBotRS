package helpers.mining;

import org.dreambot.api.methods.map.Area;

public enum OreType {
	copper(ModelColors.CopperOre.getModelColors(), helpers.areas.Mines.CopperMines.getAreas(), "Copper ore"), 
	tin(ModelColors.TinOre.getModelColors(), helpers.areas.Mines.TinMines.getAreas(), "Tin ore"), 
	none;

	private final Area[] Mines;
	private final short[] Colors;
	private final String Name;

	OreType() {
		Colors = new short[0];
		Mines = new Area[0];
		Name = "";
	}

	OreType(short[] colors, Area[] mines, String name) {
		Colors = colors;
		Mines = mines;
		Name = name;
	}

	public static OreType getOreType(String type) {
		switch (type) {
		case "Copper ore":
			return OreType.copper;
		case "Tin ore":
			return OreType.tin;
		default:
			return OreType.none;
		}
	}
	
	public Area[] getMines() {
		return Mines;
	}
	
	public short[] getModelColors() {
		return Colors;
	}
	
	public String getName() {
		return Name;
	}
};
