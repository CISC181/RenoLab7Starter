package pkgCore;

import java.io.Serializable;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardVisibility;

/**
 * @author BRG
 * @version Lab #3
 * @since Lab #3
 * 
 *        CardDraw - The purpose of this class it to keep track of the count,
 *        destination and visibility of cards when they are drawn.
 * 
 * @author Dad
 *
 */
public class CardDraw  implements Serializable {

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3 CardCount - How many cards to draw.
	 */
	private eCardCount CardCount;

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3 CardDestination - Where the card belongs- player or
	 *        common/community
	 */
	private eCardDestination CardDestination;

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3 CardVisibility - How visible is the card? Can the other
	 *        player(s) see the card?
	 */
	private eCardVisibility CardVisibility;

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3 CardDraw - Constructor. You need all three parameters.
	 * 
	 * @param cardCount
	 * @param cardDestination
	 * @param cardVisiblity
	 */
	public CardDraw(eCardCount cardCount, eCardDestination cardDestination, eCardVisibility cardVisiblity) {
		super();
		this.CardCount = cardCount;
		this.CardDestination = cardDestination;
		this.CardVisibility = cardVisiblity;
	}

	public eCardCount getCardCount() {
		return CardCount;
	}

	public eCardDestination getCardDestination() {
		return CardDestination;
	}

	public eCardVisibility getCardVisibility() {
		return CardVisibility;
	}
}