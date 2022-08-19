package FillBoard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
	
	private Board board;
	
	private void finishGame() {
		board.levelOne();
		board.moveLeft();
		board.moveLeft();
		board.moveUp();
		board.moveRight();
		board.moveUp();
		board.moveRight();
		board.moveDown();
		board.moveRight();
		board.moveUp();
		board.moveUp();
		board.moveLeft();
		board.moveUp();
		board.moveLeft();
		board.moveDown();
		board.moveLeft();
	}
	
	@BeforeEach
	public void setUp() {
		board = new Board(4,5);
	}
	
	@Test
	@DisplayName("Test at konstruktør ikke tar inn negative verdier.")
	public void testConstructor() {
		assertEquals(4, board.getWidth());
		assertEquals(5, board.getHeight());
		
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				assertTrue(board.getBlock(x, y).isAir());
			}	
		}
		
		assertThrows(IllegalArgumentException.class,() -> board = new Board(-1,-1));
	}
	
	@Test
	@DisplayName("Test at getBlock() returnerer riktig block og kaster unntak ved feil input.")
	public void testGetBlock() {
		assertTrue(board.getBlock(1, 1).isAir());
		
		board.getBlock(2, 2).setFill();
		assertTrue(board.getBlock(2, 2).isFill());
		
		assertThrows(IllegalArgumentException.class,() -> board.getBlock(5, 4));
	}
	
	@Test
	@DisplayName("Test at allBlanksFilled() returnerer true når spillet er ferdig og false ellers.")
	public void testAllBlanksFilled() {
		assertFalse(board.allBlanksFilled());
		
		finishGame();
		assertTrue(board.allBlanksFilled());
		
		assertThrows(IllegalArgumentException.class, () -> board.moveRight());
	}
	
	@Test
	@DisplayName("Test at hver level settes riktig.")
	public void testSetLevel() {
		board.levelOne();
		assertTrue(board.getBlock(0, 0).isWall());
		assertTrue(board.getBlock(3, 0).isWall());
		assertTrue(board.getBlock(0, 2).isWall());
		assertTrue(board.getBlock(3, 4).isWall());
		assertTrue(board.getBlock(2, 4).isFill());
		
		assertThrows(IllegalArgumentException.class, () -> board.levelTwo());
		
		board = new Board(5,5);
		board.levelTwo();
		assertTrue(board.getBlock(3, 1).isWall());
		assertTrue(board.getBlock(0, 3).isWall());
		assertTrue(board.getBlock(2, 4).isWall());
		assertTrue(board.getBlock(3, 4).isWall());
		assertTrue(board.getBlock(4, 4).isWall());
		assertTrue(board.getBlock(1, 0).isFill());
	}
	
	@Test
	@DisplayName("Test at fill beveger seg riktig og kaster unntak ved ulovlig bevegelse.")
	public void testMove() {
		board.levelOne();
		
		// Bevege seg inn i vegg til høyre
		assertThrows(IllegalArgumentException.class, () -> board.moveRight());
		//Bevege seg ned og ut av brettet
		assertThrows(IllegalArgumentException.class, () -> board.moveDown());
		
		board.moveLeft();
		assertTrue(board.getBlock(1, 4).isFill());
		
		board.moveLeft();
		assertTrue(board.getBlock(0, 4).isFill());
		
		board.moveUp();
		assertTrue(board.getBlock(0, 3).isFill());
		
		board.moveRight();
		assertTrue(board.getBlock(1, 3).isFill());
		
		//Bevege seg ned i seg selv, men ikke den forrige posisjonen
		assertThrows(IllegalArgumentException.class, () -> board.moveDown());
		
		//Bevege seg tilbake der man kom fra
		board.moveLeft();
		assertFalse(board.getBlock(1, 3).isFill());	
	}
}
