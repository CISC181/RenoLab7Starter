package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import app.controller.ClientStartController;
import app.controller.TexasHoldemController;
//import app.controller.ServerStartController;
//import app.hub.GameHub;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netgame.common.Client;
import pkgCore.Action;
import pkgCore.DrawResult;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Table;
import pkgEnum.eAction;
import util.PropertyUtil;


public class Poker extends Application {

	private static Properties props = null;
	private Stage primaryStage;
	private GameClient gClient = null;
	private Player appPlayer;
	
	private TexasHoldemController PokerController;
	

	public Player getAppPlayer() {
		return appPlayer;
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        main - entry point for the application
	 * 
	 * @author BRG
	 *
	 */
	public static void main(String[] args) {
		
		PropertyUtil properties = new PropertyUtil();		
		try {
			props = properties.getPropertyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		launch(args);
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        start - override 'start' in application, set the primarystage
	 * 
	 * @author BRG
	 *
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		showServer(primaryStage);

	}
	
	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        showServer - show the server form
	 * 
	 * @author BRG
	 *
	 */
	public void showServer(Stage primaryStage) {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();

			loader = new FXMLLoader(getClass().getResource("/Client/app/view/ClientStart.fxml"));
			BorderPane ClientServerOverview = (BorderPane) loader.load();
			Scene scene = new Scene(ClientServerOverview);
			primaryStage.setScene(scene);
			ClientStartController controller = loader.getController();
			controller.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void showPoker() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();

			loader = new FXMLLoader(getClass().getResource("/Client/app/view/TexasHoldemBoard.fxml"));
			BorderPane PokerOverview = (BorderPane) loader.load();
			Scene scene = new Scene(PokerOverview);
			primaryStage.setTitle("Player: " + this.appPlayer.getPlayerName());
			primaryStage.setScene(scene);
			
			PokerController = loader.getController();
			PokerController.setMainApp(this);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        StartServer - Start the server socket.
	 * 
	 * @author BRG
	 *
	 */	
	public void StartClient(String strComputerName, int iPort, String strPlayerName) {
		try {
			gClient = new GameClient(strComputerName, iPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		appPlayer = new Player(strPlayerName, gClient.getID());
		showPoker();
		
		Action act = new Action(eAction.TableState, this.getAppPlayer());		
		messageSend(act);
		
		System.out.println(appPlayer.getClientID());
	}
	

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        messageSend - Call this method to send a message (Client to Hub)
	 * 
	 * @author BRG
	 *
	 */		
	public void messageSend(final Object message) {
		System.out.println("Sending message from MainApp");
		gClient.messageSend(message);
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        stop - Kill the server socket if client stops program.
	 * 
	 * @author BRG
	 *
	 */	
	@Override
	public void stop() throws Exception {		
		gClient.serverShutdown("Client Exit");
		super.stop();
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        GameClient - Private inner class to extend Client class
	 * 
	 * @author BRG
	 *
	 */		
	private class GameClient extends Client {

		public GameClient(String hubHostName, int hubPort) throws IOException {
			super(hubHostName, hubPort);
		}

		/**
		 * @author BRG
		 * @version Lab #6
		 * @since Lab #6
		 * 
		 *        messageSend - Send a message (Hub to client)
		 * 
		 * @author BRG
		 *
		 */	
		protected void messageSend(Object message) {
			resetOutput();
			super.send(message);
		}

		/**
		 * @author BRG
		 * @version Lab #6
		 * @since Lab #6
		 * 
		 * messageReceived - A message received from the Client is
		 * sent by the Hub.  
		 * The following messgaes will be handled...
		 * 
		 * GamePlay - call HandleGamePlayMessage
		 * Table - call HandleTableMessage
		 * 
		 * Anything else will be ignored
		 * 
		 * Call the appropriate controller methods 
		 */
		@Override
		protected void messageReceived(final Object message) {
			Platform.runLater(() -> {
				System.out.println("Message Received.  The message: " + message);

				if (message instanceof String) {
					System.out.println("Message Received from hub " + message);
				}
				else if (message instanceof ArrayList)
				{
					PokerController.HandleDraw((ArrayList<DrawResult>) message);
				}
				else if (message instanceof GamePlay) {
					
				}
				else if (message instanceof Table) {
					PokerController.HandleTableState((Table)message);
				}

			});
		}

		
		
		/**
		 * @author BRG
		 * @version Lab #6
		 * @since Lab #6
		 * 
		 * serverShutdown - run this when shutdown is detected.
		 * 
		 * Server is going to send any "Action" message to the Hub.
		 */
		
		protected void serverShutdown(String message) {

			Platform.runLater(() -> {
				Platform.exit();
				System.exit(0);
			});
		}

	}

}