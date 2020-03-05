package org.groupId;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Startside extends Application {


	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/vikarbyraa.fxml"));
		Parent root = loader.load();
		stage.setScene(new Scene(root));
		stage.setTitle("Vikarbyrå");
		stage.setResizable(false);
		root.getStylesheets().add(String.valueOf(getClass().getClassLoader().getResource("test.css")));
		stage.show();
	}
}
