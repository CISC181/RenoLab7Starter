package pkgHelper;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import pkgCore.Card;
import pkgCore.GamePlay;
import pkgCore.HandPoker;
import pkgEnum.eDrawCount;

public class GamePlayHelper {

	public static GamePlay setLasteDrawCount(GamePlay gp, eDrawCount eDC)
	{
		try {

			//	Find method setLasteDrawCount with eDrawCount parameter
			Method mLasteDrawCount = gp.getClass().getDeclaredMethod("setLasteDrawCount",
					new Class[] { eDrawCount.class });
			
			//	Set up the arguments to pass a method
			Object[] ArgsLasteDrawCount = new Object[] { eDC };
			
			// Change the visibility of 'setLasteDrawCount' to true *Good Grief!*
			mLasteDrawCount.setAccessible(true);

			//	Invoke the method for a given instance of a class, set arguments
			mLasteDrawCount.invoke(gp, ArgsLasteDrawCount);

		} catch (SecurityException e) {
			fail("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail("Invocation Target Exception Thrown");
		}
		return (GamePlay) gp;
		
	}
	
	public static GamePlay PutGamePlay(GamePlay gp, UUID PlayerID, HandPoker hp) {

		try {

			//	Find a method with arguments UUID, HandPoker
			Method mPutGameHand = gp.getClass().getDeclaredMethod("PutGameHand",
					new Class[] { UUID.class, HandPoker.class });
			//	Set up the arguments to pass a method
			Object[] ArgsPutHand = new Object[] { PlayerID, hp };
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			mPutGameHand.setAccessible(true);

			//	Invoke the method for a given instance of a class, set arguments
			mPutGameHand.invoke(gp, ArgsPutHand);

		} catch (SecurityException e) {
			fail("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail("Invocation Target Exception Thrown");
		}
		return (GamePlay) gp;

	}
	
	public static GamePlay setCommonCards(GamePlay gp, ArrayList<Card> cards) {

		try {

			//	Find a method with arguments UUID, HandPoker
			Method mSetCommonCards = gp.getClass().getDeclaredMethod("setCommonCards",
					new Class[]{ArrayList.class} );
			
			
			//	Set up the arguments to pass a method
			Object[] ArgsCommonHand = new Object[] { cards };
			
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			mSetCommonCards.setAccessible(true);

			//	Invoke the method for a given instance of a class, set arguments
			mSetCommonCards.invoke(gp, ArgsCommonHand);

		} catch (SecurityException e) {
			fail("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail("Invocation Target Exception Thrown");
		}
		return (GamePlay) gp;

	}
}