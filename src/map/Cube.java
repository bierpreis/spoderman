package map;

import java.awt.Rectangle;

public class Cube implements Readable{


    private final Rectangle bounding;
    public static int size = 32; //todo improve this!
    private boolean active = false;

    public Cube(int x, int y){
        bounding = new Rectangle(x,y,size, size);
    }

    Cube(int x, int y, int width, int height) {
        bounding = new Rectangle(x, y, width, height);


    }


    public Rectangle getBounding() {
        return bounding;
    }

    public boolean isActive(){
        return active;
    }

    public void setActive(boolean isActive){
        this.active = isActive;
    }



}
