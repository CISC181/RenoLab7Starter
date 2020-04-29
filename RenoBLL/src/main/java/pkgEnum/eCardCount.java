package pkgEnum;

public enum eCardCount {
	One(1), Two(2), Three(3), Four(4), Five(5);
	
	private eCardCount(final int CardCount){
		this.CardCount = CardCount;
	}

	private int CardCount;
	
	public int getCardCount(){
		return CardCount;
	}
}