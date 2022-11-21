package man_dont_get_angry;

import javafx.scene.Scene;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
	public static int clientID;
	private static Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out=null;

	public Client(String address, int port, Scene scene)
	{
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");
			out = new ObjectOutputStream(socket.getOutputStream());

			in = new ObjectInputStream(socket.getInputStream());
			GameMaster.setPlayersCount((int)in.readObject());
			new GameMaster(scene);
			clientID=(int)in.readObject();
		}
		catch(Exception i)
		{
			System.out.println(i);
		}
	}

	public static Socket getSocket()
	{
		return socket;
	}
}