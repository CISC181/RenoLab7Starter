package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class TableTest {

	@Test
	public void TableTest1() {
		Table t = new Table("Test table");
		Player p = new Player("Bert");		
		
		t.AddPlayerToTable(p);				
		assertEquals(1,t.getTablePlayers().size());
		
		t.AddPlayerToTable(new Player("Joe"));
		assertEquals(2,t.getTablePlayers().size());
		
		t.RemovePlayerFromTable(p);
		assertEquals(1,t.getTablePlayers().size());
				
	}

}
