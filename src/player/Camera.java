package player;

import helpers.Config;

public class Camera {
    float x, y;

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
        x = -player.getX() + Config.screenX / 2;
        y = -player.getY() + Config.screenY / 2;
    }

}
