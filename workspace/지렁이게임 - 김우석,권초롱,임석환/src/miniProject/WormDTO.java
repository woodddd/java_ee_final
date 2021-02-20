package miniProject;

enum WormForm{
   SMILE, BEAUTY, MAD,
   GREEN, BLUE, RED, YELLOW, ORANGE, MAGENTA 
}
public class WormDTO {
   private static WormForm face;
   private WormForm color;
   private int WormSize, x, y;
   
	public int getWormSize() {
	
		return WormSize;
	}

	public void setWormSize(int wormSize) {
		WormSize = wormSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public WormForm getFace() {
		return face;
	}

	public void setFace(WormForm face) {
		this.face = face;
	}

	public WormForm getColor() {
		return color;
	}

	public void setColor(WormForm color) {
		this.color = color;
	}
	
	
}