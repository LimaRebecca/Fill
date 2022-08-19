package FillApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FillApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("Fill.fxml"));
		stage.setTitle("FILL");
		stage.setScene(new Scene(parent));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(FillApp.class, args);
	}
}
