package map.enemies;


public class Dolan extends AbstractEnemy {
    public Dolan(int x, int y) {
        super(x, y);

        createLook();

        createBoundings();

    }
}
