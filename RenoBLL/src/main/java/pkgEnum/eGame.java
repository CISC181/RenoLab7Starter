package pkgEnum;

import java.util.HashMap;
import java.util.Map;

public enum eGame {

	FiveStud(1, false) {
		@Override
		public String toString() {
			return "Five Card Stud";
		}
	},
	FiveStudOneJoker(2, true) {
		@Override
		public String toString() {
			return "Five Card Stud, One Joker";
		}
	},
	FiveStudTwoJoker(3, false) {
		@Override
		public String toString() {
			return "Five Card Stud, Two Jokers";
		}
	},
	TexasHoldEm(4, false) {
		@Override
		public String toString() {
			return "Texas Hold'em";
		}
	},
	Omaha(5, false) {
		@Override
		public String toString() {
			return "Omaha";
		}
	},
	DeucesWild(6, false) {
		@Override
		public String toString() {
			return "Deuces Wild";
		}
	},
	AcesAndEights(7, false) {
		@Override
		public String toString() {
			return "Aces and Eights";
		}
	},
	SevenDraw(8, false) {
		@Override
		public String toString() {
			return "Seven Card Draw";
		}
	},
	SuperOmaha(9, false) {
		@Override
		public String toString() {
			return "Super Omaha";
		}
	};

	private int gameNbr;
	private boolean bDefault = false;
	private static Map<Integer, eGame> map = new HashMap<Integer, eGame>();

	static {
		for (eGame game : eGame.values()) {
			map.put(game.gameNbr, game);
		}
	}

	public static eGame getGame(int gameNbr) {
		return map.get(gameNbr);
	}

	private eGame(final int gameNbr, boolean bDefault) {
		this.gameNbr = gameNbr;
		this.bDefault = bDefault;
	}

	public int getGame() {
		return gameNbr;
	}

	public boolean getDefault() {
		return this.bDefault;
	}
}