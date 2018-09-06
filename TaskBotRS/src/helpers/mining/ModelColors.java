package helpers.mining;

public enum ModelColors {
	None		(new short[] {}),
	CopperOre	(new short[] {4645}),
	TinOre		(new short[] {53});
	
	private final short[] modelColors;
	
	ModelColors(short[] colors) {
		modelColors = colors;
	}
	
	public short[] getModelColors() {
		return modelColors;
	}
}
