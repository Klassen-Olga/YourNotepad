package md.klass.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import md.klass.application.navigation.Navigator;

public class Main extends Application {
  public Stage primaryStage;
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.primaryStage = stage;
    Navigator.setStage(stage);
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/login.fxml"));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toString());
    stage.setScene(scene);
    stage.setTitle("Login page");
    stage.getIcons().add(new Image("images/logo.png"));
    stage.setWidth(1000);
    stage.setHeight(600);
    stage.show();
  }
}
