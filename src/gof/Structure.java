package gof;

public class Structure {
	private int Width; 
	private int Height;
	private int VectorY[];
	private int VectorX[];
	
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
	
	static Structure pond = new Structure (4, 4, new int[]{0,0,1,1,2,2,3,3}, new int[]{1,2,0,3,0,3,1,2});
}
