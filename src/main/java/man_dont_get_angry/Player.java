package man_dont_get_angry;

import javafx.scene.image.Image;

import java.util.Objects;

import static man_dont_get_angry.GameMaster.dice;

public class Player
{
	int PlayerID;
	Pawn[] playerPawns;


	Player (int PlayerID){
		this.PlayerID=PlayerID;
		playerPawns=new Pawn[4];
	}

	public void createPawns(){
		for(int i=0; i<4; i++)
		{
			if(PlayerID==0)
			{playerPawns[i]=new Pawn(i,PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/green_pawn.png")).toString()));}
			else if(PlayerID==1)
			{playerPawns[i]=new Pawn(i,PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/yellow_pawn.png")).toString()));}
			else if(PlayerID==2)
			{playerPawns[i]=new Pawn(i,PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/blue_pawn.png")).toString()));}
			else
			{playerPawns[i]=new Pawn(i,PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/red_pawn.png")).toString()));}
		}
	}

	public boolean playerTurn(){
		if(playerPawns[0].deployed||playerPawns[1].deployed||playerPawns[2].deployed||playerPawns[3].deployed||dice.getRolledNumber()==6)
		{
			playerPawns[0].addEvent();
			playerPawns[1].addEvent();
			playerPawns[2].addEvent();
			playerPawns[3].addEvent();
			return true;
		}else{
			return false;
		}
	}

	public void turnFinished(){
		playerPawns[0].removeEvent();
		playerPawns[1].removeEvent();
		playerPawns[2].removeEvent();
		playerPawns[3].removeEvent();
	}

	public Pawn[] getPlayerPawns()
	{
		return playerPawns;
	}

	public int getPlayerID()
	{
		return PlayerID;
	}
}
