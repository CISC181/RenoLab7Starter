package pkgHelper;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import pkgCore.Card;
import pkgCore.HandPoker;

public class HandPokerHelper {
	
	public static HandPoker SetHand(ArrayList<Card> setCards, HandPoker handpoker) {
		try {
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = handpoker.getClass().getDeclaredMethod("setCards", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			// invoke 'msetCardsInHand'
			msetCardsInHand.invoke(handpoker, setCards);

		
		} catch (SecurityException e) {
			fail ("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail ("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail ("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail ("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail ("Invocation Target Exception Thrown");
		}
		return handpoker;
	}
}