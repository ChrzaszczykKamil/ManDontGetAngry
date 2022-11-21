package man_dont_get_angry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private Socket socket1 = null;
	private Socket socket2 = null;
	private ServerSocket server = null;
	private ObjectOutputStream out1 = null;
	private ObjectOutputStream out2 = null;
	private ObjectInputStream  in1 = null;
	private ObjectInputStream  in2 = null;
	private int playerCount;

	public Server(int port){
		try
		{
			playerCount=2;
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket1 = server.accept();
			out1 = new ObjectOutputStream(socket1.getOutputStream());
			out1.writeObject(playerCount);
			out1.writeObject(0);
			System.out.println("Client accepted");

			socket2 = server.accept();
			out2 = new ObjectOutputStream(socket2.getOutputStream());
			out2.writeObject(playerCount);
			out2.writeObject(1);
			System.out.println("Client accepted");

			// takes input from the client socket
			System.out.println("hah");

			in1 = new ObjectInputStream(socket1.getInputStream());
			System.out.println("lmao");

			in2 = new ObjectInputStream(socket2.getInputStream());
			System.out.println("lol");

			while (true)
			{
				try
				{
					System.out.println("XD");
					int currentPlayerID=(int)in1.readObject();
					boolean [][]podium=(boolean[][])in1.readObject();
					Dice dice=(Dice)in1.readObject();
					Player currentPlayer=(Player)in1.readObject();
					Player[] players=(Player[])in1.readObject();
					int numberOfPlayers = (int)in1.readObject();
					int [] pathStatus = (int[])in1.readObject();
					System.out.println("RECEIVED");

					out2.writeObject(currentPlayerID);
					System.out.println("SENT");
					break;
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}

			System.out.println("Closing connection");
			// close connection
			socket1.close();
			socket2.close();
			in1.close();
			in2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
