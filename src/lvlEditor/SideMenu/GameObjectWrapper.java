package lvlEditor.SideMenu;

import map.BasicGameObject;
import map.Cube;

public class GameObjectWrapper {
    private BasicGameObject activeGameObject;

    public GameObjectWrapper() {
        activeGameObject = new Cube(0, 0);
    }

    public BasicGameObject get() {
        return activeGameObject;
    }

    public void set(BasicGameObject newGameObject) {
        this.activeGameObject = newGameObject;
    }
}
