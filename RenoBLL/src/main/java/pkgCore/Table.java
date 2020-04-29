package pkgCore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import pkgUtil.CircularLinkedList;

public class Table implements Serializable {

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        TableID - UUID for the table. Makes it unique
	 */
	private UUID TableID;
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        TableName - Name of the table
	 */
	private String TableName;

	/**
	 * TablePlayers - Changed to CircularLinkedList in Lab #5
	 * 
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #5
	 */
	private ArrayList<Player> TablePlayers = new ArrayList<Player>();

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        Table - Constructor
	 * 
	 *        Create an instance of Table. Set the TableID as a random UUID, set the
	 *        TableName attribute
	 * @param tableName
	 */
	public Table(String tableName) {
		TableID = UUID.randomUUID();
		TableName = tableName;
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        SetTablePlayers - set the TablePlayers ArrayList with given Players
	 * @param Players - set of Players to add to the table
	 */
	public void SetTablePlayers(ArrayList<Player> Players) {
		TablePlayers.clear();
		TablePlayers.addAll(Players);
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        AddPlayerToTable - Add a single Player to the table.
	 * @param player - Player to be added
	 */
	public void AddPlayerToTable(Player player) {
		TablePlayers.add(player);
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        RemovePlayerFromTable - Remove a single player from the Table.
	 * @param p
	 */
	public void RemovePlayerFromTable(Player p) {
		TablePlayers.remove(p);
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 *        getTablePlayers - return an ArrayList of players at the table
	 * @return
	 */
	public ArrayList<Player> getTablePlayers() {
		
		return this.TablePlayers;
		/*
		ArrayList<Player> tablePlayers = new ArrayList<Player>();
		if (TablePlayers.getItemsInOrder() == null)
			return null;
		
		for (Player p: TablePlayers.getItemsInOrder())
		{
			tablePlayers.add(p);
		}
		return tablePlayers;
		
		*/
	}

	/**
	 * AdvancePlayer - Advance the CircularLinkedList
	 * 
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #5
	 */
	/*
	public void AdvancePlayer() {
		TablePlayers.advanceCurrent();
	}
	*/
	
	/**
	 * getButton - Return the 'current' LinkedList member
	 * 
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #5
	 */
	/*
	public Player getButton() {
		return TablePlayers.getCurrent();
	}
	*/

	/**
	 * ClearTable - Clear the CircularLinkedList
	 * 
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #5
	 */	
	public void ClearTable() {
		TablePlayers.clear();
	}
}
