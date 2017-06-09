package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {

    private boolean onTop = false;
    private boolean onBot = false;
    private boolean onRightSide;
    private boolean onLeftSide;
    private boolean alreadyJumped = false;
    private boolean jumpReleased = true;
    private boolean jumping = false;
    private boolean isLookingRight = true;
    private boolean scrollingRight = false;
    private boolean scrollingLeft = false;
    private boolean alive = true;
    private boolean sayMessage = false;
    private boolean respawnLock = false;
    private boolean lockMessage = false;
    private boolean lvlUp = false;


    private int timeSinceJump;
    private int upJumpTime = 190;
    private int jumpTime = 700;
    private int x;
    private int y;
    private int swegCollected = 0;
    private int messageTimer = 0;
    private int kills = 0;
    private int lifes = 3;
    private int timeDead = 0;
    private int screenX;
    private int moveSpeed = 5;

    private float f_posx; // f_ als kennzeichen für float
    private float f_posy;

    private Rectangle bounding;
    private Rectangle botBounding;
    private BufferedImage lookingLeft, lookingRight, lookDead;

    private BufferedImage[] messagePicArray;

    private Map map;


    // konstruktor
    public Player(Map map, int screenX) {
	f_posx = 350;
	f_posy = 300;
	this.screenX = screenX;

	createLook();

	bounding = new Rectangle(x, y, lookingLeft.getWidth(), lookingLeft.getHeight());
	botBounding = new Rectangle(x, (y + (lookingLeft.getHeight())), lookingLeft.getWidth(), (20));



	this.map = map;

	sayMessage("nao i need to find teh bikmek");

    }

    public void update(boolean left, boolean right, boolean jump) {

	//map.scrollCube(scrollingRight, scrollingLeft);
	updateCubes(); // macht die hilfsboundings

	updateBigmek();
	updateEnemies();
	updateSweg();
	updateTimeDead();
	updateBounding();
	updateMessage();

	scroll();

	move(left, right);
	jump(left, right, jump);

	respawn();

	checkSweg();
	checkBigmek();
	checkCubes();
	checkEnemies();
	
    }

    private void createLook() {

	try {
	    lookingRight = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_right.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	try {
	    lookingLeft = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_left.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	try {
	    lookDead = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    void updateMessage() {
	// zeitbegrenzung message
	if (!lockMessage)
	    if (!respawnLock && sayMessage)
		messageTimer -= 15;
	if (messageTimer < 0)
	    sayMessage = false;
    }

    void updateBounding() {
	bounding.x = (int) f_posx;
	bounding.y = (int) f_posy;
	botBounding = new Rectangle((int) f_posx, ((int) f_posy + (30)), lookingLeft.getWidth(),
		(lookingLeft.getHeight() - 30)); /// !!!!!
    }

    public void updateCubes() {
	for (int i = 0; i < map.getCube().length; i++) {
	    map.getCube()[i].updateBounding(scrollingLeft, scrollingRight, moveSpeed);
	}
    }

    void updateBigmek() {
	if (map.getBigmek() != null) {
	    map.getBigmek().update(scrollingLeft, scrollingRight);
	}
    }

    public void updateEnemies() {
	if (map.getEnemies()!= null)
	    for (int i = 0; i < map.getEnemies().length; i++) {
		map.getEnemies()[i].update(scrollingLeft, scrollingRight);
	    }
    }

    void updateSweg() {
	if (map.getSweg() != null)
	    for (int i = 0; i < map.getSweg().length; i++) {
		map.getSweg()[i].updateBounding(scrollingLeft, scrollingRight);
	    }
    }

    void updateTimeDead() {
	if (!alive && lifes > 0)
	    timeDead += 15;
    }

    public Rectangle getBounding() {
	return bounding;
    }

    void respawn() {
	if (!respawnLock && !alive && lifes > 0 && timeDead > 2000) {

	    alive = true;
	    f_posx = 400;
	    f_posy = 300;
	    sayMessage("respawn  now am angri as fuk");
	    timeDead = 0;
	}
    }

    void checkCubes() {
	onRightSide = false;
	onLeftSide = false;
	onBot = false;
	onTop = false;
	for (int i = 0; i < map.getCube().length; i++) {
	    // überprüft onleftside
	    if (bounding.intersects(map.getCube()[i].getLeftBounding())) {
		onLeftSide = true;

	    }
	    // überprüft onrightside
	    if (bounding.intersects(map.getCube()[i].getRightBounding())) {
		onRightSide = true;
	    }

	    // überprüft onTop
	    if (botBounding.intersects(map.getCube()[i].getTopBounding())) {
		onTop = true;
	    }

	    // überprüft ob onBot
	    if (bounding.intersects(map.getCube()[i].getBotBounding())) {
		onBot = true;
	    }

	}

    }

    void move(boolean left, boolean right) {

	if (alive) {
	    if (left && !onRightSide && !scrollingLeft) {
		f_posx -= moveSpeed;
		isLookingRight = false;
	    }

	    if (right && !onLeftSide && !scrollingRight) {
		f_posx += moveSpeed;
		isLookingRight = true;
	    }
	}
	// schwerkraft
	if (!onTop)
	    f_posy += map.getSchwerkraft();

    }

    void jump(boolean left, boolean right, boolean jump) {
	if (alive)
	    if (onTop && (!onBot && jump && !alreadyJumped && jumpReleased)) {
		jumping = true;
		alreadyJumped = true;
		jumpReleased = false;
	    }
	
	
	if (onBot)
	    jumping = false;
	if (timeSinceJump > upJumpTime)
	    jumping = false;
	if (alreadyJumped)
	    timeSinceJump += 15;
	if (timeSinceJump > jumpTime) {
	    alreadyJumped = false;
	    timeSinceJump = 0;
	}
	if (jumping && timeSinceJump < upJumpTime && !onBot) {
	    f_posy -= 19;
	}
	if (timeSinceJump <= jumpTime)
	    jumpReleased = true;

    }

    void checkSweg() {
	if (map.getSweg() != null)
	    for (int i = 0; i < map.getSweg().length; i++) {
		if (!map.getSweg()[i].isCollected())
		    if (bounding.intersects(map.getSweg()[i].getBounding())) {
			map.getSweg()[i].setCollected();
			sayMessage("monies");
			swegCollected += 1;
		    }
	    }
    }

    void checkBigmek() {
	if (map.getBigmek() != null && !map.getBigmek().getCollected())
	    if (bounding.intersects(map.getBigmek().getBounding())) {
		map.getBigmek().setCollected();
		sayMessage("press enter to enter lvl two");
		lvlUp = true;
		lockMessage();
	    }
    }

    void checkEnemies() {
	if (map.getEnemies() != null)
	    for (int i = 0; i < map.getEnemies() .length; i++) {
		// gegner töten
		if (map.getEnemies() [i].getAlive())

		    if (botBounding.intersects(map.getEnemies() [i].getTopBounding())) {
			map.getEnemies() [i].kill();
			sayMessage("lel rekt");
			kills += 1;
		    }

		// feststellen ob tot
		if (alive)
		    if (bounding.intersects(map.getEnemies() [i].getBounding()) && map.getEnemies() [i].getAlive()) {
			alive = false;
			lifes -= 1;
			if (lifes > 0)
			    sayMessage("u got rekt 111  press spaec to respawn");
			if (lifes < 1)
			    sayMessage("g8 nao dis is mai end");
			respawnLock = true;
		    }

	    }
    }

    public BufferedImage getLook() {

	if (!alive)
	    return lookDead;

	if (isLookingRight)
	    return lookingRight;
	if (!isLookingRight)
	    return lookingLeft;

	return null; // default
    }

    void scroll() {
	scrollingLeft = false;
	scrollingRight = false;
	if (bounding.x > 0.6 * screenX && !onLeftSide && alive) {
	    scrollingRight = true;
	    f_posx -= 0.2;
	}
	if (bounding.x < 0.4 * screenX && !onRightSide && alive) {
	    scrollingLeft = true;
	    f_posx += (0.2);
	}
    }

    public int getSwegCount() {
	return swegCollected;
    }

    public int getKills() {
	return kills;
    }

    public int getLifes() {
	return lifes;
    }

    public void sayMessage(String messageString) {

	messagePicArray = Letters.createMessage(messageString);
	sayMessage = true;
	this.messageTimer = Letters.getMessageTimer();

    }

    public boolean isSayMessage() {
	return sayMessage;
    }

    public boolean getRespawnLock() {
	return respawnLock;
    }

    public void setRespawnLock(boolean respawnLock) {
	this.respawnLock = respawnLock;
    }

    public void setScrollingRight(boolean scrollingRight) {
	this.scrollingRight = scrollingRight;
    }

    public void lockMessage() { // wird von escape sequenz genutzt
	lockMessage = true;
    }

    public void unlockMessage() { // ebenfalls esc
	lockMessage = false;
	sayMessage = false;
    }

    public int getTimeDead() {
	return timeDead;
    }

    public BufferedImage[] getMessagePics() {
	return messagePicArray;
    }

    public boolean getLvlUp() {
	return lvlUp;
    }

    public void setLvlUp(boolean lvlUp) {
	this.lvlUp = lvlUp;
    }
}
