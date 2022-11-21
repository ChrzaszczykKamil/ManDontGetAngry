package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class Menu
{
	public static Group menuRoot;
	public static Scene scene;
	Image background;
	int backgroundAnimation;

	public Menu(Stage stage) {

		menuRoot = new Group();
		scene = new Scene(menuRoot);
		stage.setScene(scene);

		Canvas canvas = new Canvas(sizeX, sizeY);
		menuRoot.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image title=new Image(Objects.requireNonNull(getClass().getResource("/images/title.png")).toString());
		background = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
		backgroundAnimation=0;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(backgroundAnimation%4==0)
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
				else if(backgroundAnimation%4==1)
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/red_background.png")).toString());
				else if(backgroundAnimation%4==2)
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/yellow_background.png")).toString());
				else
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/green_background.png")).toString());

				gc.drawImage(background, 0, 0, sizeX, sizeY);
				gc.drawImage(title, sizeX/4, sizeY/12,sizeX/2, sizeX/2);
				backgroundAnimation++;
			}
		}, 0, 2000);

		gc.drawImage(background, 0, 0, sizeX, sizeY);


		ImageButton onlineButton=new ImageButton("/images/buttons/local_button.png", (sizeX-100)/2, sizeY*6/10, 100, 50);
		menuRoot.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			new LocalMenu(scene);
		});

		ImageButton localButton=new ImageButton("/images/buttons/online_button.png", (sizeX-100)/2, sizeY*7/10, 100, 50);
		menuRoot.getChildren().add(localButton.get());
		localButton.get().setOnAction(e->{
			new Client("127.0.0.1", 5000, scene);
		});

		ImageButton exitButton=new ImageButton("/images/buttons/exit_button.png", (sizeX-100)/2, sizeY*8/10, 100, 50);
		menuRoot.getChildren().add(exitButton.get());
		exitButton.get().setOnAction(arg0->System.exit(0));
	}
}