package pkgGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import pkgCore.Card;
import pkgCore.DrawResult;
import pkgCore.GamePlay;
import pkgCore.HandPoker;
import pkgCore.HandScorePoker;
import pkgCore.Player;
import pkgCore.Rule;
import pkgCore.Table;
import pkgCoreInterface.iCardDraw;
import pkgEnum.eDrawCount;
import pkgEnum.eGame;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;
import pkgHelper.GamePlayHelper;
import pkgHelper.HandPokerHelper;

public class GamePlayTest {
 
	@Test
	public void GamePlay_Test1() {
		
		//	Create new table
		Table t = new Table("Table 1");
		
		//	Create two new players 
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		//	Add players to the table
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		//	Create a new GamePlay with Rule for Texas Hold 'em
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		try {
			gp.StartGame();
		} catch (DeckException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HandException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	Get the Player hand (should be empty at this point) from GamePlay.
		HandPoker hp1 = gp.GetPlayersHand(p1);		
		HandPoker hp2 = gp.GetPlayersHand(p2);
		
		//	Create P1 cards
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.HEARTS, eRank.ACE));
		p1Cards.add(new Card(eSuit.HEARTS, eRank.ACE));
		
		//	Create p2 cards	
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		
		//	Create common cards
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.HEARTS, eRank.THREE));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FOUR));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FIVE));
		commonCards.add(new Card(eSuit.CLUBS, eRank.TEN));	
		commonCards.add(new Card(eSuit.CLUBS, eRank.QUEEN));	
		gp = GamePlayHelper.setCommonCards(gp,  commonCards);
		

		//	Set the HandPoker with known cards
		hp1 = HandPokerHelper.SetHand(p1Cards, hp1);
		hp2 = HandPokerHelper.SetHand(p2Cards, hp2);
		
		//	Set the Hands in GamePlay
		gp = GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);		
		gp = GamePlayHelper.PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		try {
			gp.EvaluateGameHands();
		} catch (HandException e) {
			fail("Evaluate hands failed");
		}

		ArrayList<Player> pWinner = null;
		try {
			pWinner = gp.GetGameWinners();
		} catch (HandException e) {
			fail("GetGameWinner Failed");
		}
		
		try {
			PrintHandScore(gp.getWinningScore());
		} catch (HandException e) {
			fail("Get winning score failed");
		}
		
		//Player 1 had a flush, they should win
		assertTrue(pWinner.contains(p1));
		
	}

	@Test
	public void GamePlay_Test2() {
		
		//	Create new table
		Table t = new Table("Table 1");
		
		//	Create two new players 
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		//	Add players to the table
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		//	Create a new GamePlay with Rule for Texas Hold 'em
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		try {
			gp.StartGame();
		} catch (DeckException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (HandException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	Get the Player hand (should be empty at this point) from GamePlay.
		HandPoker hp1 = gp.GetPlayersHand(p1);		
		HandPoker hp2 = gp.GetPlayersHand(p2);
		
		//	Create P1 cards
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.TWO));
		
		//	Create p2 cards	
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE));
		p2Cards.add(new Card(eSuit.CLUBS, eRank.TWO));
		
		//	Create common cards
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.HEARTS, eRank.THREE));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FOUR));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FIVE));
		commonCards.add(new Card(eSuit.CLUBS, eRank.TEN));	
		commonCards.add(new Card(eSuit.CLUBS, eRank.QUEEN));	
		gp = GamePlayHelper.setCommonCards(gp,  commonCards);
		

		//	Set the HandPoker with known cards
		hp1 = HandPokerHelper.SetHand(p1Cards, hp1);
		hp2 = HandPokerHelper.SetHand(p2Cards, hp2);
		
		//	Set the Hands in GamePlay
		gp = GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);		
		gp = GamePlayHelper.PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		try {
			gp.EvaluateGameHands();
		} catch (HandException e) {
			fail("Evaluate hands failed");
		}

		ArrayList<Player> pWinner = null;
		try {
			pWinner = gp.GetGameWinners();
		} catch (HandException e) {
			fail("GetGameWinner Failed");
		}
		
		try {
			PrintHandScore(gp.getWinningScore());
		} catch (HandException e) {
			fail("Get winning score failed");
		}
		
		//Tie Game...  same suit...  this should be scored a tie
		assertTrue(pWinner.contains(p1));
		assertTrue(pWinner.contains(p2));
		
	}

	@Test
	public void GamePlay_Test_LastDraw()
	{
		//	Create new table
		Table t = new Table("Table 1");
		
		//	Create two new players 
		Player p1 = new Player("Bert");
		Player p2 = new Player("Joe");
		//	Add players to the table
		t.AddPlayerToTable(p1);
		t.AddPlayerToTable(p2);

		//	Create a new GamePlay with Rule for Texas Hold 'em
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);
		
		try {
			gp.StartGame();
		} catch (DeckException e1) {
			fail("Deck Exception");
		} catch (HandException e1) {
			fail("Hand Exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//	Get the Player hand (should be empty at this point) from GamePlay.
		HandPoker hp1 = gp.GetPlayersHand(p1);		
		HandPoker hp2 = gp.GetPlayersHand(p2);
		
		//	Create P1 cards
		ArrayList<Card> p1Cards = new ArrayList<Card>();
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.ACE,52));
		p1Cards.add(new Card(eSuit.DIAMONDS, eRank.TWO,40));
		
		//	Create p2 cards	
		ArrayList<Card> p2Cards = new ArrayList<Card>();
		p2Cards.add(new Card(eSuit.CLUBS, eRank.ACE,39));
		p2Cards.add(new Card(eSuit.CLUBS, eRank.TWO,27));
		
		//	Create common cards
		ArrayList<Card> commonCards = new ArrayList<Card>();
		commonCards.add(new Card(eSuit.HEARTS, eRank.THREE,2));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FOUR,3));
		commonCards.add(new Card(eSuit.HEARTS, eRank.FIVE,4));
		commonCards.add(new Card(eSuit.CLUBS, eRank.TEN,35));	
		commonCards.add(new Card(eSuit.CLUBS, eRank.QUEEN,37));	
		gp = GamePlayHelper.setCommonCards(gp,  commonCards);
		

		//	Set the HandPoker with known cards
		hp1 = HandPokerHelper.SetHand(p1Cards, hp1);
		hp2 = HandPokerHelper.SetHand(p2Cards, hp2);
		
		//	Set the Hands in GamePlay
		gp = GamePlayHelper.PutGamePlay(gp, p1.getPlayerID(), hp1);		
		gp = GamePlayHelper.PutGamePlay(gp, p2.getPlayerID(), hp2);
		
		//	Set the eDrawCount to FIRST
		gp = GamePlayHelper.setLasteDrawCount(gp, eDrawCount.FIRST);
		
		
		//	All of the above... was to set up a new table, gameplay, add players
		//	Set up hands in the common hand, players hands.		
		//	None of the above should fail.  All of the above was previous tested.		
		//	Now we're going to test to make sure the getDrawResult is working.
		
		
		//	This is going to return the DrawResult for eDrawCount.FIRST
		ArrayList<DrawResult> p1DrawResult = gp.getDrawResult(p1);
		
		System.out.println("***** Draw Result *****");		
		for (DrawResult DR: p1DrawResult)
		{
			System.out.println("* Player : " + DR.getStrPlayerName());
			System.out.print("* Player is me " );
			System.out.println(DR.getiPlayerPosition() == p1.getiPlayerPosition());
			System.out.println("* ");
			System.out.println("* Cards for Player");	
			System.out.println(DR.getiCardNbr());
		}
		
		
		assertEquals(52,p1DrawResult.stream().filter(x -> x.getiPlayerPosition() == p1.getiPlayerPosition())
		.collect(Collectors.toList()).get(0).getiCardNbr());
		
		assertEquals(0,p1DrawResult.stream().filter(x -> x.getiPlayerPosition() == p1.getiPlayerPosition())
		.collect(Collectors.toList()).get(1).getiCardNbr());
		
		assertEquals(40,p1DrawResult.stream().filter(x -> x.getiPlayerPosition() == p2.getiPlayerPosition())
		.collect(Collectors.toList()).get(2).getiCardNbr());
		
		assertEquals(0,p1DrawResult.stream().filter(x -> x.getiPlayerPosition() == p2.getiPlayerPosition())
		.collect(Collectors.toList()).get(3).getiCardNbr());
		
		/*
		assertEquals(40,p1DrawResult.stream().filter(x -> x.getP().equals(p1))
		.collect(Collectors.toList()).get(0).getCards().get(1).getiCardNbr());
		
		assertEquals(0,p1DrawResult.stream().filter(x -> x.getP().equals(p2))
		.collect(Collectors.toList()).get(0).getCards().get(0).getiCardNbr());

		assertEquals(0,p1DrawResult.stream().filter(x -> x.getP().equals(p2))
		.collect(Collectors.toList()).get(0).getCards().get(1).getiCardNbr());
	*/
	
	}
	
	
	private void PrintHandScore(HandScorePoker hsp)
	{
		System.out.println("Hand Strength: " + hsp.geteHandStrength() );
	}
	
 
}
