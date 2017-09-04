package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {


			try{
			

			 CustomTabPane customTabPane = new CustomTabPane();

					 
			 Scene scene = new Scene(customTabPane,700,700);

			 primaryStage.setTitle("");
		     primaryStage.setScene(scene);
		     primaryStage.show();
		    
		    
		} catch(Exception e) {
			e.printStackTrace();
		 }
}
		 
	public static void main(String[] args) {
		launch(args);
	}
}
