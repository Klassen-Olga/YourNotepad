package md.klass.application.navigation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import md.klass.application.controllers.AbstractController;

import java.io.IOException;

public class Navigator {
	private static Navigator navigator;
	private static Stage primaryStage;

	private Navigator() {
	}

	public static void setStage(Stage stage) {
		Navigator.primaryStage = stage;
	}

	public static void navigateTo(String view) throws IOException {
		if (Navigator.primaryStage == null) {
			throw new IOException();
		}
		Parent root = FXMLLoader.load(Navigator.class.getClassLoader().getResource("views/" + view + ".fxml"));
		primaryStage.getScene().setRoot(root);
		primaryStage.show();
		primaryStage.setTitle(view + " page");
	}

	public static <T> void navigateTo(String view, ControllerArgument<T> argument) throws IOException {
		if (Navigator.primaryStage == null) {
			throw new IOException();
		}

		FXMLLoader fxmlLoader = new FXMLLoader(Navigator.class.getClassLoader().getResource("views/" + view + ".fxml"));
		Parent root = fxmlLoader.load();
		AbstractController controller=fxmlLoader.getController();
		controller.setInput(argument);
		controller.beforeBegin();
		primaryStage.getScene().setRoot(root);

		primaryStage.show();
		primaryStage.setTitle(view + " page");
	}


	public static Navigator getNavigator() {
		if (Navigator.navigator == null) {
			Navigator.navigator = new Navigator();
		}
		return Navigator.navigator;
	}
}
