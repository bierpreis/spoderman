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
    private int screenX = Config.getScreenX();
    private static int moveSpeed = Config.getPlayerMoveSpeed();

    private float f_posx; // f_ als kennzeichen für float
    private float f_posy;

    private Rectangle bounding;
    private Rectangle botBounding;
    private BufferedImage lookingLeft, lookingRight, lookDead;

    private BufferedImage[] messagePicArray;

    private Lvl lvl;


    // konstruktor
    public Player(Lvl lvl) {
	f_posx = 350;
	f_posy = 300;

	createLook();

	bounding = new Rectangle(x, y, lookingLeft.getWidth(), lookingLeft.getHeight());
	botBounding = new Rectangle(x, (y + (lookingLeft.getHeight())), lookingLeft.getWidth(), (20));



	this.lvl = lvl;

	sayMessage("nao i need to find teh bikmek");

    }

    public void update(boolean left, boolean right, boolean jump) {


	updateCubes(); 

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
	for (int i = 0; i < lvl.getCubes().length; i++) {
	    lvl.getCubes()[i].updateBounding(scrollingLeft, scrollingRight, moveSpeed);
	}
    }

    void updateBigmek() {
	if (lvl.getBigmek() != null) {
	    lvl.getBigmek().getBounding().scroll(scrollingLeft, scrollingRight);
	}
    }

    public void updateEnemies() {
	if (lvl.getEnemy()!= null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		lvl.getEnemy()[i].updateEnemy(scrollingLeft, scrollingRight);
	    }
    }

    void updateSweg() {
	if (lvl.getSweg() != null)
	    for (int i = 0; i < lvl.getSweg().length; i++) {
		lvl.getSweg()[i].getBounding().scroll(scrollingLeft, scrollingRight);
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
	for (int i = 0; i < lvl.getCubes().length; i++) {
	    // überprüft onleftside
	    if (bounding.intersects(lvl.getCubes()[i].getLeftBounding())) {
		onLeftSide = true;

	    }
	    // überprüft onrightside
	    if (bounding.intersects(lvl.getCubes()[i].getRightBounding())) {
		onRightSide = true;
	    }

	    // überprüft onTop
	    if (botBounding.intersects(lvl.getCubes()[i].getTopBounding())) {
		onTop = true;
	    }

	    // überprüft ob onBot
	    if (bounding.intersects(lvl.getCubes()[i].getBotBounding())) {
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
	    f_posy += Config.getGravity();

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
	if (lvl.getSweg() != null)
	    for (int i = 0; i < lvl.getSweg().length; i++) {
		if (!lvl.getSweg()[i].getCollected())
		    if (bounding.intersects(lvl.getSweg()[i].getBounding())) {
			lvl.getSweg()[i].setCollected();
			sayMessage("monies");
			swegCollected += 1;
		    }
	    }
    }

    void checkBigmek() {
	if (lvl.getBigmek() != null && !lvl.getBigmek().getCollected())
	    if (bounding.intersects(lvl.getBigmek().getBounding())) {
		lvl.getBigmek().setCollected();
		sayMessage("press enter to enter lvl two");
		lvlUp = true;
		lockMessage();
	    }
    }

    void checkEnemies() {
	if (lvl.getEnemy() != null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		// gegner töten
		if (lvl.getEnemy()[i].getAlive())

		    if (botBounding.intersects(lvl.getEnemy()[i].getTopBounding())) {
			lvl.getEnemy()[i].kill();
			sayMessage("lel rekt");
			kills += 1;
		    }

		// feststellen ob tot
		if (alive)
		    if (bounding.intersects(lvl.getEnemy()[i].getTopBounding()) && lvl.getEnemy()[i].getAlive()) {
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
