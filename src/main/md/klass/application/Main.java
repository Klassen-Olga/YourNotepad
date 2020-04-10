package md.klass.application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import md.klass.application.navigation.Navigator;


public class Main extends Application {
	public Stage primaryStage;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		System.out.println("Init app");
		super.init();

	}

	@Override
	public void stop() throws Exception {
		System.out.println("Stop app");
		super.stop();
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Для создания интерфейса из файла fxml применяется метод FXMLLoader.load(). Чтобы получить
		//определение интерфейса из файла, используется метод getClass().getResource("Main.fxml").
		this.primaryStage = stage;
		Navigator.setStage(stage);
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/register.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("css/style.css").toString());

		stage.setScene(scene);

		stage.setTitle("Login page");
		stage.setWidth(1000);
		stage.setHeight(600);

		stage.show();

	}
/*@Override
public void start(Stage stage) throws Exception {
	//Для создания интерфейса из файла fxml применяется метод FXMLLoader.load(). Чтобы получить
	//определение интерфейса из файла, используется метод getClass().getResource("Main.fxml").

	Button button=new Button("Hello World");
	String html="<!DOCTYPE html>" +
		"<html>" +
		"<body>" +
		"<h1>Hello World!</h1>" +
		"</body>"+
		"</html>";
	WebView webView=new WebView();
	WebEngine webEngine=webView.getEngine();
	webEngine.loadContent(html);

	HTMLEditor htmlEditor=new HTMLEditor();
	Group root=new Group(htmlEditor);
	Scene scene = new Scene(root);

	stage.setScene(scene);

	stage.setTitle("Login page");
	stage.setWidth(800);
	stage.setHeight(600);

	stage.show();

}*/


}
