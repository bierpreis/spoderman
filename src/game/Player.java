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
    private boolean lookingRight = true;
    private boolean scrollingRight = false;
    private boolean scrollingLeft = false;
    private boolean alive = true;
    private boolean sayMessage = false;
    private boolean respawnLock = false;
    private boolean lockMessage = false;
    private boolean lvlUp = false;

    private int moveSpeed;
    private int schwerkraft;
    private int timeSinceJump;
    private int screenX;
    private int upJumpTime = 190;
    private int jumpTime = 700;
    private int startXPos = 350;
    private int startYPos = 300;
    private int x;
    private int y;
    private int swegCollected = 0;
    private int messageTimer = 0;
    private int kills = 0;
    private int lifes = 3;
    private int timeDead = 0;

    private float f_posx; // f_ als kennzeichen für float
    private float f_posy;

    private Rectangle bounding;
    private Rectangle botBounding;
    private BufferedImage look;

    private BufferedImage[] messagePicArray;

    private Map map;
    private Cube[] cube;
    private Enemy[] enemies;
    private Sweg[] sweg;
    private Bigmek bigmek;



    // konstruktor
    public Player(int screenX, Map map) {
	f_posx = startXPos;
	f_posy = startYPos;

	look = getLook();

	bounding = new Rectangle(x, y, look.getWidth(), look.getHeight());
	botBounding = new Rectangle(x, (y + (look.getHeight())), look.getWidth(), (20));

	sweg = map.getSweg();
	bigmek = map.getBigmek();
	cube = map.getCube();
	enemies = map.getEnemies();

	moveSpeed = map.getMoveSpeed();
	schwerkraft = map.getSchwerkraft();

	this.screenX = screenX;
	this.map = map;

	sayMessage("nao i need to find teh bikmek");
    }

    public void update(boolean left, boolean right, boolean jump) {

	cube = map.scrollCube(isScrollingRight(), isScrollingLeft());
	updateCubes(); // macht die hilfsboundings

	updateBigmek();
	updateEnemies();
	updateSweg();
	updateTimeDead();
	updateBounding();
	updateMessage();

	scroll();

	move(left, right, jump);

	respawn();

	checkSweg();
	checkBigmek();
	checkCubes();
	checkEnemies();
    }

    void updateMessage() {
	// zeitbegrenzung message
	if (!lockMessage)
	    if (!isRespawnLock() && isSayMessage())
		messageTimer -= 15;
	if (messageTimer < 0)
	    setSayMessage(false);
    }

    void updateBounding() {
	bounding.x = (int) f_posx;
	bounding.y = (int) f_posy;
	botBounding = new Rectangle((int) f_posx, ((int) f_posy + (30)), look.getWidth(), (look.getHeight() - 30)); /// !!!!!
    }

    public void updateCubes() {
	for (int i = 0; i < cube.length; i++) {
	    cube[i].updateBounding();
	}
    }

    void updateBigmek() {
	if (bigmek != null) {
	    bigmek.update(isScrollingLeft(), isScrollingRight());
	}
    }

    public void updateEnemies() {
	if (enemies != null)
	    for (int i = 0; i < enemies.length; i++) {
		if (isScrollingLeft())
		    enemies[i].setScrollingLeft(true);
		if (isScrollingRight())
		    enemies[i].setScrollingRight(true);
		map.getEnemies()[i].update(isScrollingLeft(), isScrollingRight());
	    }
    }

    void updateSweg() {
	if (sweg != null)
	    for (int i = 0; i < sweg.length; i++) {
		sweg[i].setBounding(sweg[i].update(isScrollingLeft(), isScrollingRight()));
	    }
    }

    void updateTimeDead() {
	if (!alive && lifes > 0)
	    timeDead += 15;
    }

    public boolean getLookingRight() {
	return lookingRight;
    }

    public Rectangle getBounding() {
	return bounding;
    }

    void respawn() {
	if (!isRespawnLock() && !alive && lifes > 0 && timeDead > 2000) {

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
	for (int i = 0; i < cube.length; i++) {
	    // überprüft onleftside
	    if (bounding.intersects(cube[i].getLeftBounding())) {
		onLeftSide = true;

	    }
	    // überprüft onrightside
	    if (bounding.intersects(cube[i].getRightBounding())) {
		onRightSide = true;
	    }

	    // überprüft onTop
	    if (botBounding.intersects(cube[i].getTopBounding())) {
		onTop = true;
	    }

	    // überprüft ob onBot
	    if (bounding.intersects(cube[i].getBotBounding())) {
		onBot = true;
	    }

	}

    }

    void move(boolean left, boolean right, boolean jump) {
	// links
	if (alive)
	    if (left && !onRightSide && !isScrollingLeft()) {
		f_posx -= moveSpeed;
		lookingRight = false;
	    }
	// rechts
	if (alive)
	    if (right && !onLeftSide && !isScrollingRight()) {
		f_posx += moveSpeed;
		lookingRight = true;
	    }
	// schwerkraft
	if (!onTop)
	    f_posy += schwerkraft;

	// jump
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
	if (sweg != null)
	    for (int i = 0; i < sweg.length; i++) {
		if (bounding.intersects(sweg[i].getBounding()) && sweg[i].isNotCollected() == true) {
		    sweg[i].setNotCollected(false);
		    sayMessage("monies");
		    swegCollected += 1;
		}
	    }
    }

    void checkBigmek() {
	if (bigmek != null && !bigmek.getCollected())
	    if (bounding.intersects(bigmek.getBounding())) {
		bigmek.setCollected();
		sayMessage("press enter to enter lvl two");
		setLvlUp(true);
		lockMessage();
	    }
    }

    void checkEnemies() {
	if (enemies != null)
	    for (int i = 0; i < enemies.length; i++) {
		// gegner töten
		if (enemies[i].getAlive())
		    if (alive)
			if (botBounding.intersects(enemies[i].getTopBounding())) {
			    enemies[i].kill();
			    sayMessage("lel");
			    kills += 1;
			}

		// feststellen ob tot
		if (alive)
		    if (bounding.intersects(enemies[i].getBounding()) && enemies[i].getAlive()) {
			alive = false;
			lifes -= 1;
			if (lifes > 0)
			    sayMessage("u got rekt 111  press spaec to respawn");
			if (lifes < 1)
			    sayMessage("g8 nao dis is mai end");
			setRespawnLock(true);
		    }

	    }
    }

    public BufferedImage getLook() {
	if (alive) {
	    if (lookingRight) {
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_right.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (!lookingRight)
		try {
		    look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/spoder_left.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	} else
	    try {
		look = ImageIO.read(getClass().getClassLoader().getResourceAsStream("img/blood.png"));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	return look;
    }

    void scroll() {
	setScrollingRight(false);
	setScrollingLeft(false);
	if (bounding.x > 0.6 * screenX && !onLeftSide && alive) {
	    setScrollingRight(true);
	    f_posx -= 0.2;
	}
	if (bounding.x < 0.4 * screenX && !onRightSide && alive) {
	    setScrollingLeft(true);
	    f_posx += (0.2);
	}
    }

    public int getSweg() {
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
	setSayMessage(true);
	this.messageTimer = Letters.getMessageTimer();

    }

    public boolean isSayMessage() {
	return sayMessage;
    }

    public void setSayMessage(boolean sayMessage) {
	this.sayMessage = sayMessage;
    }

    public boolean isRespawnLock() {
	return respawnLock;
    }

    public void setRespawnLock(boolean respawnLock) {
	this.respawnLock = respawnLock;
    }

    public boolean isScrollingLeft() {
	return scrollingLeft;
    }

    public void setScrollingLeft(boolean scrollingLeft) {
	this.scrollingLeft = scrollingLeft;
    }

    public boolean isScrollingRight() {
	return scrollingRight;
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
