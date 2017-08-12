package general;

public class Config {

    private static final int playerMoveSpeed = 5;
    private static final int playerRespawnTime = 2000;
    private static final int targetFps = 80;
    private static final int gravity = 5;
    
    private static final int messageBaseTime = 1000;
    private static final int messageTimePerLetter = 50;
    
    private static final int screenX = 800;
    private static final int screenY = 600;

    public static final int timeJumpingUp = 190;
    public static final int maxTimeBetweenJumps = 600;
    public static final int escTime = 2000;
    public static final int enemyMoveSpeed = 1;
    public static final int buttonLockTime = 500;
    public static final int jumpSpeed = 17;

    public static int getPlayerMoveSpeed() {
	return playerMoveSpeed;
    }

    public static int getMsPerFrame() {
	return 1000/targetFps;
    }

    public static int getGravity() {
	return gravity;
    }
    
    public static int getScreenX(){
	return screenX;
    }
    public static  int getScreenY(){
	return screenY;
    }
    
    public static int getPlayerRespawnTime(){
	return playerRespawnTime;
    }
    
    public static int getMessageBaseTime(){
	return messageBaseTime;
    }
    
    public static int getMessageTimePerLetter(){
	return messageTimePerLetter;
    }
}
