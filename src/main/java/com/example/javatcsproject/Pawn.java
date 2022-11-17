package com.example.javatcsproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Pawn
{

	ImageView pawnIV;
	int index;
	int colorIndex;

	public Pawn(int color, int index){
		colorIndex=color;
		this.index=index;

		Image img;
		if(color==0){
			img=new Image(Objects.requireNonNull(getClass().getResource("/UI/green_pawn.png")).toString());
		}else if(color==1){
			img=new Image(Objects.requireNonNull(getClass().getResource("/UI/blue_pawn.png")).toString());
		}else if(color==2){
			img=new Image(Objects.requireNonNull(getClass().getResource("/UI/yellow_pawn.png")).toString());
		}else{
			img=new Image(Objects.requireNonNull(getClass().getResource("/UI/red_pawn.png")).toString());
		}
		pawnIV=new ImageView(img);
	}

	public ImageView getPawnIV()
	{
		return pawnIV;
	}
}
