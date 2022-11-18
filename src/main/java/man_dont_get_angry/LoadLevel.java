package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class LoadLevel
{
	public Group root;
	public static GraphicsContext gc;
	static Image blue_bg;
	static Image yellow_bg;
	static Image red_bg;
	static Image green_bg;

	public LoadLevel(Scene scene)
	{
		root=new Group();
		Canvas canvas = new Canvas(sizeX, sizeY);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		green_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/green_background.png")).toString());
		yellow_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/yellow_background.png")).toString());
		red_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/red_background.png")).toString());
		blue_bg = new Image(Objects.requireNonNull(getClass().getResource("/images/blue_background.png")).toString());
		gc.drawImage(green_bg, 0, 0, sizeX, sizeY);

		Image field=new Image(Objects.requireNonNull(getClass().getResource("/images/fields/field.png")).toString());
		Image greenField=new Image(Objects.requireNonNull(getClass().getResource("/images/fields/green_field.png")).toString());
		Image blueField=new Image(Objects.requireNonNull(getClass().getResource("/images/fields/blue_field.png")).toString());
		Image redField=new Image(Objects.requireNonNull(getClass().getResource("/images/fields/red_field.png")).toString());
		Image yellowField=new Image(Objects.requireNonNull(getClass().getResource("/images/fields/yellow_field.png")).toString());

		for(int i=0;i<11; i++) {
			int j=0;
			int max=11;
			if(i<4||i>6)
			{
				j=4;
				max=7;
			}
			for(;j<max; j++)
			{
				ImageView fieldIV=new ImageView();
				if(!((i==5&&j!=0&&j!=10)||(j==5&&i!=0&&i!=10))){
					fieldIV.setImage(field);
				}else{
					if(i<5){
						fieldIV.setImage(yellowField);
					}else if(i>5){
						fieldIV.setImage(greenField);
					}else if(j>5){
						fieldIV.setImage(blueField);
					}else if(j<5){
						fieldIV.setImage(redField);
					}
				}
				if(!(i==5&&j==5))
				{
					fieldIV.setX(sizeX/2-64*i+32*9);
					fieldIV.setY(sizeX/2-64*j+32*9);
					root.getChildren().add(fieldIV);
				}
			}
		}
		for(int i=1; i<3;i++){
			for(int j=1; j<3;j++){
				ImageView fieldIV=new ImageView();
				fieldIV.setImage(yellowField);
				fieldIV.setX(sizeX/2-64*i+32*9);
				fieldIV.setY(sizeX/2-64*j+32*9);
				root.getChildren().add(fieldIV);
			}
			for(int j=8; j<10;j++){
				ImageView fieldIV=new ImageView();
				fieldIV.setImage(blueField);
				fieldIV.setX(sizeX/2-64*i+32*9);
				fieldIV.setY(sizeX/2-64*j+32*9);
				root.getChildren().add(fieldIV);
			}
		}
		for(int i=8; i<10;i++){
			for(int j=1; j<3;j++){
				ImageView fieldIV=new ImageView();
				fieldIV.setImage(redField);
				fieldIV.setX(sizeX/2-64*i+32*9);
				fieldIV.setY(sizeX/2-64*j+32*9);
				root.getChildren().add(fieldIV);
			}
			for(int j=8; j<10;j++){
				ImageView fieldIV=new ImageView();
				fieldIV.setImage(greenField);
				fieldIV.setX(sizeX/2-64*i+32*9);
				fieldIV.setY(sizeX/2-64*j+32*9);
				root.getChildren().add(fieldIV);
			}
		}


		scene.setRoot(root);
	}

	public void placePawns(Player player){
		Pawn[] pawns=player.getPlayerPawns();

		if(player.getPlayerID()==0)
			for(int i=8; i<10; i++)
				for(int j=8; j<10; j++)
					placeSinglePawn(pawns[i-8+2*(j-8)], i, j);

		else if(player.getPlayerID()==2)
			for(int i=1; i<3; i++)
				for(int j=8; j<10; j++)
					placeSinglePawn(pawns[i-1+2*(j-8)], i, j);

		else if(player.getPlayerID()==1)
			for(int i=1; i<3; i++)
				for(int j=1; j<3; j++)
					placeSinglePawn(pawns[i-1+2*(j-1)], i, j);

		else
			for(int i=8; i<10; i++)
				for(int j=1; j<3; j++)
					placeSinglePawn(pawns[i-8+2*(j-1)], i, j);
	}

	private void placeSinglePawn(Pawn pawn, int i, int j){
		pawn.getPawnIV().setX(sizeX/2-64*i+32*9);
		pawn.getPawnIV().setY(sizeX/2-64*j+32*9);
		root.getChildren().add(pawn.getPawnIV());
		pawn.setInitialXY(sizeX/2-64*i+32*9,sizeX/2-64*j+32*9);
	}

	public Group getRoot()
	{
		return root;
	}
	public static void changeBackground(int player){
		if(player==0)
			gc.drawImage(green_bg, 0, 0, sizeX, sizeY);
		else if(player==1)
			gc.drawImage(yellow_bg, 0, 0, sizeX, sizeY);
		else if(player==2)
			gc.drawImage(blue_bg, 0, 0, sizeX, sizeY);
		else if(player==3)
		gc.drawImage(red_bg, 0, 0, sizeX, sizeY);
	}
}
