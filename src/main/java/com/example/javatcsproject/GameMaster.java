package com.example.javatcsproject;

import javafx.scene.Group;
import javafx.scene.Scene;

import static com.example.javatcsproject.MainVariables.sizeX;
import static com.example.javatcsproject.MainVariables.sizeY;

public class GameMaster
{
	public Group root;
	private final Pawn[][]pawns;

	public GameMaster(Scene scene)
	{
		LoadLevel lvl=new LoadLevel(scene);
		root=lvl.getRoot();
		//add pawns
		pawns=new Pawn[4][4];
		for(int i=0; i<4;i++){
			for(int j=0; j<4;j++){
				pawns[i][j]= new Pawn(i, 4*i+j);
			}
		}
		lvl.placePawns(pawns);

		ImageButton onlineButton=new ImageButton("/UI/local_button.png", (sizeX-100)/2, sizeY*6/10, 100, 50);
		root.getChildren().add(onlineButton.get());
		onlineButton.get().setOnAction(e->{
			getPawnByID(4*1+1).getPawnIV().setX(20);
			getPawnByID(4*2+1).getPawnIV().setX(40);
			getPawnByID(4*3+1).getPawnIV().setX(80);
			getPawnByID(4*0+1).getPawnIV().setX(100);
		});

	}

	public Pawn getPawnByID(int id){
		return pawns[(id-id%4)/4][id%4];
	}
}
