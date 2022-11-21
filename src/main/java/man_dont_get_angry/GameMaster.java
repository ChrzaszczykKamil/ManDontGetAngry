package man_dont_get_angry;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

import static man_dont_get_angry.Client.getSocket;
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

	private static void clearLevel(){
		System.out.println("WINNER");
		new EndGameScreen(root, currentPlayer);
	}

	public static void setNextPlayer(){
		if(checkWin())
		{
			sendUpdate();
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
		sendUpdate();
	}

	public static void sendUpdate(){
		Socket socket=getSocket();
		ObjectOutputStream out =null;
		try
		{
			out=new ObjectOutputStream(socket.getOutputStream());

		out.writeObject(currentPlayerID);
			out.writeObject(podium);
			out.writeObject(dice);
			out.writeObject(currentPlayer);
			out.writeObject(players);
			out.writeObject(numberOfPlayers);
			out.writeObject(pathStatus);

			out.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void getUpdate(){

	}
}
