package com.capgemini.starterkit.solejnik.booksFX;

import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {
	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Locale.setDefault(Locale.US);
		primaryStage.setTitle("Library FX");

		Parent root = FXMLLoader.load(
				getClass().getResource("/com/capgemini/starterkit/solejnik/booksFX/view/Book-search.fxml"));

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/com/capgemini/starterkit/solejnik/booksFX/css/local.css").toExternalForm());
		primaryStage.setScene(scene);

		primaryStage.show();
	}

}
