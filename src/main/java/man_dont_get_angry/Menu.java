package man_dont_get_angry;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class Menu
{
	public static Group menuRoot;
	public static Scene scene;
	Image background;
	Image blueBackground;
	Image redBackground;
	Image yellowBackground;
	Image greenBackground;
	int backgroundAnimation;

	public Menu(Stage stage) {

		menuRoot = new Group();
		scene = new Scene(menuRoot);
		stage.setScene(scene);

		Canvas canvas = new Canvas(sizeX, sizeY);
		menuRoot.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image title=new Image(Objects.requireNonNull(getClass().getResource("/images/title.png")).toString());
		blueBackground=new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
		greenBackground=new Image(Objects.requireNonNull(getClass().getResource("/images/green_background.png")).toString());
		yellowBackground=new Image(Objects.requireNonNull(getClass().getResource("/images/yellow_background.png")).toString());
		redBackground=new Image(Objects.requireNonNull(getClass().getResource("/images/red_background.png")).toString());
		background = blueBackground;
		backgroundAnimation=0;


		Timeline fiveSecondsWonder = new Timeline(
				new KeyFrame(Duration.seconds(2), event->{
					if(backgroundAnimation%4==0)
						gc.drawImage(blueBackground, 0, 0, sizeX, sizeY);
					else if(backgroundAnimation%4==1)
						gc.drawImage(redBackground, 0, 0, sizeX, sizeY);
					else if(backgroundAnimation%4==2)
						gc.drawImage(yellowBackground, 0, 0, sizeX, sizeY);
					else
						gc.drawImage(greenBackground, 0, 0, sizeX, sizeY);

					gc.drawImage(title, sizeX/4, sizeY/12,sizeX/2, sizeX/2);
					backgroundAnimation++;
				}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();

		gc.drawImage(greenBackground, 0, 0, sizeX, sizeY);
		gc.drawImage(title, sizeX/4, sizeY/12,sizeX/2, sizeX/2);


		ImageButton onlineButton=new ImageButton("/images/buttons/local_button.png", (sizeX-100)/2, sizeY*6/10, 100, 50);
		menuRoot.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			new LocalMenu(scene);
		});

		ImageButton localButton=new ImageButton("/images/buttons/online_button.png", (sizeX-100)/2, sizeY*7/10, 100, 50);
		menuRoot.getChildren().add(localButton.get());
		localButton.get().setOnAction(e->{
			new Client("23.102.51.210", 5000, scene);
		});

		ImageButton exitButton=new ImageButton("/images/buttons/exit_button.png", (sizeX-100)/2, sizeY*8/10, 100, 50);
		menuRoot.getChildren().add(exitButton.get());
		exitButton.get().setOnAction(arg0->System.exit(0));
	}
}