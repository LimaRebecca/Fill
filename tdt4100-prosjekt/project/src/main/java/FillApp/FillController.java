package FillApp;

import java.io.FileNotFoundException;
import java.util.Map;
import FillBoard.Block;
import FillBoard.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FillController {
	
	private Board board;
	private FillGame game = new FillGame();
	private SaveHandler saveHandler = new FillSaveHandler();
	
	@FXML Pane field;	@FXML Pane controlls;
	@FXML Text midFieldText = new Text(); @FXML Text scoreText = new Text();
	@FXML Button downButton;@FXML Button upButton;@FXML Button rightButton;
	@FXML Button leftButton;@FXML Button undoButton;@FXML Button saveButton;
	@FXML TextField nameBar;
	
	@FXML
	private void initialize() {
		setDeciredLevel();
		createField();
		drawField();
	}
	
	private void createField() {
		field.getChildren().clear();
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				Pane block = new Pane();
				block.setTranslateX(x*80 + 10 *(x+1));
				block.setTranslateY(y*80 + 10);
				block.setPrefHeight(70);
				block.setPrefWidth(70);
				field.getChildren().add(block);
			}
		}
		removeSaveButton();
	}
	
	private void drawField() {
		// Tegner brettet
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				field.getChildren().get(y*board.getWidth() + x).setStyle("-fx-background-color: " + getBlockColor(board.getBlock(x, y)) + ";");
			}
		}
		// Her VINNES spillet! M� f� denne teksten til � vises p� skjermen og at det ikke g�r ann � spille mer!
		if (game.isWon(board)) {
			printMidFeldText("You won with " + game.getMoves() + " moves");
			removeControlls();
			addSaveButton();
		}
		// En level er passert, setter ny
		else if (game.isLevelDone(board)) {
			game.levelUp();
			setDeciredLevel();
			createField();
			drawField();
		}		
	}
	
	@FXML
	private void upButton() {
		game.move();
		try {
			board.moveUp();
			drawField();
			
		} catch (Exception e) {
		}
	}
	
	@FXML
	private void downButton() {
		game.move();
		try {
			board.moveDown();
			drawField();
			
		} catch (Exception e) {
		}	
	}
	
	@FXML
	private void leftButton() {
		game.move();
		try {
			board.moveLeft();
			drawField();
			
		} catch (Exception e) {	
		}
	}
	
	@FXML
	private void rightButton() {
		game.move();
		try {
			board.moveRight();
			drawField();
			
		} catch (Exception e) {	
		}
	}
	
	@FXML
	private void undoMovesButton() {
		if (!board.allBlanksFilled()) {
			initialize();	
		}
	}
	
	@FXML
	private void resetButton() {
		try {
			addControlls();
			
		} catch (Exception e) {
		}
		game.reset();
		initialize();
	}
	
	@FXML
	private void getHighscore() {
		try {
			Map<String, Integer> highScoreMap = saveHandler.load();
			String highscore = game.getHighScoreString(highScoreMap);
			printHighScore(highscore);
			removeControlls();
		
		} catch (FileNotFoundException e) {
			printMidFeldText("File not found");
			e.printStackTrace();
			
		} catch (IllegalArgumentException e)	{
			printMidFeldText("No saved High Score \n"
					+ "Press reset");
			removeControlls();
		}
	}
	
	@FXML
	private void saveToHighscore(){
		String name = nameBar.getText();
		if (!game.checkName(name)) {
			printMidFeldText("Illegal char in name");
		}
		else {
			String nameToSave = game.getNameForSaving(name);
			Integer score = Integer.valueOf(game.getMoves());
			
			try {
				saveHandler.save(nameToSave, score);
				printMidFeldText("The score was saved");
				removeSaveButton();
				
			} catch (FileNotFoundException e) {
				printMidFeldText("File not found");
				e.printStackTrace();
			}
		}
	}
	
	
	private void setDeciredLevel() {
		if (game.getLevel() == 1) {
			board = new Board(4,5);
			board.levelOne();
		}
		if (game.getLevel() == 2) {
			board = new Board(5,5);
			board.levelTwo();
		}
		if (game.getLevel() == 3) {
			board = new Board(6,5);
			board.levelThree();
		}
	}
	
	
	private String getBlockColor(Block block) {
		if (block.isAir()) {
			return "#c6cfce";
		}
		if (block.isWall()) {
			return "#17769c";
		}
		return "#29bd19";
	}
	
	private void printMidFeldText(String text) {
		field.getChildren().remove(midFieldText);
		midFieldText.setText(text);
		midFieldText.setStyle("-fx-font-size: 40px");
		midFieldText.setFill(Color.FIREBRICK);
		midFieldText.setTranslateX(80.0);
		midFieldText.setTranslateY(250.0);
		field.getChildren().add(midFieldText);
	}
	
	private void addControlls() {
		controlls.getChildren().add(downButton);
		controlls.getChildren().add(upButton);
		controlls.getChildren().add(leftButton);
		controlls.getChildren().add(rightButton);
		controlls.getChildren().add(undoButton);
	}
	
	private void removeControlls() {
		controlls.getChildren().remove(downButton);
		controlls.getChildren().remove(upButton);
		controlls.getChildren().remove(leftButton);
		controlls.getChildren().remove(rightButton);
		controlls.getChildren().remove(undoButton);
	}
		
	private void addSaveButton() {
		controlls.getChildren().add(saveButton);
		controlls.getChildren().add(nameBar);
	}
	
	private void removeSaveButton() {
		controlls.getChildren().remove(saveButton);
		controlls.getChildren().remove(nameBar);
	}
	
	private void printHighScore(String highScore) {
		field.getChildren().clear();
		scoreText.setText(highScore);
		scoreText.setStyle("-fx-font-size: 40px");
		scoreText.setFill(Color.DARKCYAN);
		scoreText.setTranslateX(0);
		scoreText.setTranslateY(50);
		field.getChildren().add(scoreText);
	}

}
