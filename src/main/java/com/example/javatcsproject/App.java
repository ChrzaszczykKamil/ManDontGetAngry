package com.example.javatcsproject;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
	public void start(Stage stage)
	{
		Rectangle2D screenBounds=Screen.getPrimary().getBounds();
		//gridSize=sizeY/10;
		stage.setTitle("Tower Defence the Game!");
		new Menu(stage);
		stage.setResizable(false);
		stage.show();

		//stage.setFullScreen(true);
	}

	public static void main(String[] args)
	{
		launch();
	}
}