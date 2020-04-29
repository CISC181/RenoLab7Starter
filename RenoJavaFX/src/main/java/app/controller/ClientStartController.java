package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Poker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClientStartController implements Initializable {

	@FXML
	private TextField txtPlayerName;
	@FXML
	private TextField txtClientPort;
	@FXML
	private TextField txtComputerName;

	private Poker mainApp;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		// This is the crazy way you have to code to make an item
		// the default selection / focus
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txtPlayerName.requestFocus();
			}
		});
		
		txtPlayerName.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					btnOK_OnAction(null);
				}
			}
		});
	}

	public void setMainApp(Poker mainApp) {
		this.mainApp = mainApp;
	}


	/**
	 * btnOK - Execute this action when the 'OK' button is clicked.
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param event
	 */
	@FXML
	public void btnOK_OnAction(ActionEvent event) {
 
		boolean bServer = false;
		String strComputerName = txtComputerName.getText();
		int iPort = Integer.parseInt(txtClientPort.getText());
		mainApp.StartClient(strComputerName, iPort, txtPlayerName.getText());
	}

	/**
	 * btnCancel - Execute this action when the 'Cancel' button is clicked.
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param event
	 */
	@FXML
	public void btnCancel(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

}