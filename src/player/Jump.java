package player;

import helpers.Bounding;
import helpers.Config;

public class Jump {
    boolean jumping = false;
    int timeAlreadyJumping = 0;
    int timeSinceLastJump = 200;

    boolean checkIfJump(boolean isSpacePressed, boolean isPlayerOnTop, boolean isPlayerOnBot) {
        timeSinceLastJump = +timeSinceLastJump + Config.msPerFrame;
        if (isPlayerOnBot && isSpacePressed && timeSinceLastJump > Config.minTimeBetweenJumps)
            jumping = true;
        if(isPlayerOnTop)
            jumping = false;

        return jumping;
    }

    Bounding performJump(Bounding playerBounding) {
        timeAlreadyJumping += Config.msPerFrame;
        if (timeAlreadyJumping > Config.timeJumpingUp) {
            jumping = false;
            timeAlreadyJumping = 0;
        }
        playerBounding.jump();
        return playerBounding;

    }
}
