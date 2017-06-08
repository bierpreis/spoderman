package game;

import menu.Menu;

public class Main {

	public static void main(String[] args){
		
		int screenX = 800;
		int screenY = 600;

		
		Menu menu = new Menu(screenX, screenY);

		
		menu.showMenu();
		menu.dispose();
		System.exit(0);

		
		

	}

	

}
