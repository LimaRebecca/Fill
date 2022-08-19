package FillBoard;

import java.util.ArrayList;

public class Board {
	
	private int height;
	private int width;
	private Block[][] board;
	private ArrayList<Block> fill = new ArrayList<>();
	
	
	
	public Board(int width, int height) {
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Width and height cannot be negative");
		}
		
		this.width = width;
		this.height = height;
		
		this.board = new Block[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[x][y] = new Block(x,y);
				
			}	
		}
	}
	
	public Block getBlock(int x, int y) {
		if (!checkIsBlock(x, y)) {
			throw new IllegalArgumentException("Out of range");
		}
		return board[x][y];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public void moveUp() {
		move(fill.get(0).getX(),fill.get(0).getY() - 1);
	}
	
	public void moveDown() {
		move(fill.get(0).getX(),fill.get(0).getY() + 1);
	}
	public void moveLeft() {
		move(fill.get(0).getX() - 1,fill.get(0).getY());
	}
	
	public void moveRight() {
		move(fill.get(0).getX() + 1,fill.get(0).getY());
	}

	public boolean allBlanksFilled() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (getBlock(x, y).isAir()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void levelOne() {
		getBlock(0, 0).setWall();
		getBlock(3, 0).setWall();
		getBlock(0, 2).setWall();
		getBlock(3, 4).setWall();
		initiateFill(2, 4);
	}
	
	public void levelTwo() {
		getBlock(3, 1).setWall();
		getBlock(0, 3).setWall();
		getBlock(2, 4).setWall();
		getBlock(3, 4).setWall();
		getBlock(4, 4).setWall();
		initiateFill(1, 0);
	}
	
	public void levelThree() {
		getBlock(4, 1).setWall();
		getBlock(0, 3).setWall();
		getBlock(2, 4).setWall();
		initiateFill(5, 3);
	}
	
	private boolean checkIsBlock(int x, int y) {
		if (x >= width || y >= height || x < 0 || y < 0 ) {
			return false;
		}
		return true;
	}
	
	private boolean canMove(int x, int y) {
		if (!checkIsBlock(x,y)) {
			return false;
		}
		return (getBlock(x, y).isAir() || (getBlock(x, y).isFill() && getBlock(x, y).equals(fill.get(1))));
	}
	
	private void move(int x, int y) {
		if (allBlanksFilled()) {
			throw new IllegalArgumentException();
		}
		else if (canMove(x, y)) {
			if (getBlock(x, y).isFill() && getBlock(x, y).equals(fill.get(1))) {
				fill.get(0).setAir();
				removeFrontFill();
			}
			movementTo(x, y);
		}
		else{
			throw new IllegalArgumentException();
		}
	}
	
	private void initiateFill(int x, int y) {
		if (!checkIsBlock(x, y)) {
			throw new IllegalArgumentException("out of range");
		}
		getBlock(x, y).setFill();
		fill.add(getBlock(x, y));
	}
	
	private void removeFrontFill() {
		fill.remove(0);
		ArrayList<Block> fill2 = new ArrayList<>();
		for (int i = 1; i < fill.size(); i++) {
			fill2.add(fill.get(i));
			
		} 
		fill = fill2;
	}
	
	private void movementTo(int x, int y) {
		getBlock(x, y).setFill();
		fill.add(0, getBlock(x,y));
	}
}
