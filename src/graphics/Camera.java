package graphics;

import game.Config;
import player.Player;

public class Camera {
    private float x, y;
    private int screenX = Integer.parseInt(Config.get("screenX"));
    private int screenY = Integer.parseInt(Config.get("screenY"));

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(Player player) {
        x = -player.getX() + screenX / 2;
        y = -player.getY() + screenY / 2;
    }

}
