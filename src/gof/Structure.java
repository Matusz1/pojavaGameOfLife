package gof_zooming;

import java.util.HashMap;
import java.util.Map;

public class Structure {
	
	// Currently not used dimensions that a structure fits in
	private int Width; 
	private int Height;
	// X and Y coordinates of a vector attached to the upper left cell pointing to a cell that should be alive
	private int VectorY[];
	private int VectorX[];
	
	// Currently the only constructor
	public Structure (int Width, int Height, int VectorY[], int VectorX[]) {
		this.Height = Height;
		this.Width = Width;
		
		this.VectorY = new int[VectorY.length];
		for (int i = 0; i < VectorY.length; i++) 
			this.VectorY[i] = VectorY[i];
		
		this.VectorX = new int[VectorX.length];
		for (int i = 0; i < VectorX.length; i++) 
			this.VectorX[i] = VectorX[i];
	}
	
	public int getH () {
		return Height;
	}
	
	public int getW () {
		return Width;
	}
	
	public int[] getVX() {
		return VectorX;
	}
	
	public int[] getVY() {
		return VectorY;
	}
	
	
	// Structures Library that can and should be enlarged
	static private Map<String, Structure> structuresMap;
	static {
		structuresMap = new HashMap<>();
		
		//Basics - still - oscilling 
		structuresMap.put ("Pond", new Structure (4, 4, 
				new int[]{-1,-1,0,0,1,1,2,2},
				new int[]{0,1,-1,2,-1,2,0,1}));
		
		//Long living
		structuresMap.put ("Diehard", new Structure (3, 8,
				new int[] {-2,-1,-1,0,0,0,0},
				new int[] {1,-5,-4,-4,0,1,2}));
		structuresMap.put ("Acorn", new Structure (3, 7,
				new int[] {-1,0,1,1,1,1,1},
				new int[] {-2,0,-3,-2,1,2,3}));
		
		//Spaceships
		structuresMap.put ("Glider", new Structure (3, 3, 
				new int[] {-2,-1,0,0,0}, 
				new int[] {-1,0,-2,-1,0}));
		structuresMap.put("Light-weight spaceship", new Structure (4, 5,
				new int[] {-2,-2,-1,0,0,1,1,1,1},
				new int[] {-3,0,1,-3,1,-2,-1,0,1}));
		structuresMap.put("Middle-weight spaceship", new Structure (5, 6,
				new int[] {-4,-3,-3,-2,-1,-1,0,0,0,0,0}, 
				new int[] {0,-2,2,3,-2,3,-1,0,1,2,3}));
		structuresMap.put("Heavy-weight spaceship", new Structure (5, 7,
				new int[] {-3,-3,-2,-2,-1,0,0,1,1,1,1,1,1}, 
				new int[] {0,1,-2,3,4,-2,4,-1,0,1,2,3,4}));
		
		//Guns
		structuresMap.put ("Glider Spawner", new Structure (9, 36, 
				new int[] {-2,-1,-1,0,0,0,0,0,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,5,5,6,6}, 
				new int[] {10,8,10,-2,-1,6,7,20,21,-3,1,6,7,20,21,-14,-13,-4,2,6,7,-14,-13,-4,0,2,3,8,10,-4,2,10,-3,1,-2,-1}));
	}
	
	
	public static Map<String, Structure> getStructuresMap () {
		return structuresMap;
	}

}
