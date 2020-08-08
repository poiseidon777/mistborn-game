package com.pisoft.mistborn_game.player.actions;

public class CrouchAction extends PlayerAction {
	
	@Override
	public void resolve() {
		targetPlayer.setAccelerating(false);
		if (targetPlayer.getxSpeed() != 0) {
			targetPlayer.setSliding(true);
		}
		targetPlayer.setGrounded(true);
		targetPlayer.setLanding(false);
		targetPlayer.setJumping(false);
		targetPlayer.setDoubleJumping(false);
		targetPlayer.setWallJumping(false);
		targetPlayer.setFalling(false);
		targetPlayer.setCrouching(true);
		targetPlayer.setCanJump(false);
		
		targetPlayer.setxAcc(0);
	}
}
