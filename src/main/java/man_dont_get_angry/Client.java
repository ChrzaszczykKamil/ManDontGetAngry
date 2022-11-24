package man_dont_get_angry;

import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client
{
	public static int clientID;
	private static Socket socket = null;
	ObjectInputStream in= null;
	ObjectOutputStream out=null;
	static Thread t;

	public Client(String address, int port, Scene scene)
	{
		try
		{
			int connectionTries=0;
			while(connectionTries<5){
				try
				{

					socket=new Socket(address, port);
					System.out.println("Connected");
					connectionTries=10;
				}catch(Exception e)
				{
					connectionTries++;
					Thread.sleep(2000);
				}
			}
			if(connectionTries!=10)
			{
					Alert alert=new Alert(Alert.AlertType.WARNING);
					alert.setTitle("WARNING!");
					alert.setHeaderText("COULD NOT CONNECT!");
					alert.showAndWait();
				return;
			}

			out=new ObjectOutputStream(socket.getOutputStream());
			in=new ObjectInputStream(socket.getInputStream());

			GameMaster.setPlayersCount((int)in.readObject());
			in.readObject();
			GameMaster g=new GameMaster(scene);
			clientID=(int)in.readObject();

			t= new ClientGameMaster(in, out);
			t.setDaemon(true);
			t.start();
			g.getClient((ClientGameMaster)t);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	static void clientStop(){
		try
		{
			t.interrupt();
			socket.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}