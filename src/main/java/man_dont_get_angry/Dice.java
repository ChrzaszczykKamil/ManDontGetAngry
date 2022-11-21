package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static man_dont_get_angry.Client.clientID;
import static man_dont_get_angry.GameMaster.*;
import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class Dice implements Serializable
{
	private transient ImageView diceIV;
	private int rolledNumber;
	public static boolean rollPossible;

	public Dice(Group root){
		rollPossible=true;

		diceIV=new ImageView();

		ImageButton rollDice=new ImageButton("/images/buttons/roll_button.png", (sizeX-128)/2, sizeY*14/15, 128, 64);
		root.getChildren().add(rollDice.get());
		root.getChildren().add(diceIV);

		rollDice.get().setOnAction(e->{
			if(rollPossible&&(currentPlayerID==clientID||isOffline))
			{
				moveDiceToCorner(currentPlayerID);
				roll();
				playerTurn();
			}
		});
	}

	public void roll(){
		rolledNumber=ThreadLocalRandom.current().nextInt(1, 9);//7
		if(rolledNumber>6)
			rolledNumber=6;
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
	}

	private void moveDiceToCorner(int c){
		if(c==0){
			diceIV.setX(20);
			diceIV.setY(20);
		}
		else if(c==1){
			diceIV.setX(sizeX-100);
			diceIV.setY(sizeX-100);
		}
		else if(c==2){
			diceIV.setX(sizeX-100);
			diceIV.setY(20);
		}
		else{
			diceIV.setX(20);
			diceIV.setY(sizeX-100);
		}
	}

	public int getRolledNumber(){
		return rolledNumber;
	}
}
