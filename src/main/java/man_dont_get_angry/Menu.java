package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Menu
{
	public static Group menuRoot;
	public static Scene scene;

	public Menu(Stage stage) {

		menuRoot = new Group();
		scene = new Scene(menuRoot);
		stage.setScene(scene);

		Canvas canvas = new Canvas(MainVariables.sizeX, MainVariables.sizeY);
		menuRoot.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image background = new Image(Objects.requireNonNull(getClass().getResource("/UI/background.png")).toString());
		gc.drawImage(background, 0, 0, MainVariables.sizeX, MainVariables.sizeY);

		Image title=new Image(Objects.requireNonNull(getClass().getResource("/UI/title.png")).toString());
		gc.drawImage(title, (MainVariables.sizeX-title.getWidth())/2, (MainVariables.sizeY)/9);


		ImageButton onlineButton=new ImageButton("/UI/local_button.png", (MainVariables.sizeX-100)/2, MainVariables.sizeY*6/10, 100, 50);
		menuRoot.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			new GameMaster(scene);
			//GameMaster game=
		});

		ImageButton localButton=new ImageButton("/UI/online_button.png", (MainVariables.sizeX-100)/2, MainVariables.sizeY*7/10, 100, 50);
		menuRoot.getChildren().add(localButton.get());
		localButton.get().setOnAction(e->{
			System.out.println("CLICKED");
			//GameMaster game=new GameMaster();
		});

		ImageButton exitButton=new ImageButton("/UI/exit_button.png", (MainVariables.sizeX-100)/2, MainVariables.sizeY*8/10, 100, 50);
		menuRoot.getChildren().add(exitButton.get());
		exitButton.get().setOnAction(arg0->System.exit(0));
	}
}