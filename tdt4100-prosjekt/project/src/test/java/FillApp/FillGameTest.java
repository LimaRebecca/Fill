package FillApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import FillBoard.Board;


public class FillGameTest {
	
	 private Map<String, Integer> highScoreMap = new HashMap<String, Integer>();
	 private FillGame game = new FillGame();
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
		 highScoreMap.put("Hei", 60);
		 highScoreMap.put("lol", 70);
		 highScoreMap.put("reb", 65);
		 
		 board = new Board(4,5);
	 }
	 
	 
	 @Test
	 @DisplayName("Test at highscores printes på riktig format og i riktig rekkefølge.")
	 public void testGetHighScoreString() {
		 String highScoreString = game.getHighScoreString(highScoreMap);
		 String expectedHighScoreString = "High Scores:\nName \tMoves\n";
		 
		 expectedHighScoreString += "Hei 		60\n";
		 expectedHighScoreString += "reb 		65\n";
		 expectedHighScoreString += "lol 		70\n";
		 
		 assertEquals(expectedHighScoreString, highScoreString); 
	 }
	 
	 @Test
	 @DisplayName("Test at checkName() returnerer riktig.")
	 public void testCheckName() {
		 assertTrue(game.checkName("kaffetrakter"));
		 assertTrue(game.checkName("007"));
		 assertFalse(game.checkName("XÆA-12"));
		 assertFalse(game.checkName("!kaffetrakter"));
		 assertFalse(game.checkName("yo"));
	 }
	 
	 @Test
	 @DisplayName("Test at et godkjent navn forkortes til tre tegn.")
	 public void testGetNameForSaving() {
		 assertEquals("lol", game.getNameForSaving("lol"));
		 assertEquals("kaf", game.getNameForSaving("kaffetrakter"));
	 }
	 
	 @Test
	 @DisplayName("Test at levelUp() og levelDown() fungerer og kaster unntak ved feil.")
	 public void testLevel() {
		 assertEquals(1, game.getLevel());
		 
		 assertThrows(IllegalStateException.class, () -> game.levelDown());
		 
		 game.levelUp();
		 assertEquals(2, game.getLevel());
		 
		 game.levelUp();
		 assertThrows(IllegalStateException.class, () -> game.levelUp());
	 }
	 
	 @Test
	 @DisplayName("Test at riktig antall moves returneres.")
	 public void testMoves() {
		 assertEquals(0, game.getMoves());
		 
		 game.move();
		 assertEquals(1, game.getMoves());
	 }
	 
	 @Test
	 @DisplayName("Test at reset() gir riktige startverdier.")
	 public void testReset() {
		 game.move();
		 game.move();
		 game.move();
		 game.reset();
		 assertEquals(0, game.getMoves());
		 assertEquals(1, game.getLevel());
		 
		 game.move();
		 game.move();
		 game.move();
		 game.levelUp();
		 game.move();
		 game.move();
		 game.move();
		 game.reset();
		 assertEquals(0, game.getMoves());
		 assertEquals(1, game.getLevel());	 
	 }
	 
	 @Test
	 @DisplayName("Test at levelen er ferdig.")
	 public void testIsLevelDone() {
		 assertFalse(game.isLevelDone(board));
		 
		 finishGame();
		 assertTrue(game.isLevelDone(board));
	 }
	 
	 @Test
	 @DisplayName("Test at spillet er ferdig ved nivå 3.")
	 public void testIsWon() {
		 assertFalse(game.isWon(board));
		 
		 finishGame();
		 assertFalse(game.isWon(board));
		 
		 game.levelUp();
		 assertFalse(game.isWon(board));
		 
		 game.levelUp();
		 assertTrue(game.isWon(board));
		 
		 game.reset();
		 assertFalse(game.isWon(board));
	 }
	 
	 @Test
	 @DisplayName("Test at kun 5 scores printes.")
	 public void testHighScoreStringLength() {
		 highScoreMap.put("boy", 60);
		 highScoreMap.put("pla", 70);
		 highScoreMap.put("van", 65);
		 highScoreMap.put("mac", 65);
		 
		 String highScoreString = game.getHighScoreString(highScoreMap);
		 assertEquals(7, highScoreString.split("\n").length);
	 }
}