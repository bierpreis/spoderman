package player;

import helpers.Config;

import java.awt.*;

public class Jump {
    private boolean jumping = false;
    private int timeAlreadyJumping = 0;
    private int timeSinceLastJump = 200;

    boolean checkIfJump(boolean isSpacePressed, boolean isPlayerOnGround, boolean isPlayerOnTop) {
        timeSinceLastJump = +timeSinceLastJump + Config.msPerFrame;
        if (isPlayerOnGround && isSpacePressed && timeSinceLastJump > Config.minTimeBetweenJumps)
            jumping = true;
        if (isPlayerOnTop)
            jumping = false;

        return jumping;
    }

    Rectangle performJump(Rectangle playerBounding) {
        timeAlreadyJumping += Config.msPerFrame;
        if (timeAlreadyJumping > Config.timeJumpingUp) {
            jumping = false;
            timeAlreadyJumping = 0;
        }
        playerBounding.y = playerBounding.y - Config.jumpSpeed;
        return playerBounding;

    }
}
