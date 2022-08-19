package FillApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class HighScoreComparatorTest {
	
	private HighScoreComparator comparator;
	private Map<String, Integer> highScoreMap = new HashMap<String, Integer>();
	private Map<String, Integer> emptyMap = new HashMap<String, Integer>();
	
	 @BeforeEach
	 public void setUp() {
		 highScoreMap.put("Hei", 60);
		 highScoreMap.put("lol", 70);
		 highScoreMap.put("reb", 65);
		 
		 comparator = new HighScoreComparator(highScoreMap);
	 }
	 
	 @Test
	 @DisplayName("Test at konstruktøren kaster unntak.")
	 public void testConstructor() {
		 assertThrows(IllegalArgumentException.class,() -> comparator = new HighScoreComparator(null));
		 
		 assertThrows(IllegalArgumentException.class,() -> comparator = new HighScoreComparator(emptyMap));
	 }

	 @Test
	 @DisplayName("Test at compare() returnerer riktig verdi.")
	 public void testCompare() {
		 assertEquals(-1, comparator.compare("Hei", "lol"));
		 assertEquals(-1, comparator.compare("Hei", "reb"));
		 assertEquals(-1, comparator.compare("reb", "lol"));
		 
		 assertEquals(1, comparator.compare("lol", "Hei"));
		 assertEquals(1, comparator.compare("reb", "Hei"));
		 assertEquals(1, comparator.compare("lol", "reb"));
	 } 
}
