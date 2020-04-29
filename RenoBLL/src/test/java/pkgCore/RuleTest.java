package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardDestination;
import pkgEnum.eDrawCount;
import pkgEnum.eGame;
import pkgEnum.eStartEnd;

public class RuleTest {

	@Test
	public void RuleTest() {

		Rule rle = new Rule(eGame.TexasHoldEm);
		int iStartIdx = rle.getIdx(eDrawCount.FOURTH,  eStartEnd.START);
		int iEndIdx = rle.getIdx(eDrawCount.FOURTH,  eStartEnd.END);
	}
	
	@Test
	public void RuleTest_NextDraw() {
		
		
		assertEquals(eDrawCount.FIRST, Rule.getNextDraw(eGame.TexasHoldEm, null));
		assertEquals(eDrawCount.SECOND, Rule.getNextDraw(eGame.TexasHoldEm,  eDrawCount.FIRST));
		assertEquals(eDrawCount.THIRD, Rule.getNextDraw(eGame.TexasHoldEm,  eDrawCount.SECOND));
		assertEquals(eDrawCount.FOURTH, Rule.getNextDraw(eGame.TexasHoldEm,  eDrawCount.THIRD));
		assertNull(Rule.getNextDraw(eGame.TexasHoldEm,  eDrawCount.FOURTH));
		
		
		
		
	}

}
