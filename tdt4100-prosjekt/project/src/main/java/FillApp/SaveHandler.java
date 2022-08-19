package FillApp;

import java.io.FileNotFoundException;
import java.util.Map;

public interface SaveHandler {
	
	void save(String name, int score) throws FileNotFoundException;
	
	Map<String, Integer> load() throws FileNotFoundException;
}
