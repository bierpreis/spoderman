package game;

import menu.Menu;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu();

        menu.showMenu();

        menu.dispose();
        System.exit(0);

    }

}
