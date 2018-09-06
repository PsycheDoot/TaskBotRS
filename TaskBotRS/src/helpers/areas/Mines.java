package helpers.areas;

import org.dreambot.api.methods.map.Area;

public enum Mines {
	CopperMines(new Area[] {
			new Area(3221, 3151, 3233, 3142)
	}),
	TinMines(new Area[] {
			new Area(3221, 3151, 3233, 3142)
	});
	
	private final Area[] Areas;
	
	Mines(Area[] areas) {
		Areas = areas;
	}
	
	public Area[] getAreas() {
		return Areas;
	}
}
