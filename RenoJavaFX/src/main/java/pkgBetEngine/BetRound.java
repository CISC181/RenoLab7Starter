package pkgBetEngine;

import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import pkgBetEngine.BetEngine;
import pkgBetEngine.BetRound;
import pkgEnum.eBetRound;
import pkgEnum.eRank;


public class BetRound {
	
	@JsonPropertyOrder({ "BetRoundNumber", "eBetRound", "cardRanks", "iScore" })
	private int BetRoundNumber;
	private eBetRound eBetRound;
	private ArrayList<eRank> cardRanks = new ArrayList<eRank>();
	private int iScore;

	public BetRound() {
		super();
	}

	public BetRound(int betRoundNumber, pkgEnum.eBetRound eBetRound, ArrayList<eRank> cardRanks, int iScore) {
		super();
		BetRoundNumber = betRoundNumber;
		this.eBetRound = eBetRound;
		this.cardRanks = cardRanks;
		this.iScore = iScore;
	}



	public int getBetRoundNumber() {
		return BetRoundNumber;
	}



	public void setBetRoundNumber(int betRoundNumber) {
		BetRoundNumber = betRoundNumber;
	}



	public eBetRound geteBetRound() {
		return eBetRound;
	}



	public void seteBetRound(eBetRound eBetRound) {
		this.eBetRound = eBetRound;
	}



	public ArrayList<eRank> getCardRanks() {
		return cardRanks;
	}



	public void setCardRanks(ArrayList<eRank> cardRanks) {
		this.cardRanks = cardRanks;
	}



	public int getiScore() {
		return iScore;
	}



	public void setiScore(int iScore) {
		this.iScore = iScore;
	}



	public static BetRound getBetRound(eBetRound eBR)  
	{
		BetEngine be = null;
		try {
			be = BetEngine.LoadBettingEngine("BettingEngine.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BetRound br =  be.getBetRound()
				.stream()
				.filter(x -> x.geteBetRound() == eBR)
				.findAny()
				.orElse(null);		
		return br;

	}
 
	
}
