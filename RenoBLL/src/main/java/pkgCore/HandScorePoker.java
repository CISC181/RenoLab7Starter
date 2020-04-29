package pkgCore;

import java.util.ArrayList;
import java.util.Objects;

import pkgEnum.eHandStrength;

public class HandScorePoker extends HandScore {
	private eHandStrength eHandStrength;
	private Card HiCard;
	private Card LoCard;
	private ArrayList<Card> kickers = new ArrayList<Card>();
	private boolean Natural = true;
	private ArrayList<Card> SubstitutedCards = new ArrayList<Card>();
	private int RemainingCards;

	public eHandStrength geteHandStrength() {
		return eHandStrength;
	}

	protected void seteHandStrength(eHandStrength eHandStrength) {
		this.eHandStrength = eHandStrength;
	}

	protected Card getHiCard() {
		return HiCard;
	}

	protected void setHiCard(Card hiCard) {
		HiCard = hiCard;
	}

	protected Card getLoCard() {
		return LoCard;
	}

	protected void setLoCard(Card loCard) {
		LoCard = loCard;
	}

	protected ArrayList<Card> getKickers() {
		return kickers;
	}

	protected void setKickers(ArrayList<Card> kickers) {
		this.kickers = kickers;
	}

	public boolean isNatural() {
		return Natural;
	}

	public void setNatural(boolean natural) {
		Natural = natural;
	}

	ArrayList<Card> getSubtitutedCards() {
		return SubstitutedCards;
	}

	void addSubstitutedCard(Card c) {
		SubstitutedCards.add(c);
	}

	void setSubtitutedCards(ArrayList<Card> subtitutedCards) {
		SubstitutedCards.clear();
		SubstitutedCards.addAll(subtitutedCards);
	}

	int getRemainingCards() {
		return RemainingCards;
	}

	void setRemainingCards(int remainingCards) {
		RemainingCards = remainingCards;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.geteHandStrength(), this.getHiCard(), this.getLoCard(), this.getKickers());
	}

	@Override
	public boolean equals(Object obj) {
		HandScorePoker PassedHSP = (HandScorePoker) obj;
		HandScorePoker ThisHSP = this;

		if ((PassedHSP.geteHandStrength() != null) && (ThisHSP.geteHandStrength() != null)
				&& (PassedHSP.geteHandStrength() != ThisHSP.geteHandStrength())) {
			return false;
		}
		if ((PassedHSP.geteHandStrength() == null) && (ThisHSP.geteHandStrength() != null)) {
			return false;
		}
		if ((PassedHSP.geteHandStrength() != null) && (ThisHSP.geteHandStrength() == null)) {
			return false;
		}

		if ((PassedHSP.getHiCard() != null) && (ThisHSP.getHiCard() != null)
				&& (PassedHSP.getHiCard().geteRank().getiRankNbr() != ThisHSP.getHiCard().geteRank().getiRankNbr())) {
			return false;
		}
		if ((PassedHSP.getHiCard() == null) && (ThisHSP.getHiCard() != null)) {
			return false;
		}
		if ((PassedHSP.getHiCard() != null) && (ThisHSP.getHiCard() == null)) {
			return false;
		}

		if ((PassedHSP.getLoCard() != null) && (ThisHSP.getLoCard() != null)
				&& (PassedHSP.getLoCard().geteRank().getiRankNbr() != ThisHSP.getLoCard().geteRank().getiRankNbr())) {
			return false;
		}
		if ((PassedHSP.getLoCard() == null) && (ThisHSP.getLoCard() != null)) {
			return false;
		}
		if ((PassedHSP.getLoCard() != null) && (ThisHSP.getLoCard() == null)) {
			return false;
		}

		if ((PassedHSP.getKickers() == null) || (ThisHSP.getKickers() == null))
		{
			return true;
		}
		for (int i = 0; i < 4; i++) {

			if ((PassedHSP.getKickers().get(i) != null) && (ThisHSP.getKickers().get(i) != null)
					&& (PassedHSP.getKickers().get(i).geteRank().getiRankNbr() != 
					ThisHSP.getKickers().get(i).geteRank().getiRankNbr())) {
				return false;
			}
			if ((PassedHSP.getKickers().get(i) == null) && (ThisHSP.getKickers().get(i) != null)) {
				return false;
			}
			if ((PassedHSP.getKickers().get(i) != null) && (ThisHSP.getKickers().get(i) == null)) {
				return false;
			}
		}

		return true;
	}

}