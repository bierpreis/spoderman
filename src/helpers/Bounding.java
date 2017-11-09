package helpers;




import java.awt.Rectangle;

public class Bounding extends Rectangle implements Scrollable{

    public Bounding(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Bounding(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public void scroll(boolean scrollingLeft, boolean scrollingRight) {

        if (scrollingLeft)
            x = x + Config.playerMoveSpeed;

        if (scrollingRight)
            x = x - Config.playerMoveSpeed;
    }


}
