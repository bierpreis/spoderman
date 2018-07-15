package helpers;


import java.awt.Rectangle;

public class Bounding extends Rectangle {


    public Bounding(int x, int y, int width, int height) {
        super(x, y, width, height);
    }



    public void jump() {
        y = y - Config.jumpSpeed;
    }


}
