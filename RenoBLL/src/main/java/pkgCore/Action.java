package pkgCore;

import java.io.Serializable;
import pkgEnum.eAction;

public class Action implements Serializable {

	private static final long serialVersionUID = 1L;
	private eAction EA;
	private Player ActPlayer;
	private Object payload;
	
	public Action(eAction eAction, Player player  ) {
		super();
		this.EA = eAction;
		this.ActPlayer = player;	
	}
	
	public Action(eAction eAction, Player player, Object Payload ) {
		super();
		this.EA = eAction;
		this.ActPlayer = player;	
		this.payload = Payload;
	}
	
	public eAction geteAction() {
		return EA;
	}
	public void seteAction(eAction eAction) {
		this.EA = eAction;
	}
	public Player getActPlayer() {
		return ActPlayer;
	}
	public void setActPlayer(Player actPlayer) {
		ActPlayer = actPlayer;
	}

	public Object getPayload() {
		return payload;
	}
	
}
