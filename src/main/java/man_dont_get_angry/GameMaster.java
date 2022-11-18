package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.Arrays;

import static man_dont_get_angry.Dice.rollPossible;

public class GameMaster
{
	public Group root;
	public static Player currentPlayer;
	public static int currentPlayerID=0;
	public static int numberOfPlayers;
	public static Player[] players;
	public static Dice dice;
	static int[] pathStatus;
	static boolean madeAMove;

	public GameMaster(Scene scene)
	{
		LoadLevel lvl=new LoadLevel(scene);
		root=lvl.getRoot();

		numberOfPlayers=4;
		madeAMove=false;
		pathStatus=new int[40];
		Arrays.fill(pathStatus, -1);

		//add pawns
		dice=new Dice(root);
		players=new Player[numberOfPlayers];

		for(int i=0; i<numberOfPlayers;i++){
			players[i]=new Player(i);
			players[i].createPawns();
			lvl.placePawns(players[i]);
		}
		currentPlayer=players[0];
	}

	public static void playerTurn(){
		rollPossible=false;
		if(!currentPlayer.playerTurn()){
			setNextPlayer();
		}
	}

	public static boolean isSpotTakenByAlly(int coordinate, int id){
		return pathStatus[coordinate]==id;
	}
	public static void setPathSpot(int coordinate, int id){
		if(id!=-1)
		{
			if(pathStatus[coordinate]!=-1)
			{
				for(int i=0; i<4; i++)
				{
					if(players[pathStatus[coordinate]].getPlayerPawns()[i].pathLocation==coordinate)
					{players[pathStatus[coordinate]].getPlayerPawns()[i].pawnDied();}
				}
			}
		}
		pathStatus[coordinate]=id;
	}

	public static boolean isSpotTakenByEnemy(int coordinate, int id){
		return pathStatus[coordinate]!=id&&pathStatus[coordinate]!=-1;
	}

	public static void setNextPlayer(){
		rollPossible=true;
		currentPlayer.turnFinished();
		if(numberOfPlayers<3)
			currentPlayerID=(currentPlayerID+1)%numberOfPlayers;
		else if(numberOfPlayers==3)
		{
			currentPlayerID=(currentPlayerID+2)%numberOfPlayers;
		}else{
			if(currentPlayerID==2)
				currentPlayerID=1;
			else if(currentPlayerID==3)
				currentPlayerID=0;
			else
				currentPlayerID+=2;
		}
		currentPlayer=players[currentPlayerID];
	}
}
