package game;

import menu.Menu;

public class Main {

    public static void main(String[] args) {

	Menu menu = new Menu();

	boolean escape = false;

	while (!escape) {
	    escape = menu.showMenu();

	}

	menu.dispose();
	System.exit(0);

    }

}
