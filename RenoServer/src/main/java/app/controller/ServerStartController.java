package app.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import app.ServerStart;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class ServerStartController implements Initializable {

	private Timeline clock;
	private ServerStart mainApp;

	@FXML
	private TextField txtServerPort;
	@FXML
	private TextField txtComputerName;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnOK;

	public void setMainApp(ServerStart mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        initialize - Run this before any controller method
	 * 
	 * @author BRG
	 *
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// clock - do this action at interval.
		clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
			LocalTime currentTime = LocalTime.now();
			System.out.println((currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond()));
		}), new KeyFrame(Duration.seconds(5)));

		clock.setCycleCount(Animation.INDEFINITE);

		txtServerPort.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					btnOK_OnAction(null);
				}
			}
		});
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        btnOK_OnAction - Call the StartServer method in main application
	 * 
	 * @author BRG
	 *
	 */

	@FXML
	public void btnOK_OnAction(ActionEvent event) {

		String strComputerName = "localhost";
		int iPort = Integer.parseInt(txtServerPort.getText());
		boolean bServer = true;
		mainApp.StartServer(bServer, strComputerName, iPort);

		btnOK.setDisable(true);
		btnCancel.setText("Stop");

		clock.play();
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 *        btnCancel_OnAction - Do this after the cancel button is clicked
	 * 
	 * @author BRG
	 *
	 */
	@FXML
	public void btnCancel_OnAction(ActionEvent event) {

		try {
			this.mainApp.stop();
		} catch (Exception e) {
		}

		Platform.exit();
		System.exit(0);
		System.out.println("End Program");
	}

}