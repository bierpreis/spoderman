package player;

import game.Config;

import java.awt.*;

public class Jump {
    private boolean jumping = false;
    private int timeAlreadyJumping = 0;
    private int timeSinceLastJump = 200;
    private int msPerFrame = Integer.parseInt(Config.get("msPerFrame"));
    private int minTimeBetweenJumps = Integer.parseInt(Config.get("minTimeBetweenJumps"));
    private int timeJumpingUp = Integer.parseInt(Config.get("timeJumpingUp"));
    private int jumpSpeed = Integer.parseInt(Config.get("jumpSpeed"));

    boolean checkIfJump(boolean isSpacePressed, boolean isPlayerOnGround, boolean isPlayerOnTop) {
        timeSinceLastJump = +timeSinceLastJump + msPerFrame;
        if (isPlayerOnGround && isSpacePressed && timeSinceLastJump > minTimeBetweenJumps)
            jumping = true;
        if (isPlayerOnTop)
            jumping = false;
        return jumping;
    }

    Rectangle performJump(Rectangle playerBounding, boolean onTop) {
        timeAlreadyJumping += msPerFrame;
        if (timeAlreadyJumping > timeJumpingUp || onTop) {
            jumping = false;
            timeAlreadyJumping = 0;
        }
        playerBounding.y = playerBounding.y - jumpSpeed;
        return playerBounding;

    }
}
