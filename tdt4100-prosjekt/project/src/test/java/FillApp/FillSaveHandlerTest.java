package FillApp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(OrderAnnotation.class)
public class FillSaveHandlerTest {
	
//	highScores.txt settes helt tom (kun en tom linje) før kjøring av testen for å kunne 
//	teste de ulike delene ved lasting og lagring
	
	private SaveHandler saveHandler = new FillSaveHandler();
	private Map<String, Integer> highScoreMap = new HashMap<String, Integer>();
	
	
	@BeforeEach
	public void setUp() {
		 highScoreMap.put("Hei", 60);
		 highScoreMap.put("lol", 70);
		 highScoreMap.put("reb", 65);
	}
	
	@Test
	@Order(1)
	@DisplayName("Test at load() returnerer null ved tom fil.")
	public void testLoadFromEmpty() {
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			assertNull(loadedMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("Test at lagring til tom fil fungerer.")
	public void testSaveToEmpty() {
		try {
			saveHandler.save("Hei", 60);
			saveHandler.save("lol", 70);
			saveHandler.save("reb", 65);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			
			assertTrue(loadedMap.containsKey("Hei"));
			assertTrue(loadedMap.containsKey("lol"));
			assertTrue(loadedMap.containsKey("reb"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	@DisplayName("Test at load() returnerer riktig map.")
	public void testLoad() {
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			
			assertTrue(loadedMap.containsKey("Hei"));
			assertTrue(loadedMap.containsKey("lol"));
			assertTrue(loadedMap.containsKey("reb"));
			
			assertEquals(highScoreMap.get("Hei"), loadedMap.get("Hei"));
			assertEquals(highScoreMap.get("lol"), loadedMap.get("lol"));
			assertEquals(highScoreMap.get("reb"), loadedMap.get("reb"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(4)
	@DisplayName("Test at ny score kan lagres.")
	public void testSaveNewScore() {
		try {
			saveHandler.save("kaf", 67);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			assertTrue(loadedMap.containsKey("kaf"));
			assertEquals(67, loadedMap.get("kaf"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	@DisplayName("Test at forbedret score overskriver gammel score.")
	public void testSaveImprovedScore() {
		try {
			saveHandler.save("kaf", 65);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			assertTrue(loadedMap.containsKey("kaf"));
			assertEquals(65, loadedMap.get("kaf"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(6)
	@DisplayName("Test at verre score ikke overskiver eldre og bedre score.")
	public void testSaveWorseScore() {
		try {
			saveHandler.save("kaf", 75);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Map<String, Integer> loadedMap = saveHandler.load();
			assertTrue(loadedMap.containsKey("kaf"));
			assertEquals(65, loadedMap.get("kaf"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
