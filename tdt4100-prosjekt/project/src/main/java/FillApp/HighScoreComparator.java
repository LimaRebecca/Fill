package FillApp;

import java.util.Comparator;
import java.util.Map;

/**
 * St�tteklasse for FillGame
 *
 * Laget for � kunne sortere verdiene i Mapen som returneres ved load,
 * slik at beste score er �verst osv..
 * inpirasjon og kode hentet fra:
 * https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
 */

public class HighScoreComparator implements Comparator<String>{
	
	Map<String, Integer> base;

	public HighScoreComparator(Map<String, Integer> map) {
		if (map == null ) {
			throw new IllegalArgumentException("The map is empty");
		}
		if (map.isEmpty()) {
			throw new IllegalArgumentException("The map is empty");
		}
		base = map;
	}
	
	@Override
	public int compare(String o1, String o2) {
		if (base.get(o1) <= base.get(o2)) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
