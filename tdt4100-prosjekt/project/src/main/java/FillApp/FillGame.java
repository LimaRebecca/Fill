package FillApp;

import java.util.Map;
import java.util.TreeMap;

import FillBoard.Board;

/**
 * St�tteklasse for Controller
 *
 * Holder styr p� antall moves og hvilken level vi er p�.
 * Hjelper med � sjekke brettets tilstand.
 * Sjekker lagringsnavn og sorterer listen som printes ved High Score.
 * 
 */

public class FillGame {
	
	private int level = 1;
	
	private int moves;
	
	public String getHighScoreString(Map<String, Integer> highScoreMap) {
		HighScoreComparator hsc = new HighScoreComparator(highScoreMap);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(hsc);
		
		sortedMap.putAll(highScoreMap);
	
		String highscore = "High Scores:\nName \tMoves\n";
		int i = 5;
		for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
			if (i == 0) {
				break;
			}
			highscore += String.format(
					"""
					%s \t\t%d	
					""",
					entry.getKey(),
					entry.getValue()
					
					);
			i--;
		}
		return highscore;
	}
	
	public boolean checkName(String name) {
		if (name.isBlank() || name.isEmpty() || name.length() < 3) {
			return false;
		}
		return name.matches("[A-Za-z0-9]+");
	}
	
	public String getNameForSaving(String name) {
		return name.substring(0, 3);
	}
	public int getLevel() {
		return level;
	}
	
	
	
	public void levelUp() {
		if (level == 3) {
			throw new IllegalStateException("Cannot level up from this level.");
		}
		level++;
	}
	
	public void levelDown() {
		if (level == 1) {
			throw new IllegalStateException("Cannot level down from this level.");
		}
		level--;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public void move() {
		moves++;
	}
	
	public void reset() {
		setLevel(1);
		setMoves(0);
	}
	
	public boolean isWon(Board board) {
		return board.allBlanksFilled() && level == 3;
	}
	public boolean isLevelDone(Board board) {
		return board.allBlanksFilled();
	}
	
	private void setMoves(int moves) {
		this.moves = moves;
	}
		
	private void setLevel(int level) {
		if (level < 1 || level > 3) {
			throw new IllegalArgumentException("Level must be 0-3");
		}
		this.level = level;
	}
}
