package com.pisoft.mistborn_game.player.metalpushing;

import java.awt.Point;
import java.awt.event.InputEvent;

import com.pisoft.mistborn_game.Game;
import com.pisoft.mistborn_game.levels.*;
import com.pisoft.mistborn_game.player.Player;
import com.pisoft.mistborn_game.player.controllers.*;

public class PushPullManager {
    private static Metal targetMetal;
    private Player targetPlayer;

    private double xPush;
    private double yPush;

    //TODO: should this actually find the closest metal, or is finding a metal within 80px good enough?
    public static void findTargetMetal() {
        Point mousePos = MouseTracker.getMousePoint();

        for (Metal metal : Game.getActiveLevel().getMetals()) {
            if (Math.hypot(mousePos.x - metal.getxPos(), mousePos.y - metal.getyPos()) < 80) {
                targetMetal = metal;
                return;
            } else {
                targetMetal = null;
            }
        }
    }

    //TODO: make this return a Point instead of directly set variables 
    public void setSteelPush() {
        if (targetMetal != null && MouseTracker.getButtonsPressed().contains(InputEvent.BUTTON1_MASK)) {
            //set xPush and yPush based off of player data
            double direction = 0;
            double magnitude = 0;

            double x1 = targetPlayer.getxPos() + (targetPlayer.getWidth() / 2);
            double x2 = targetMetal.getxPos() + 2.5;
            double y1 = targetPlayer.getyPos() + (targetPlayer.getHeight() / 2);
            double y2  = targetMetal.getyPos() + 2.5;

            magnitude = MetalPushingConstants.getPushStrength() * Math.pow(Math.hypot(x1 - x2, y1 - y2), -2);
            direction = (x1 - x2 > 0) ? Math.atan((y1-y2) / (x1-x2)) : Math.atan((y1-y2) / (x1-x2)) + Math.PI;

            xPush = Math.abs(magnitude * Math.cos(direction)) < 0.3 ? 0 : magnitude * Math.cos(direction);
            yPush = Math.abs(magnitude * Math.sin(direction)) < 0.3 ? 0 : -magnitude * Math.sin(direction);
        } else {
            //no metal is targeted, or player is not pressing button for push -> return zero push
            xPush = 0;
            yPush = 0;
        }
    }

    //getters and setters
    // -----------------------------------------------------------------------------------------------
    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public double getxPush() {
        return xPush;
    }

    public void setxPush(double xPush) {
        this.xPush = xPush;
    }

    public double getyPush() {
        return yPush;
    }

    public void setyPush(double yPush) {
        this.yPush = yPush;
    }

    public static Metal getTargetMetal() {
        return targetMetal;
    }

    public static void setTargetMetal(Metal targetMetal) {
        PushPullManager.targetMetal = targetMetal;
    }
}