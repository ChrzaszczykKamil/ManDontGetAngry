package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static man_dont_get_angry.Client.clientID;
import static man_dont_get_angry.GameMaster.*;
import static man_dont_get_angry.MainVariables.sizeX;
import static man_dont_get_angry.MainVariables.sizeY;

public class Dice
{
	private ImageView diceIV;

	Image dice1IV;
	Image dice2IV;
	Image dice3IV;
	Image dice4IV;
	Image dice5IV;
	Image dice6IV;
	private int rolledNumber;
	public static boolean rollPossible;

	public Dice(Group root){
		rollPossible=true;

		diceIV=new ImageView();

		ImageButton rollDice=new ImageButton("/images/buttons/roll_button.png", (sizeX-128)/2, sizeY*14/15, 128, 64);

		dice1IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice1.png")).toString());
		dice2IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice2.png")).toString());
		dice3IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice3.png")).toString());
		dice4IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice4.png")).toString());
		dice5IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice5.png")).toString());
		dice6IV=new Image(Objects.requireNonNull(getClass().getResource("/images/dice/dice6.png")).toString());
		root.getChildren().add(rollDice.get());
		root.getChildren().add(diceIV);

		rollDice.get().setOnAction(e->{
			if(rollPossible&&(currentPlayerID==clientID||isOffline))
			{
				roll();
				playerTurn();
			}
		});
	}

	public void roll(){
		rolledNumber=ThreadLocalRandom.current().nextInt(1, 9);//7
		if(rolledNumber>6)
			rolledNumber=6;
		setImage();
		if(!isOffline)
			sendData(3000+rolledNumber);
	}

	void setDiceRoll(int x){
		rolledNumber=x;
		setImage();
	}

	private void setImage()
	{
		moveDiceToCorner(currentPlayerID);
		if(rolledNumber==1)
			diceIV.setImage(dice1IV);
		if(rolledNumber==2)
			diceIV.setImage(dice2IV);
		if(rolledNumber==3)
			diceIV.setImage(dice3IV);
		if(rolledNumber==4)
			diceIV.setImage(dice4IV);
		if(rolledNumber==5)
			diceIV.setImage(dice5IV);
		if(rolledNumber==6)
			diceIV.setImage(dice6IV);
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
