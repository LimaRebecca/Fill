package FillBoard;

public class Block {
	
	
	private char type = ' ';
	private int x;
	private int y;
	
	public Block(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x and y cannot be negative");
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setWall() {
		type = 'W';
	}
	
	public void setAir() {
		type = ' ';
	}
	
	public void setFill() {
		type = 'F';
	}
	
	public boolean isWall() {
		return type == 'W';
	}
	
	public boolean isAir() {
		return type == ' ';
	}
	
	public boolean isFill() {
		return type == 'F';
	}

}
