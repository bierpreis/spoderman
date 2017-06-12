package game;

import menu.Menu;

public class Main {

	public static void main(String[] args){
		
		final int screenX = Config.getScreenX();
		final int screenY = Config.getScreenY();

		
		Menu menu = new Menu(screenX, screenY);

		
		menu.showMenu();
		menu.dispose();
		System.exit(0);

		
		

	}

	

}
