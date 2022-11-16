package com.example.javatcsproject;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static com.example.javatcsproject.MainVariables.sizeX;
import static com.example.javatcsproject.MainVariables.sizeY;

public class GameMaster
{
	public Group selectionRoot;

	public GameMaster()
	{
		this.selectionRoot= new Group();

		Canvas canvas = new Canvas(sizeX, sizeY);
		selectionRoot.getChildren().add(canvas);
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
					selectionRoot.getChildren().add(fieldIV);
				}

			}
		}
		/*
		fieldIV.setX(sizeX/2-sizeX/26);
		selectionRoot.getChildren().add(fieldIV);
		fieldIV.setImage(background);
		ImageView fieldIV2= new ImageView(field);
		fieldIV2.setY(sizeX/2-sizeX/26);
		selectionRoot.getChildren().add(fieldIV2);*/
	}

	public Group getRoot() {
		return selectionRoot ;
	}
}
