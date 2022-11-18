package man_dont_get_angry;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import static man_dont_get_angry.GameMaster.*;
import static man_dont_get_angry.MainVariables.sizeX;
public class Pawn
{

	ImageView pawnIV;
	int pawnID;
	int pathLocation;
	int x;
	int y;
	double startX;
	double startY;
	boolean deployed;
	int playerID;
	EventHandler <MouseEvent> eventHandler;

	public Pawn(int pawnID, int playerID, Image img){
		this.pawnID=pawnID;
		this.x=0;
		this.y=0;
		this.playerID=playerID;
		pathLocation=-1;
		deployed=false;
		pawnIV=new ImageView(img);
		eventHandler =e ->{
			if(!deployed)
			{
				if(dice.getRolledNumber()==6)
				{
					System.out.println("XD");
					System.out.println(playerID==0&&!isSpotTakenByAlly(0,playerID));
					System.out.println((playerID==2&&!isSpotTakenByAlly(10,playerID)));
					System.out.println((playerID==1&&!isSpotTakenByAlly(20,playerID)));
					System.out.println((playerID==3&&!isSpotTakenByAlly(30,playerID)));
					System.out.println(pathLocation);
					System.out.println("XD");
					if(playerID==0&&!isSpotTakenByAlly(0,playerID)
							||(playerID==2&&!isSpotTakenByAlly(10,playerID))
							||(playerID==1&&!isSpotTakenByAlly(20,playerID))
							||(playerID==3&&!isSpotTakenByAlly(30,playerID))){
						deployPawn();
						setNextPlayer();
					}
				}
			}
			else{
				if(moveToNext(dice.getRolledNumber()))
					setNextPlayer();
			}
		};
	}

	public void addEvent(){
		pawnIV.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	public void removeEvent(){
		pawnIV.removeEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
	}

	public ImageView getPawnIV()
	{
		return pawnIV;
	}

	public void moveByOne(){
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
		/*
		pawnIV.setX(sizeX/2-64*i+32*9);
		pawnIV.setY(sizeX/2-64*j+32*9);*/
	}

	public boolean moveToNext(int n){
		if(!isSpotTakenByAlly((pathLocation+n)%40, playerID)){
			for(int i=0; i<n;i++){
				moveByOne();
			}
			setPathSpot(pathLocation,-1);
			pathLocation=(pathLocation+n)%40;

			setPathSpot(pathLocation,playerID);
			return true;
		}
		System.out.println("TAKEN");
		return false;
	}

	public void pawnDied(){
		pathLocation=-1;
		x=-1;
		y=-1;
		deployed=false;
		pawnIV.setX(startX);
		pawnIV.setY(startY);
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
		setPathSpot(pathLocation,playerID);
		deployed=true;
	}

	public void setInitialXY(double startX, double startY){
		this.startX=startX;
		this.startY=startY;
	}

}
