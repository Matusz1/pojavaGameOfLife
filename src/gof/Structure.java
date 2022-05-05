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
	
	// For rotation purposes
	// Zero is in some way north direction and the directions go clockwise. But see: getRotationCoordinate
	private static int direction = 0;
	private static boolean reverse = false;
	
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
	
	/* After making separate switches for X and Y coordinate 
	 * one realizes that both X and Y coordinates of the drawing vector are found in here. 
	 * Y coordinates are trailing by 1 the X coordinates 
	 * See: highlight and drawStructure at GamePanel Class*/ 
	public static int getRotationCoordinate(int dir, int i) {
		switch ((4+dir)%4) // In java % operator returns negative numbers
		{
		case 0:
			if (!reverse)
				return +structuresMap.get(StructuresDialog.getValue()).getVX()[i];
			else
				return -structuresMap.get(StructuresDialog.getValue()).getVX()[i];
		case 1:
				return -structuresMap.get(StructuresDialog.getValue()).getVY()[i];
		case 2:
			if (!reverse)
				return -structuresMap.get(StructuresDialog.getValue()).getVX()[i];
			else
				return +structuresMap.get(StructuresDialog.getValue()).getVX()[i];
		case 3:
				return +structuresMap.get(StructuresDialog.getValue()).getVY()[i];
		default: 
				return 0;
		}
	}
	
	public static int getDirection() {
		return direction;
	}

	public static void resetDirection() {
		direction = 0;
	}

	public static void nextDirection () {
		direction++;
		direction %= 4;
	}

	public static void reverseStructure() {
		reverse = !reverse;
	}
	
	
	
	
	/*********************************************************
	 *					STRUCTURES LIBRARY                   *
	 *********************************************************/
	
	
	public static Map<String, Structure> getStructuresMap () {
		return structuresMap;
	}
	
	// Structures Library that can and should be enlarged
	static private Map<String, Structure> structuresMap;
	static {
		structuresMap = new HashMap<>();
		
		//Oscillators
		structuresMap.put ("Pond", new Structure (4, 4, 
				new int[]{-1,-1,0,0,1,1,2,2},
				new int[]{0,1,-1,2,-1,2,0,1}));
		structuresMap.put ("Pulsar", new Structure (13, 13, 
				new int[]{6,6,6,4,4,3,3,2,2,1,1,1,-6,-6,-6,-4,-4,-3,-3,-2,-2,-1,-1,-1,-6,-6,-6,-4,-4,-3,-3,-2,-2,-1,-1,-1,6,6,6,4,4,3,3,2,2,1,1,1},
				new int[]{2,3,4,1,6,1,6,1,6,2,3,4,-2,-3,-4,-1,-6,-1,-6,-1,-6,-2,-3,-4,2,3,4,1,6,1,6,1,6,2,3,4,-2,-3,-4,-1,-6,-1,-6,-1,-6,-2,-3,-4}));
		structuresMap.put ("I-Column", new Structure (16, 3, 
				new int[]{-7,-6,-5,-5,-5,-2,-2,-2,-1,0,1,2,3,3,3,6,6,6,7,8},
				new int[]{0,0,-1,0,1,-1,0,1,0,0,0,0,-1,0,1,-1,0,1,0,0}));
		structuresMap.put ("Gabriel's p138", new Structure (15, 15, 
				new int[]{-7,-7,-7,-6,-6,-5,-5,-4,-4,-4,-4,-3,-3,-2,-2,-2,-2,-1,-1,-1,0,0,0,0,1,1,1,2,2,2,2,3,3,4,4,4,4,5,5,6,6,7,7,7},
				new int[]{0,1,2,-1,2,0,4,-5,1,2,3,-4,2,-7,-6,-4,-3,-7,-4,6,-7,-5,5,7,-6,4,7,3,4,6,7,-2,4,-3,-2,-1,5,-4,0,-2,1,-2,-1,0}));
		
		//Long living
		structuresMap.put ("The R-pentomino", new Structure (3, 3,
				new int[] {-1,-1,0,0,1},
				new int[] {0,1,-1,0,0}));
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
		structuresMap.put ("Simkin glider gun", new Structure (21, 33, 
				new int[] {-10,-10,-10,-10,-9,-9,-9,-9,-7,-7,-6,-6,-1,-1,-1,-1,0,0,1,1,1,1,2,2,2,2,2,2,3,7,7,8,9,9,9,10}, 
				new int[] {-21,-20,-14,-13,-21,-20,-14,-13,-17,-16,-17,-16,1,2,4,5,0,6,0,7,10,11,0,1,2,6,10,11,5,-1,0,-1,0,1,2,2}));
		structuresMap.put ("Gosper glider gun", new Structure (9, 36, 
				new int[] {-2,-1,-1,0,0,0,0,0,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,3,3,4,4,4,5,5,6,6}, 
				new int[] {10,8,10,-2,-1,6,7,20,21,-3,1,6,7,20,21,-14,-13,-4,2,6,7,-14,-13,-4,0,2,3,8,10,-4,2,10,-3,1,-2,-1}));
	}
	
}
