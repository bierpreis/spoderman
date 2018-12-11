package map;

public class Bigmek extends UnitGameObject {

    private boolean collected = false;


    public Bigmek(int x, int y) {
        super(x, y);
        createLook();
        createBoundings();


    }


    public boolean getCollected() {
        return collected;
    }

    public void setCollected() {

        collected = true;
        look = null;
    }
}
