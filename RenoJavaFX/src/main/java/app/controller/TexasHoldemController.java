package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import app.Poker;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import pkgCore.Action;
import pkgCore.Card;
import pkgCore.DrawResult;
import pkgCore.Player;
import pkgCore.Table;
import pkgCoreInterface.iCardDraw;
import pkgEnum.eAction;
import pkgEnum.eDrawCount;

public class TexasHoldemController implements Initializable {

	private int iAnimationLength = 150;
	@FXML
	private BorderPane parentNode;

	@FXML
	private Label PlayerLabel1;
	@FXML
	private Label PlayerLabel2;
	@FXML
	private Label PlayerLabel3;
	@FXML
	private Label PlayerLabel4;
	@FXML
	private Label PlayerLabel5;
	@FXML
	private Label PlayerLabel6;
	@FXML
	private Label PlayerLabel7;
	@FXML
	private Label PlayerLabel8;
	@FXML
	private Label PlayerLabel9;

	@FXML
	private HBox HBoxCardsp1;
	@FXML
	private HBox HBoxCardsp2;
	@FXML
	private HBox HBoxCardsp3;
	@FXML
	private HBox HBoxCardsp4;
	@FXML
	private HBox HBoxCardsp5;
	@FXML
	private HBox HBoxCardsp6;
	@FXML
	private HBox HBoxCardsp7;
	@FXML
	private HBox HBoxCardsp8;
	@FXML
	private HBox HBoxCardsp9;

	@FXML
	private HBox HBoxDeck;

	@FXML
	private HBox HBoxCommon;

	private Poker mainApp;

	@FXML
	private HBox hbProgressBarp4;

	public void setMainApp(Poker mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 * initialize - Run this when the controller starts
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClearCardBoxes();
	}

	/**
	 * @author BRG
	 * @version Lab #7
	 * @since Lab #7
	 * 
	 * ClearCardBoxes - Clear all the card boxes
	 */
	private void ClearCardBoxes() {

		HBoxCommon.getChildren().clear();
		for (int i = 0; i < 5; i++) {
			final ImageView imgBlank = BuildImage(-2, 0);
			HBoxCommon.getChildren().add(imgBlank);
		}
		for (Node n : getAllControls(parentNode, new HBox())) {
			HBox hb = (HBox) n;
			if (hb.getId() != null) {
				if (hb.getId().contains("HBoxCardsp")) {
					hb.getChildren().clear();
					for (int i = 0; i < 2; i++) {
						final ImageView imgBlank = BuildImage(-2, 0);
						hb.getChildren().add(imgBlank);
					}
				}
			}
		}
	}

	/**
	 * @author BRG
	 * @version Lab #7
	 * @since Lab #7
	 * 
	 * @param lstDrawResult
	 * 
	 * HandleDraw - Handle the draw result
	 */
	public void HandleDraw(ArrayList<DrawResult> lstDrawResult) {

		SequentialTransition MainTransition = new SequentialTransition();

		if (lstDrawResult.get(0).geteDrawCount() == eDrawCount.FIRST) {
			ClearCardBoxes();
		}

		for (DrawResult DR : lstDrawResult) {

			// This is the common cards
			if (DR.getiPlayerPosition() == 0) {
				ImageView iCardImg = BuildImage(DR.getiCardNbr(), 15);
				MainTransition.getChildren().add(DealCard(HBoxCommon, iCardImg, DR.getiCardPosition()));
			}
			// This is the player cards
			else if (DR.getiPlayerPosition() > 0) {

				ImageView iCardImg = BuildImage(DR.getiCardNbr(), 0);
				String strControl = "HBoxCardsp" + DR.getiPlayerPosition();
				Optional<Node> optNode = this.getSpecificControl(parentNode, strControl);
				HBox pCards = (HBox) optNode.get();
				MainTransition.getChildren().add(DealCard(pCards, iCardImg, DR.getiCardPosition()));
			}
		}

		MainTransition.play();
	}

	/**
	 * @author BRG
	 * @version Lab #7
	 * @since Lab #7
	 * 
	 * @param HBoxTarget
	 * @param iCardImg
	 * @param iCardNbr
	 * @return
	 * 
	 * SequentialTransition - Create a giant transition for dealing the card
	 */
	private SequentialTransition DealCard(HBox HBoxTarget, ImageView iCardImg, int iCardNbr) {

		Point2D pntDeck = FindPoint(HBoxDeck, 0);
		final ImageView img = BuildImage(0, 0);
		final ImageView imgBlank = BuildImage(-2, 0);
		img.setX(pntDeck.getX());
		img.setY(pntDeck.getY());

		parentNode.getChildren().add(img);

		SequentialTransition sT = new SequentialTransition();

		Point2D pntCardDealt = FindPoint(HBoxTarget, iCardNbr);

		// Transition should be... two parallel transitions in sequence
		
		// First parallel is transition/rotate card
		// Second parallel is to fade in/out

		// Create a Translate Transition
		TranslateTransition transT = CreateTranslateTransition(pntDeck, pntCardDealt, img);

		// Create a Rotate transition
		RotateTransition rotT = CreateRotateTransition(img);
		// Create a Scale transition (we're not using it, but this is how you do it)
		// ScaleTransition scaleT = CreateScaleTransition(iCardImg);
		
		// Create a Path transition (we're not using it, but this is how you do it)
		// PathTransition pathT = CreatePathTransition(pntDeck, pntCardDealt, img);

		// Create a new Parallel transition.
		ParallelTransition patTMoveRot = new ParallelTransition();
		// Add transitions you want to execute currently to the parallel transition
		patTMoveRot.getChildren().addAll(rotT, transT);

		// Create a new Parallel transition to fade in/fade out
		ParallelTransition patTFadeInFadeOut = createFadeTransition((ImageView) HBoxTarget.getChildren().get(iCardNbr),
				iCardImg.getImage());

		// Create a new sequential transition
		SequentialTransition seqDeal = new SequentialTransition();

		// Add the two parallel transitions to the sequential transition
		seqDeal.getChildren().addAll(patTMoveRot, patTFadeInFadeOut);

		// Set up event handler to remove the animation image after the transition is
		// complete
		seqDeal.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				parentNode.getChildren().remove(img);
			}
		});

		sT.getChildren().add(seqDeal);
		sT.setInterpolator(Interpolator.EASE_OUT);

		return sT;

	}

	/**
	 * HandleTableState - Run this method if the Table has changed.
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 * @param currentTable
	 */
	public void HandleTableState(Table currentTable) {

		if ((currentTable == null) || (currentTable.getTablePlayers() == null))
			return;

		// Blank out all the player labels
		for (Node n : getAllControls(parentNode, new Label())) {
			Label l = (Label) n;
			if ((l.getId() != null) && (l.getId().contains("PlayerLabel"))) {
				l.setText("");
			}
		}

		// Update the Player Label
		for (Player p : currentTable.getTablePlayers()) {
			String strLabel = "PlayerLabel" + p.getiPlayerPosition();
			SetLabelText(strLabel, p.getPlayerName());
		}

		// Am I sitting? If I am, the other buttons should be not visible
		// Am I sitting? If I am, the seated position should be 'Leave'
		// Am I not sitting? Every other button where someone is not sitting should be
		// 'Sit'.

		// iPlayerSittingCount - this will be 1 if the player is seated

		Optional<Player> SeatedAppPlayer = currentTable.getTablePlayers().stream()
				.filter(x -> x.getPlayerID().equals(this.mainApp.getAppPlayer().getPlayerID())).findFirst();

		for (Node n : getAllNodes(parentNode)) {
			if (n instanceof Button) {
				Button btnSit = (Button) n;
				if ((n.getId() != null) && (n.getId().contains("btnSit"))) {
					Optional<Player> pSeated = currentTable.getTablePlayers().stream()
							.filter(x -> x.getiPlayerPosition() == Integer
									.parseInt(btnSit.getId().substring(btnSit.getId().length() - 1)))
							.findFirst();

					// I'm not seated anywhere, no one is seated in this position
					if ((pSeated.isEmpty()) && (SeatedAppPlayer.isEmpty())) {
						btnSit.setText("Sit");
						btnSit.setVisible(true);
						btnSit.setDisable(false);
						// Someone is sitting at position, I'm sitting, It's me
					} else if ((pSeated.isPresent()) && (SeatedAppPlayer.isPresent()
							&& (pSeated.get().getPlayerID().equals(SeatedAppPlayer.get().getPlayerID())))) {
						btnSit.setText("Leave");
						btnSit.setVisible(true);
						btnSit.setDisable(false);
						// Someone is sitting at position, I'm sitting, it's not me
					} else if ((pSeated.isPresent() && (SeatedAppPlayer.isPresent()
							&& (!pSeated.get().getPlayerID().equals(SeatedAppPlayer.get().getPlayerID()))))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);

					} else if ((pSeated.isEmpty() && (SeatedAppPlayer.isPresent()))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);
					} else if ((pSeated.isPresent() && (SeatedAppPlayer.isEmpty()))) {
						btnSit.setVisible(false);
						btnSit.setDisable(true);
					}
				}
			}
		}

	}

	/**
	 * btnStartGame - Start the game
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * 
	 * @param event
	 */
	@FXML

	private void btnStartGame(ActionEvent event) {
		Action act = new Action(eAction.StartGamePoker, this.mainApp.getAppPlayer());
		this.mainApp.messageSend(act);
	}

	/**
	 * @author BRG
	 * @version Lab #7
	 * @since Lab #7
	 * 
	 * btnDraw_onAction - handle the draw action
	 */
	@FXML
	private void btnDraw_onAction() {
		Action act = new Action(eAction.Draw, this.mainApp.getAppPlayer());
		this.mainApp.messageSend(act);
	}

	/**
	 * btnSit - execute this action after the Sit/Leave button is clicked
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param event
	 */
	@FXML
	private void btnSitLeave(ActionEvent event) {

		// extract the button from the event
		Button btnSit = (Button) event.getSource();
		// Find the Player
		this.mainApp.getAppPlayer()
				.setiPlayerPosition(Integer.parseInt(btnSit.getId().substring(btnSit.getId().length() - 1)));

		eAction eA = (btnSit.getText().contentEquals("Sit") ? eAction.Sit : eAction.Leave);

		// Create an Action
		Action act = new Action(eA, this.mainApp.getAppPlayer());

		// Send the Action to the Hub
		this.mainApp.messageSend(act);
	}

	/**
	 * BuildImage - Create an image view for a given iCardNbr
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param iCardNbr
	 * @return
	 */
	private ImageView BuildImage(int iCardNbr, int iRotate) {

		String strImgPath = null;
		int iWidth = 72;
		int iHeight = 96;
		switch (iCardNbr) {
		case -1:
			strImgPath = "/img/b1fh.png";
			break;
		case -2:
			strImgPath = "/img/blank.png";
			break;
		case 0:
			strImgPath = "/img/b1fv.png";
			break;
		default:
			strImgPath = "/img/" + iCardNbr + ".png";
		}
		ImageView iCardImageView = new ImageView(
				new Image(getClass().getResourceAsStream(strImgPath), iWidth, iHeight, true, true));
		iCardImageView.setRotate(iRotate);
		return iCardImageView;
	}

	/**
	 * @author BRG
	 * @version Lab #7
	 * @since Lab #7
	 * 
	 * getSpecificControl - Find a specific control by ID
	 * 
	 * @param root  - the outer/parent container
	 * @param strID - the ID of the control
	 * @return
	 */
	private Optional<Node> getSpecificControl(Parent root, String strID) {
		ArrayList<Node> allNodes = getAllNodes(root);
		Optional<Node> n = allNodes.stream().filter(x -> x.getId() != null).filter(x -> x.getId().equals(strID))
				.findFirst();
		return n;
	}

	/**
	 * getAllControls - get all controls of a particular control type, starting with
	 * a given root
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root - root control
	 * @param o    - instance of given control type
	 * @return
	 */
	private ArrayList<Node> getAllControls(Parent root, Object o) {
		ArrayList<Node> nodeControl = new ArrayList<Node>();
		for (Node n : getAllNodes(parentNode)) {
			if (n.getClass() == o.getClass()) {
				nodeControl.add(n);
			}
		}
		return nodeControl;
	}

	/**
	 * getAllNodes - Find all the nodes (controls) in the form starting with the
	 * outer frame
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root - root control
	 * @return
	 */
	private ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}

	/**
	 * addAllDescendents - Find all controls within a root
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param root
	 * @return
	 */
	private void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}

	/**
	 * SetLabelText - Find the control... set the text
	 * 
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param strLabelName
	 * @param strText
	 */
	private void SetLabelText(String strLabelName, String strText) {
		ArrayList<Node> nodes = getAllNodes(parentNode);

		for (Node n : nodes) {
			if (n instanceof Label) {
				Label l = (Label) n;
				if (l.getId().equals(strLabelName)) {
					l.setText(strText);
				}
			}
		}
	}


	private Point2D FindPoint(HBox hBoxCard, int iCardNbr) {

		ImageView imgvCardFaceDown = (ImageView) hBoxCard.getChildren().get(iCardNbr);
		Bounds bndCardDealt = imgvCardFaceDown.localToScene(imgvCardFaceDown.getBoundsInLocal());
		Bounds boundsInScreen = imgvCardFaceDown.localToScreen(imgvCardFaceDown.getBoundsInLocal());
		Point2D pntCardDealt = new Point2D(bndCardDealt.getMinX(), bndCardDealt.getMinY());
		return pntCardDealt;
	}

	/**
	 * @author BRG
	 * @version Lab #6
	 * @since Lab #6
	 * @param event
	 * 
	 * btnCheck - temporary... have this event to check where I am
	 */
	@FXML
	private void btnCheck(ActionEvent event) {

		//	I'm using this method to figure out where points are on the screen
		
		Point2D p2d = FindPoint(HBoxCardsp4, 0);
		Point2D p2dDealer = FindPoint(HBoxCommon, 0);
		Point2D p2dDeck = FindPoint(HBoxDeck, 0);

		Circle c = new Circle(p2d.getX(), p2d.getY(), 15);
		Circle cD = new Circle(p2dDealer.getX(), p2dDealer.getY(), 5);
		Circle cDeck = new Circle(p2dDeck.getX(), p2dDeck.getY(), 5);

		c.setFill(Color.DARKRED);
		c.setRotate(-10);
		cD.setFill(Color.YELLOW);
		cDeck.setFill(Color.LIGHTSEAGREEN);

		parentNode.getChildren().add(c);
		parentNode.getChildren().add(cD);
		parentNode.getChildren().add(cDeck);
	}

	private PathTransition CreatePathTransition(Point2D fromPoint, Point2D toPoint, ImageView img) {
		Path path = new Path();

		// TODO: Fix the Path transition. My Path looks terrible... do something cool :)

		path.getElements().add(new MoveTo(fromPoint.getX(), fromPoint.getY()));
		path.getElements().add(new CubicCurveTo(toPoint.getX() * 2, toPoint.getY() * 2, toPoint.getX() / 3,
				toPoint.getY() / 3, toPoint.getX(), toPoint.getY()));
		// path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(750));
		pathTransition.setPath(path);
		pathTransition.setNode(img);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount((int) 1f);
		pathTransition.setAutoReverse(false);

		return pathTransition;

	}

	private ScaleTransition CreateScaleTransition(ImageView img) {
		ScaleTransition st = new ScaleTransition(Duration.millis(iAnimationLength), img);
		st.setByX(.25f);
		st.setByY(.25f);
		st.setCycleCount((int) 1f);
		st.setAutoReverse(true);

		return st;
	}

	private RotateTransition CreateRotateTransition(ImageView img) {

		RotateTransition rotateTransition = new RotateTransition(Duration.millis(iAnimationLength / 4), img);
		rotateTransition.setByAngle(180F);
		rotateTransition.setCycleCount(2);
		rotateTransition.setAutoReverse(false);

		return rotateTransition;
	}

	private TranslateTransition CreateTranslateTransition(Point2D fromPoint, Point2D toPoint, ImageView img) {

		Circle c1 = new Circle(fromPoint.getX(), fromPoint.getY(), 5);
		Circle c2 = new Circle(toPoint.getX(), toPoint.getY(), 5);
		c1.setFill(Color.DARKRED);
		c2.setFill(Color.LIGHTPINK);
		parentNode.getChildren().add(c1);
		parentNode.getChildren().add(c2);

		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(iAnimationLength), img);

		translateTransition.setFromX(0);
		translateTransition.setToX(toPoint.getX() - fromPoint.getX());
		translateTransition.setFromY(0);
		translateTransition.setToY(toPoint.getY() - fromPoint.getY());
		translateTransition.setCycleCount(1);
		translateTransition.setAutoReverse(false);

		return translateTransition;
	}

	private ParallelTransition createFadeTransition(final ImageView imgVFadeOut, final Image imgFadeIn) {

		FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(iAnimationLength), imgVFadeOut);
		fadeOutTransition.setFromValue(1.0);
		fadeOutTransition.setToValue(0.0);
		fadeOutTransition.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				imgVFadeOut.setImage(imgFadeIn);
			}
		});

		FadeTransition fadeInTransition = new FadeTransition(Duration.millis(iAnimationLength), imgVFadeOut);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		ParallelTransition parallelTransition = new ParallelTransition();
		parallelTransition.getChildren().addAll(fadeOutTransition, fadeInTransition);
		return parallelTransition;
	}
}
