package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

import static man_dont_get_angry.GameMaster.playerTurn;
import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class Dice
{
	ImageView diceIV;
	private int rolledNumber;
	public static int diceRollCounter;
	public static boolean rollPossible;
	Group root;

	public Dice(Group root){
		this.root=root;
		diceRollCounter=0;
		rollPossible=true;

		diceIV=new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice1.png")).toString()));
		diceIV.setX(sizeX/2-32);
		diceIV.setY(sizeX*7/8);

		ImageButton rollDice=new ImageButton("/images/buttons/roll_button.png", (sizeX-128)/2, sizeY*14/15, 100, 50);
		root.getChildren().add(rollDice.get());
		root.getChildren().add(diceIV);

		rollDice.get().setOnAction(e->{
			if(rollPossible)
			{
				diceRollCounter++;
				System.out.println(roll());
				playerTurn();
			}
		});
	}

	public int roll(){
		rolledNumber=6;
		return 6;/*
		rolledNumber=ThreadLocalRandom.current().nextInt(1, 7);
		if(rolledNumber==1)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice1.png")).toString()));
		if(rolledNumber==2)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice2.png")).toString()));
		if(rolledNumber==3)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice3.png")).toString()));
		if(rolledNumber==4)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice4.png")).toString()));
		if(rolledNumber==5)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice5.png")).toString()));
		if(rolledNumber==6)
			diceIV.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice6.png")).toString()));
		return rolledNumber;*/
	}

	public ImageView getDiceIV()
	{
		return diceIV;
	}

	int getRolledNumber(){
		return rolledNumber;
	}
}
