package man_dont_get_angry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static man_dont_get_angry.Server.allClients;
import static man_dont_get_angry.Server.serverStop;

public class ServerGameMaster extends Thread
{
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket socket;

	public ServerGameMaster(ServerSocket server)
	{
		try
		{
			socket=server.accept();
			this.out=new ObjectOutputStream(socket.getOutputStream());
			this.in=new ObjectInputStream(socket.getInputStream());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				if(!Thread.currentThread().isInterrupted())
				{
					int x=(int)in.readObject();
					sendToEveryone(x);
				}else{
					return;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			serverStop();
		}
	}
	public void write(int x) throws IOException
	{
			out.writeObject(x);
	}

	public void sendToEveryone(int x) throws IOException
	{
			for(ServerGameMaster sgm: allClients)
			{
				if(sgm!=this)
					sgm.write(x);
			}
	}
}