package com.example.javatcsproject;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

import static com.example.javatcsproject.MainVariables.sizeX;
import static com.example.javatcsproject.MainVariables.sizeY;

public class Menu
{
	public static Group menuRoot;
	public static Scene scene;

	public Menu(Stage stage) {

		menuRoot = new Group();
		scene = new Scene(menuRoot);
		stage.setScene(scene);

		Canvas canvas = new Canvas(sizeX, sizeY);
		menuRoot.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image background = new Image(Objects.requireNonNull(getClass().getResource("/UI/background.png")).toString());
		gc.drawImage(background, 0, 0, sizeX, sizeY);

		Image title=new Image(Objects.requireNonNull(getClass().getResource("/UI/title.png")).toString());
		gc.drawImage(title, (sizeX-300)/2, (sizeY)/9);


		ImageButton startButton=new ImageButton("/UI/button.png", (sizeX-100)/3, sizeY/2, (int)(sizeX*0.1), (int)(sizeY*0.05));
		menuRoot.getChildren().add(startButton.get());
		startButton.get().setOnAction(e->{
			System.out.println("CLICKED");
			GameMaster game=new GameMaster();
			scene.setRoot(game.getRoot());
		});

		ImageButton exitButton=new ImageButton("/UI/button.png", (sizeX-100)*2/3, sizeY/2, (int)(sizeX*0.1), (int)(sizeY*0.05));
		menuRoot.getChildren().add(exitButton.get());
		exitButton.get().setOnAction(arg0->System.exit(0));
	}
}