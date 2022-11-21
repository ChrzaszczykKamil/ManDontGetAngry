package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static man_dont_get_angry.GameMaster.isOffline;
import static man_dont_get_angry.GameMaster.setPlayersCount;
import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class LocalMenu{
	Image background;
	public static Group root;
	int backgroundAnimation;

	LocalMenu(Scene scene){

		root = new Group();

		Canvas canvas = new Canvas(sizeX, sizeY);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		background = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
		backgroundAnimation=0;

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(backgroundAnimation%4==0){
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/green_background.png")).toString());
				}
				else if(backgroundAnimation%4==1){
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/red_background.png")).toString());
				}
				else if(backgroundAnimation%4==2){
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/yellow_background.png")).toString());
				}
				else{
					background = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
				}
				gc.drawImage(background, 0, 0, sizeX, sizeY);
				backgroundAnimation++;
			}
		}, 0, 3000);
		setPlayersCount(2);

		CheckBox twoPlayers = new CheckBox("2");
		twoPlayers.setSelected(true);
		CheckBox threePlayers = new CheckBox("3");
		threePlayers.setSelected(false);
		CheckBox fourPlayers = new CheckBox("4");
		fourPlayers.setSelected(false);

		Text pl= new Text("Players:");
		VBox toppings1V = new VBox(pl, twoPlayers, threePlayers, fourPlayers);
		toppings1V.setSpacing(20);
		toppings1V.setLayoutX(sizeX/2);
		toppings1V.setLayoutY(sizeX/3);

		twoPlayers.setOnAction(e->{
			if(twoPlayers.isSelected())
			{
				setPlayersCount(2);
				threePlayers.setSelected(false);
				fourPlayers.setSelected(false);
				System.out.println("2");
			}else{
				twoPlayers.setSelected(true);
			}
		});

		threePlayers.setOnAction(e->{
			if(threePlayers.isSelected())
			{
				setPlayersCount(3);
				twoPlayers.setSelected(false);
				fourPlayers.setSelected(false);
				System.out.println("3");
			}else{
				threePlayers.setSelected(true);
			}
		});

		fourPlayers.setOnAction(e->{
			if(fourPlayers.isSelected())
			{
				setPlayersCount(4);
				threePlayers.setSelected(false);
				twoPlayers.setSelected(false);
				System.out.println("4");
			}else{
				fourPlayers.setSelected(true);
			}
		});
		root.getChildren().add(toppings1V);
		scene.setRoot(root);

		ImageButton onlineButton=new ImageButton("/images/buttons/local_button.png", (sizeX-100)/2, sizeY*6/10, 100, 50);
		root.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			isOffline=true;
			new GameMaster(scene);
		});
	}
}
