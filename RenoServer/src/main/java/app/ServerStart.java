package app;

import java.io.IOException;

import app.controller.ServerStartController;
import app.hub.GameHub;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import netgame.common.Client;
import pkgCore.Action;

public class ServerStart extends Application {

	private Stage primaryStage;
	private GameHub gHub = null;
	private GameClient gClient = null;

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

			loader = new FXMLLoader(getClass().getResource("/Server/app/view/ServerStart.fxml"));
			BorderPane ClientServerOverview = (BorderPane) loader.load();
			Scene scene = new Scene(ClientServerOverview);
			primaryStage.setScene(scene);
			ServerStartController controller = loader.getController();
			controller.setMainApp(this);
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
	public void StartServer(boolean bStartHub, String strComputerName, int iPort) {
		if (bStartHub) {
			try {
				gHub = new GameHub(iPort);

			} catch (Exception e) {
				System.out.println("Error: Can't listen on port " + iPort);
				return;
			}
		}
		try {
			gClient = new GameClient(strComputerName, iPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		 * messageReceived will get an Object message... it's up to you to determine
		 * what should happen to that the message.
		 * 
		 * Server is going to send any "Action" message to the Hub.
		 */
		@Override
		protected void messageReceived(final Object message) {
			Platform.runLater(() -> {
				System.out.println("Message Received.  The message: " + message);

				if (message instanceof String) {
					System.out.println("Message Received from hub " + message);
				}

				else if (message instanceof Action) {
					gHub.messageReceived(((Action) message).getActPlayer().getClientID(), message);
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