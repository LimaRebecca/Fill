package FillApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * H�nderer fillagring og opplasting for Controller
 *
 * Data lagres som en map, hvor key og value skrives p� hver sin linje.
 * Hver key har en value, og den laveste verdien per spiller lagres
 * Data lastes opp som en som en map, som senere sorteres i FillGame.
 * 
 */

public class FillSaveHandler implements SaveHandler{
	
	public final static String FILENAME = "src/main/java/FillApp/highScores.txt";
	
	@Override
	public Map<String, Integer> load() throws FileNotFoundException {
		try (Scanner scanner = new Scanner(new File(FILENAME))) {
			if (!scanner.hasNextLine()) {
				return null;
			}
			
			Map<String, Integer> highScoreMap = new HashMap<String, Integer>();
			
			while (scanner.hasNext()) {
				String name = scanner.nextLine();
				String scoreString = scanner.nextLine();
				Integer score = Integer.valueOf(scoreString);
				highScoreMap.put(name, score);
			}
					
			return highScoreMap;
		}
	}
	
	
	@Override
	public void save(String name, int score) throws FileNotFoundException {
		Map<String, Integer> highScoreMap = load();
		try (PrintWriter writer = new PrintWriter(FILENAME)) {
					
			if (highScoreMap == null) {
				writer.println(name);
				writer.println(score);
			}
			else {
				boolean hasScoreInMap = false;
			
				for (Map.Entry<String, Integer> entry : highScoreMap.entrySet()) {
					if (name.equals(entry.getKey()) && score < entry.getValue()) {
						highScoreMap.replace(name, entry.getValue(), score);
						hasScoreInMap = true;
					}
					if (name.equals(entry.getKey())) {
						hasScoreInMap = true;
					}
			    }
				
				if (!hasScoreInMap) {
					highScoreMap.put(name, score);
				}
				
				for (Map.Entry<String, Integer> entry : highScoreMap.entrySet()) {
					writer.println(entry.getKey());
					writer.println(entry.getValue());
				}
			}
		}
	}	
}
