package FillBoard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlockTest {
	
	Block block;
	
	
	@BeforeEach
	public void setUp() {
		block = new Block(0, 0);
	}

	@Test
	@DisplayName("Test at konstruktøren ikke tar inn negative verdier, og at x og y settes riktig.")
	public void testConstructor() {
		assertEquals(0, block.getX());
		assertEquals(0, block.getY());
		
		block = new Block(2, 4);
		assertEquals(2, block.getX());
		assertEquals(4, block.getY());
		
		assertThrows(IllegalArgumentException.class, () -> block = new Block(-1, -1));
	}
	
	@Test
	@DisplayName("Test at typene settes riktig.")
	public void testSetType() {
		assertTrue(block.isAir());
		
		block.setWall();
		assertTrue(block.isWall());
		
		block.setFill();
		assertTrue(block.isFill());
		
		block.setAir();
		assertTrue(block.isAir());
	}
}
