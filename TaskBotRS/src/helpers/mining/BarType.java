package helpers.mining;

public enum BarType {
	bronze(new String[] { "Copper ore", "Tin ore" }), 
	none;

	private final String[] Ores;

	BarType() {
		Ores = new String[0];
	}

	BarType(String[] ores) {
		Ores = ores;
	}
	
	public String[] getOres() {
		return Ores;
	}
};