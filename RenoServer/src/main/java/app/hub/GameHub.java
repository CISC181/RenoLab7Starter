package app.hub;

import java.io.IOException;
import java.util.ArrayList;

import netgame.common.Hub;
import pkgCore.Action;
import pkgCore.DrawResult;
import pkgCore.GamePlay;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgEnum.eAction;
import pkgEnum.eGame;
import pkgException.DeckException;
import pkgException.HandException;

public class GameHub extends Hub {

	private Table HubPokerTable = null;
	private GamePlay HubGamePlay = null;

	public GameHub(int port) throws IOException {
		super(port);
		this.setAutoreset(true);
	}

	@Override
	public void messageReceived(int ClientID, Object message) {

		if (HubPokerTable == null)
			HubPokerTable = new Table("Poker");
		
		if (message instanceof Action)
		{
			Action act = (Action)message;
			
			switch (act.geteAction())
			{
			case TableState:
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case Sit:
				HubPokerTable.AddPlayerToTable(act.getActPlayer());
					resetOutput();
 				sendToAll(HubPokerTable);
				break;
			case Leave:
				HubPokerTable.RemovePlayerFromTable(act.getActPlayer());
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case Bet:
				break;
			case Deal:
				break;
			case Fold:
				break;
			case GameState:
				break;
			case Raise:
				break;
			case ScoreGame:
				break;
			case StartGamePoker:
				try {
					HubGamePlay = new GamePlay(HubPokerTable, new Rule(eGame.TexasHoldEm));
					HubGamePlay.StartGame();
				} catch (DeckException e) {
					e.printStackTrace();
				} catch (HandException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}				
			case Draw:
				try {
					HubGamePlay.Draw();
				} catch (DeckException e) {
					e.printStackTrace();
				} catch (HandException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}				
				for (Player p: this.HubGamePlay.getGamePlayers())
				{					
					ArrayList<DrawResult> DR = HubGamePlay.getDrawResult(p);
					resetOutput();
					sendToOne(p.getClientID(),DR);
				}				
				break;				
			default:
				break;
			}
		}
		
		
		
		
		
		
		
//		if (HubPokerTable == null)
//			HubPokerTable = new Table();
//		
//		if (message instanceof Action) {
//			
//			Action a = (Action)message;
//
//			switch (a.geteAction()) {
//			case Sit:
//				HubPokerTable.AddPlayerToTable(a.getActPlayer());
//				resetOutput();
//				sendToAll(HubPokerTable);				
//				break;
//			case Leave:
//				HubPokerTable.RemovePlayerFromTable(a.getActPlayer());
//				resetOutput();
//				sendToAll(HubPokerTable);
//				break;
//			case TableState:
//				resetOutput();
//				sendToAll(HubPokerTable);
//			case GameState:
//				//TODO: Implement this
//				break;
//			case StartGameBlackJack:
//				//TODO: Implement this	
//				eGameType = eGameType.BLACKJACK;
//
//				break;
//			case Draw:
//				//TODO: Implement this
//				break;
//			}

		// }
	}

}