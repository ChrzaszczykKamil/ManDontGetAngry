package man_dont_get_angry;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
	public void start(Stage stage)
	{
		stage.setTitle("Man, Don't Get Angry!");
		new Menu(stage);
		stage.setResizable(false);
		stage.show();

		//stage.setFullScreen(true);
	}

	public static void main(String[] args)
	{
		launch();
	}
}