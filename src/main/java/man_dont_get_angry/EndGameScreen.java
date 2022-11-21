package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;
import static man_dont_get_angry.Menu.menuRoot;
import static man_dont_get_angry.Menu.scene;

public class EndGameScreen
{
	public EndGameScreen(Group root, Player winner){
		root.getChildren().clear();
		Canvas canvas = new Canvas(sizeX, sizeY);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image winner_bg;
		if(winner.getPlayerID()==0)
			winner_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/green_background.png")).toString());
		else if(winner.getPlayerID()==1)
		winner_bg= new Image(Objects.requireNonNull(getClass().getResource("/images/yellow_background.png")).toString());
		else if(winner.getPlayerID()==2)
			winner_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
		else
			winner_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/red_background.png")).toString());

		Image RK = new Image(Objects.requireNonNull(getClass().getResource("/images/winner.png")).toString());
		gc.drawImage(winner_bg, 0, 0, sizeX, sizeY);
		gc.drawImage(RK, sizeX/2-450/2, 0, 450, sizeY);
		winner.getPlayerPawns()[0].getPawnIV().setX(sizeX/2-32);
		winner.getPlayerPawns()[0].getPawnIV().setY(sizeX*2/3);
		root.getChildren().add(winner.getPlayerPawns()[0].getPawnIV());

		ImageButton onlineButton=new ImageButton("/images/buttons/continue_button.png", (sizeX-128)/2, sizeY*8/10, 128, 64);
		root.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			scene.setRoot(menuRoot);
		});
	}
}
