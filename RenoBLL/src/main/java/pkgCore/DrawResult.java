package pkgCore;

import java.io.Serializable;
import java.util.ArrayList;
import pkgEnum.eDrawCount;

import pkgCoreInterface.iCardDraw;

public class DrawResult implements Serializable {

	private CardDraw CD;
	private String strPlayerName;
	private int iPlayerPosition;
	private int iCardNbr;
	private eDrawCount eDrawCount;
	private int iCardPosition;
	
	
	public DrawResult(CardDraw cD, String strPlayerName, int iPlayerPosition, int iCardNbr, eDrawCount eDrawCount, int iCardPosition) {
		super();
		CD = cD;
		this.strPlayerName = strPlayerName;
		this.iPlayerPosition = iPlayerPosition;
		this.iCardNbr = iCardNbr;
		this.eDrawCount = eDrawCount;
		this.iCardPosition = iCardPosition;
	}
	public int getiCardPosition() {
		return iCardPosition;
	}
	public CardDraw getCD() {
		return CD;
	}
	public String getStrPlayerName() {
		return strPlayerName;
	}
	public int getiPlayerPosition() {
		return iPlayerPosition;
	}
	public int getiCardNbr() {
		return iCardNbr;
	}
	public eDrawCount geteDrawCount() {
		return eDrawCount;
	}
	
	
}
