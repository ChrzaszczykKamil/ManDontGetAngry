package man_dont_get_angry;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable
{
	private int PlayerID;
	private Pawn[] playerPawns;


	Player (int PlayerID){
		this.PlayerID=PlayerID;
		playerPawns=new Pawn[4];
	}

	public void createPawns(){
		for(int i=0; i<4; i++)
		{
			if(PlayerID==0)
			{playerPawns[i]=new Pawn(PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/green_pawn.png")).toString()));}
			else if(PlayerID==1)
			{playerPawns[i]=new Pawn(PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/yellow_pawn.png")).toString()));}
			else if(PlayerID==2)
			{playerPawns[i]=new Pawn(PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/blue_pawn.png")).toString()));}
			else
			{playerPawns[i]=new Pawn(PlayerID, new Image(Objects.requireNonNull(getClass().getResource("/images/pawns/red_pawn.png")).toString()));}
		}
	}

	public boolean playerTurn(){
		if(playerPawns[0].canPawnMove()||playerPawns[1].canPawnMove()||playerPawns[2].canPawnMove()||playerPawns[3].canPawnMove())
		{
			if(playerPawns[0].canPawnMove())
				playerPawns[0].addEvent();
			if(playerPawns[1].canPawnMove())
				playerPawns[1].addEvent();
			if(playerPawns[2].canPawnMove())
				playerPawns[2].addEvent();
			if(playerPawns[3].canPawnMove())
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
