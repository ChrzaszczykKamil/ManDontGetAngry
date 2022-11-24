package man_dont_get_angry;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server
{
	public static List <ServerGameMaster> allClients=new ArrayList <>();
	private ServerSocket server = null;
	private int playerCount;

	public Server(int port){
		try
		{
			server = new ServerSocket(port);
			playerCount=-1;
			System.out.println("Server started");
			System.out.println("Enter number of players (2-4):");
			Scanner scanner=new Scanner(System.in);
			while(playerCount!=2&&playerCount!=3&&playerCount!=4){
				try{
					playerCount=scanner.nextInt();
					if(playerCount!=2&&playerCount!=3&&playerCount!=4)
						System.out.println("Wrong number of players (2-4)");
				}catch(Exception e)
				{
					System.out.println("Not a number!");
					scanner.next();
				}
			}

			System.out.println("Waiting for a client ...");

			while(true){
			for(int i=0; i<playerCount; i++){
					Thread t= new ServerGameMaster(server);
					allClients.add((ServerGameMaster)t);
					((ServerGameMaster)t).write(playerCount);
					((ServerGameMaster)t).write(i);
					System.out.println("Client accepted");
					t.setDaemon(true);
					t.start();
				}
				for(int i=0; i<playerCount; i++){
					allClients.get(i).write(i);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	static void serverStop(){
		try{
			for(ServerGameMaster sgm: allClients)
			{
				try{
					sgm.write(9999);
				}catch(Exception ignored) {}
				sgm.interrupt();
			}
			allClients.clear();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new Server(5000);
	}
}
