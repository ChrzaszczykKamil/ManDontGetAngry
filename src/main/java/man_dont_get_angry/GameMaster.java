package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.Arrays;

import static man_dont_get_angry.Dice.rollPossible;
import static man_dont_get_angry.LoadLevel.changeBackground;

public class GameMaster
{
	private static Group root;

	public static int currentPlayerID;
	public static boolean [][]podium;
	public static Dice dice;
	public static boolean isOffline;

	private static Player currentPlayer;
	private static Player[] players;
	public static int numberOfPlayers;
	private static int[] pathStatus;

	private static ClientGameMaster client;

	public GameMaster(Scene scene) {
		LoadLevel lvl=new LoadLevel(scene);
		root=lvl.getRoot();

		currentPlayerID=0;
		pathStatus=new int[40];
		podium=new boolean[4][4];
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

	public static void setPlayersCount(int x){
		numberOfPlayers=x;
	}

	public static int getPlayersCount(){
		return numberOfPlayers;
	}

	public static void playerTurn(){
		rollPossible=false;
		if(!currentPlayer.playerTurn()){
			setNextPlayer();
		}
	}

	public static boolean isSpotFree(int coordinate, int id){
		return pathStatus[coordinate]!=id;
	}

	public static boolean isPodiumSpotFree(int coordinate, int id){
		if(coordinate<4)
			return !podium[id][coordinate];
		else
			return false;
	}

	public static void setPathSpot(int coordinate, int id){
		if(id!=-1)
		{
			if(pathStatus[coordinate]!=-1)
			{
				for(int i=0; i<4; i++)
				{
					if(players[pathStatus[coordinate]].getPlayerPawns()[i].getPathLocation()==coordinate)
					{players[pathStatus[coordinate]].getPlayerPawns()[i].pawnDied();}
				}
			}
		}
		pathStatus[coordinate]=id;
	}

	private static boolean checkWin(){
		for(int i=0; i<4;i++){
			int x=0;
			for(int j=0; j<4; j++){
				if(podium[i][j])
					x++;
			}
			if(x==4)
				return true;
		}
		return false;
	}

	public static void clearLevel(){
		System.out.println("WINNER");
		new EndGameScreen(root, currentPlayer);
	}

	public static void setTheSamePlayer(){
		if(checkWin())
		{
			clearLevel();
			return;
		}
		rollPossible=true;
		currentPlayer.turnFinished();
	}

	public static void setNextPlayer(){
		if(checkWin())
		{
			clearLevel();
			return;
		}
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
		changeBackground(currentPlayerID);
		if(!isOffline)
			sendData(currentPlayerID+2000);
	}

	public static void sendData(int x){
		client.write(x);
	}

	public static void setData(int x){
		if(3000>x&&x>=2000)
		{
			setCurrentPlayer(x-2000);
			changeBackground(currentPlayerID);
		}else if(4000>x&&x>=3000){
			dice.setDiceRoll(x-3000);
		}else if(5000>x&&x>=4000){
			System.out.println("MOVING PAWN #"+ x);
			currentPlayer.getPlayerPawns()[x-4000].event();
		}
	}

	public static void setCurrentPlayer(int id){
		currentPlayerID=id;
		currentPlayer=players[id];
	}

	void getClient(ClientGameMaster c){
		client=c;
	}
}
