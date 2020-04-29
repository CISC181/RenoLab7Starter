package pkgCore;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

public class Player implements Serializable, Comparable<Player> {

	private UUID PlayerID;
	private String PlayerName;
	private int ClientID;
	private int iPlayerPosition;
	
	
	 
	@Override
	public int hashCode() {
		return Objects.hash(this.PlayerID);
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	public int getiPlayerPosition() {
		return iPlayerPosition;
	}

	public void setiPlayerPosition(int iPlayerPosition) {
		this.iPlayerPosition = iPlayerPosition;
	}

	public Player(String playerName, int iClientID) {		
		super();
		PlayerID = UUID.randomUUID();
		PlayerName = playerName;
		this.ClientID = iClientID;
	}
		
	public Player(String playerName) {
		this.PlayerID = UUID.randomUUID();
		PlayerName = playerName;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	public UUID getPlayerID() {
		return PlayerID;
	}

	public int getClientID() {
		return ClientID;
	}


    // External Comparator
    public static Comparator<Player> hpPlayerPosition = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {        	
        	return p1.compareTo(p2);
        }
    };

	@Override
	public int compareTo(Player o) {
		Player PassedPlayer =  o;
		Player ThisPlayer = this;
		
		if (PassedPlayer.getiPlayerPosition() - ThisPlayer.getiPlayerPosition() != 0)
			return PassedPlayer.getiPlayerPosition() - ThisPlayer.getiPlayerPosition();
		return 0;
	}
	
	
}
