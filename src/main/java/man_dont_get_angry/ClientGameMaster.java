package man_dont_get_angry;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static man_dont_get_angry.GameMaster.setData;
import static man_dont_get_angry.Menu.menuRoot;
import static man_dont_get_angry.Menu.scene;

public class ClientGameMaster extends Thread
{

	int playerCount;
	ObjectInputStream in;
	ObjectOutputStream out;

	public ClientGameMaster(ObjectInputStream in, ObjectOutputStream out)
	{
		this.playerCount=GameMaster.getPlayersCount();
		this.in=in;
		this.out=out;
	}

	@Override
	public void run()
	{
		try{
			while(true)
			{
				if(!Thread.currentThread().isInterrupted())
				{
					int x=(int)in.readObject();
					if(x==9999)
					{
						Platform.runLater(()->{
							Alert alert=new Alert(Alert.AlertType.WARNING);
							alert.setTitle("WARNING!");
							alert.setHeaderText("ANOTHER PLAYER DISCONNECTED!");
							alert.showAndWait();
							scene.setRoot(menuRoot);
						});
						interrupt();
						return;
					}
					setData(x);
				}else{
					return;
				}
			}
		}catch(Exception e)
		{

			Platform.runLater(()->{
				Alert alert=new Alert(Alert.AlertType.WARNING);
				alert.setTitle("WARNING!");
				alert.setHeaderText("SERVER DISCONNECTED!");
				alert.showAndWait();
				scene.setRoot(menuRoot);
			});
			interrupt();
			return;
		}
	}

	void write (int x){
		try
		{
			out.writeObject(x);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}