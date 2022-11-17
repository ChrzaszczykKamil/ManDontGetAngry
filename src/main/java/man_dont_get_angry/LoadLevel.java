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

	public LoadLevel(Scene scene)
	{
		root=new Group();
		Canvas canvas = new Canvas(sizeX, sizeY);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image background = new Image(Objects.requireNonNull(getClass().getResource("/UI/background.png")).toString());
		gc.drawImage(background, 0, 0, sizeX, sizeY);

		Image field=new Image(Objects.requireNonNull(getClass().getResource("/UI/field.png")).toString());
		Image greenField=new Image(Objects.requireNonNull(getClass().getResource("/UI/green_field.png")).toString());
		Image blueField=new Image(Objects.requireNonNull(getClass().getResource("/UI/blue_field.png")).toString());
		Image redField=new Image(Objects.requireNonNull(getClass().getResource("/UI/red_field.png")).toString());
		Image yellowField=new Image(Objects.requireNonNull(getClass().getResource("/UI/yellow_field.png")).toString());

		for(int i=0;i<11; i++)
		{
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

	public void placePawns(Pawn[][] pawns){
		int id=0;
		for(int i=8; i<10;i++){
			for(int j=8; j<10;j++,id++){
				placeSinglePawn(id,pawns, i, j);
			}
		}
		for(int i=1; i<3;i++){
			for(int j=8; j<10;j++,id++){
				placeSinglePawn(id,pawns, i, j);
			}
		}
		for(int i=1; i<3;i++){
			for(int j=1; j<3;j++,id++){
				placeSinglePawn(id,pawns, i, j);
			}
		}
		for(int i=8; i<10;i++){
			for(int j=1; j<3;j++,id++){
				placeSinglePawn(id,pawns, i, j);
			}
		}
	}

	private void placeSinglePawn(int id,Pawn[][] pawns, int i, int j){
		pawns[(id-id%4)/4][id%4].getPawnIV().setX(sizeX/2-64*i+32*9);
		pawns[(id-id%4)/4][id%4].getPawnIV().setY(sizeX/2-64*j+32*9);
		root.getChildren().add(pawns[(id-id%4)/4][id%4].getPawnIV());
	}

	public Group getRoot()
	{
		return root;
	}
}
