package map.enemies;

public class Gooby extends AbstractEnemy {
    public Gooby(int x, int y) {
        super(x, y);


        createLook();
        createBoundings();
    }
}
