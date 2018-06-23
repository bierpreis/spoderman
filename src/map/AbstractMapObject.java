package map;

import helpers.Bounding;

import java.awt.image.BufferedImage;

public class AbstractMapObject {
    protected Bounding bounding;
    private BufferedImage look;


    public int getX() {
        return bounding.x;
    }

    public AbstractMapObject(){

    }

    public AbstractMapObject(int x, int y){
        bounding = new Bounding(x,y,0,0);

    }






    public int getY() {
        return bounding.y;
    }

    public BufferedImage getLook(){
        return look;
    }

    public Bounding getBounding(){
        return bounding;
    }

    public void scroll(boolean scrollingLeft, boolean scrollingRight){
        bounding.scroll(scrollingLeft, scrollingRight);
    }
}
