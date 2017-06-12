package game;

public class Config {

    private static int playerMoveSpeed = 5;
    private static int enemyMoveSpeed = 1;
    private static int targetFps = 80;
    private static int gravity = 5;
    
    private static int screenX = 800;
    private static int screenY = 600;

    public static int getPlayerMoveSpeed() {
	return playerMoveSpeed;
    }

    public int getEnemyMoveSpeed() {
	return enemyMoveSpeed;
    }

    public static int getTargetFps() {
	return targetFps;
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

}
