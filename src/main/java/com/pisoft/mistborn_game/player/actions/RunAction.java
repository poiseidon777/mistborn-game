package com.pisoft.mistborn_game.player.actions;

import com.pisoft.mistborn_game.player.Side;
import com.pisoft.mistborn_game.player.constants.PlatformingConstants;

public class RunAction extends PlayerAction {

	private Side direction;
	
	public RunAction(Side direction) {
		super();
		
		this.direction = direction;
	}
	
	@Override
	public void resolve() {
		targetPlayer.setFacingSide(direction);
		targetPlayer.setWantsToAccelerate(true);
		targetPlayer.setAccelerating(true);
		targetPlayer.setWalking(false);
		targetPlayer.setRunning(true);
		targetPlayer.setSliding(false);

		// player must be facing either LEFT or RIGHT
		double accMultiplier = (targetPlayer.getFacingSide() == Side.RIGHT) ? 1 : -1;

		double xAcc = PlatformingConstants.getRunAcc();

		targetPlayer.setxAcc(xAcc * accMultiplier);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		RunAction clone = (RunAction) super.clone();
		
		clone.direction = this.direction;
		
		return clone;
	}
}
