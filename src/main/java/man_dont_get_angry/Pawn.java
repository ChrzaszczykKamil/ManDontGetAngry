package man_dont_get_angry;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

import static man_dont_get_angry.Client.clientID;
import static man_dont_get_angry.GameMaster.*;
import static man_dont_get_angry.MainVariables.sizeX;

public class Pawn implements Serializable
{
	private final transient EventHandler <MouseEvent> eventHandler;
	private final transient ImageView pawnIV;
	private final int playerID;
	private int pathLocation;
	private int traveled;
	private int x;
	private int y;
	private int podiumPlace;
	private double startX;
	private double startY;
	private boolean deployed;
	private boolean isOnPodium;

	public Pawn(int playerID, Image img){
		this.x=0;
		this.y=0;
		this.playerID=playerID;

		traveled=0;
		pathLocation=-1;
		podiumPlace=-1;
		isOnPodium=false;
		deployed=false;

		pawnIV=new ImageView(img);
		eventHandler =e ->{
			if(isOffline ||currentPlayerID==clientID)
			{
				if(!deployed)
				{
					deployPawn();
					setTheSamePlayer();
				}
				else if(isOnPodium)
				{
					moveToPodium(podiumPlace+dice.getRolledNumber());
					setNextPlayer();
				}
				else if(traveled+dice.getRolledNumber()<=40)
				{
					moveToNext(dice.getRolledNumber());
					setNextPlayer();
				}
				else
				{
					moveToPodium((traveled+dice.getRolledNumber())-41);
					setNextPlayer();
				}
			}
		};
	}

	public void setInitialXY(double startX, double startY){
		this.startX=startX;
		this.startY=startY;
	}

	public void addEvent(){
		pawnIV.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	public void removeEvent(){
		pawnIV.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	private void deployPawn(){
		if(playerID==0){//green
			pathLocation=0;
			x=0;
			y=4;
			pawnIV.setX(sizeX/2-64*6+32);
			pawnIV.setY(sizeX/2-64-32);
		}else if(playerID==1){//yellow
			pathLocation=20;
			x=10;
			y=6;
			pawnIV.setX(sizeX/2+64*4+32);
			pawnIV.setY(sizeX/2+64-32);
		}else if(playerID==2){//blue
			pathLocation=10;
			x=6;
			y=0;
			pawnIV.setX(sizeX/2+32);
			pawnIV.setY(sizeX/2-64*5-32);
		}else{//red
			pathLocation=30;
			x=4;
			y=10;
			pawnIV.setX(sizeX/2-64*2+32);
			pawnIV.setY(sizeX/2+64*5-32);
		}
		traveled=1;
		setPathSpot(pathLocation,playerID);
		deployed=true;
	}

	private void moveByOne(){
		if(y==4){
			if(x<4||(x>5&&x<10)) {
				x++;
				pawnIV.setX(pawnIV.getX()+64);
			}
			else if(x==4){
				y--;
				pawnIV.setY(pawnIV.getY()-64);
			}
			else if(x==10){
				y++;
				pawnIV.setY(pawnIV.getY()+64);
			}
		}
		else if(y==6){
			if((x<5&&x>0)||x>6) {
				x--;
				pawnIV.setX(pawnIV.getX()-64);
			}
			else if(x==6){
				y++;
				pawnIV.setY(pawnIV.getY()+64);
			}
			else if(x==0){
				y--;
				pawnIV.setY(pawnIV.getY()-64);
			}
		}else if(y==5){
			if(x==0){
				y--;
				pawnIV.setY(pawnIV.getY()-64);
			}else{
				y++;
				pawnIV.setY(pawnIV.getY()+64);
			}
		}else if(y==0){
			if(x==6)
			{
				y++;
				pawnIV.setY(pawnIV.getY()+64);
			}else{
				x++;
				pawnIV.setX(pawnIV.getX()+64);
			}
		}else if(y==10){
			if(x==4)
			{
				y--;
				pawnIV.setY(pawnIV.getY()-64);
			}else{
				x--;
				pawnIV.setX(pawnIV.getX()-64);
			}
		}else{
			if(x==4){
				y--;
				pawnIV.setY(pawnIV.getY()-64);
			}else{
				y++;
				pawnIV.setY(pawnIV.getY()+64);
			}
		}
	}

	private void moveToNext(int n) {
		for(int i=0; i<n; i++)
		{
			moveByOne();
		}
		setPathSpot(pathLocation, -1);
		pathLocation=(pathLocation+n)%40;
		traveled+=n;

		setPathSpot(pathLocation, playerID);
	}

	private void moveToPodium(int podiumSpot){
		isOnPodium=true;
		if(pathLocation!=-1)
			setPathSpot(pathLocation, -1);
		pathLocation=-1;
		podium[playerID][podiumSpot]=true;
		if(podiumPlace!=-1){
			podium[playerID][podiumPlace]=false;
		}
		podiumPlace=podiumSpot;
		if(playerID==0) {
			if(podiumSpot==0)
				pawnIV.setX(sizeX/2-64*5+32);
			else if(podiumSpot==1)
				pawnIV.setX(sizeX/2-64*4+32);
			else if(podiumSpot==2)
				pawnIV.setX(sizeX/2-64*3+32);
			else if(podiumSpot==3)
				pawnIV.setX(sizeX/2-64*2+32);
			pawnIV.setY(sizeX/2-32);
		}
		else if(playerID==1) {
			if(podiumSpot==0)
				pawnIV.setX(sizeX/2+64*3+32);
			else if(podiumSpot==1)
				pawnIV.setX(sizeX/2+64*2+32);
			else if(podiumSpot==2)
				pawnIV.setX(sizeX/2+64+32);
			else if(podiumSpot==3)
				pawnIV.setX(sizeX/2+32);
			pawnIV.setY(sizeX/2-32);
		}
		else if(playerID==2) {
			if(podiumSpot==0)
				pawnIV.setY(sizeX/2-64*4-32);
			else if(podiumSpot==1)
				pawnIV.setY(sizeX/2-64*3-32);
			else if(podiumSpot==2)
				pawnIV.setY(sizeX/2-64*2-32);
			else if(podiumSpot==3)
				pawnIV.setY(sizeX/2-64-32);
			pawnIV.setX(sizeX/2-32);
		}
		else{
			if(podiumSpot==0)
				pawnIV.setY(sizeX/2+64*4-32);
			else if(podiumSpot==1)
				pawnIV.setY(sizeX/2+64*3-32);
			else if(podiumSpot==2)
				pawnIV.setY(sizeX/2+64*2-32);
			else if(podiumSpot==3)
				pawnIV.setY(sizeX/2+64-32);
			pawnIV.setX(sizeX/2-32);
		}
	}

	public void pawnDied(){
		pathLocation=-1;
		x=-1;
		y=-1;
		deployed=false;
		traveled=0;
		pawnIV.setX(startX);
		pawnIV.setY(startY);
	}

	public boolean canPawnMove(){
		if(isOnPodium){
			return isPodiumSpotFree(podiumPlace+dice.getRolledNumber(), playerID);
		}
		else if(deployed){
			if(traveled+dice.getRolledNumber()<=40)
			{
				return isSpotFree((pathLocation+dice.getRolledNumber())%40, playerID);
			}
			else{
				return isPodiumSpotFree((traveled+dice.getRolledNumber())-41, playerID);
			}
		}
		else{
			if(dice.getRolledNumber()==6){
				return playerID==0 && isSpotFree(0, playerID)
						|| (playerID==2 && isSpotFree(10, playerID))
						|| (playerID==1 && isSpotFree(20, playerID))
						|| (playerID==3 && isSpotFree(30, playerID));
			}
			else{
				return false;
			}
		}
	}

	public int getPathLocation() {
		return pathLocation;
	}

	public ImageView getPawnIV() {
		return pawnIV;
	}
}
