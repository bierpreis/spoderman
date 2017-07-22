package game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import general.Bounding;
import general.Config;
import general.KeyHandler;
import general.Message;
import map.Lvl;

public class Player {

    private boolean onTop = false;
    private boolean onBot = false;
    private boolean onRightSide;
    private boolean onLeftSide;

    private boolean isLookingRight = true;
    
    private boolean scrollingRight = false;
    private boolean scrollingLeft = false;
    private boolean alive = true;

    private int timeSinceJump;
    private int upJumpTime = 190;
    private int jumpTime = 700;
    private boolean alreadyJumped = false;
    private boolean jumpReleased = true;
    private boolean jumping = false;

    private int swegCollected = 0;
    private int kills = 0;
    private int lifes = 3;

    private boolean showEscDialog = false;

    private int timeDead = 0;

    private float f_posx = 350; // f_ als kennzeichen für float
    private float f_posy = 300;

    private Bounding bounding;
    private Bounding botBounding;

    private BufferedImage lookingLeft, lookingRight, lookDead;

    private Message message = null;

    private Lvl lvl;

    // konstruktor
    public Player(Lvl lvl) {

	createLook();

	bounding = new Bounding((int) f_posx, (int) f_posy, lookingLeft.getWidth(), lookingLeft.getHeight());
	botBounding = new Bounding((int) f_posx, (int) f_posy + 20, lookingLeft.getWidth(), 20);

	this.lvl = lvl;

	createMessage("nao i need to find teh bikmek");

    }

    public String update(KeyHandler keyHandler) {

	updateCubes();
	updateBigmek();
	updateEnemies();
	updateSweg();

	checkSweg();
	checkCubes();
	checkEnemies();
	checkBigMek();

	// already called in checkifrespawn
	// updateTimeDead();
	checkRespawn(keyHandler);
	updateBounding();
	scroll();
	move(keyHandler);
	jump(keyHandler);

	if (checkIfEscape(keyHandler))
	    return "EXIT";

	

	if (getLvlUp(keyHandler))
	    return "LVL_UP";

	return "CONTINUE";

    }

    private boolean checkIfEscape(KeyHandler keyHandler) {

	if (showEscDialog) {
	    long nextActionTime = System.currentTimeMillis() + 2000;

	    while (System.currentTimeMillis() < nextActionTime) {

		if (keyHandler.getEnter())
		    return true;
		try {
		    Thread.sleep(10);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
	showEscDialog = false;
	if (keyHandler.getEscape()) {
	    showEscDialog = true;
	    createMessage("press enter to get out and fak awf");
	}
	return false;

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

    void updateBounding() {
	bounding.x = (int) f_posx;
	bounding.y = (int) f_posy;
	botBounding.x = (int) f_posx;
	botBounding.y = (int) f_posy + 20;

    }

    public void updateCubes() {
	for (int i = 0; i < lvl.getCubes().length; i++) {
	    lvl.getCubes()[i].updateBounding(scrollingLeft, scrollingRight);
	}
    }

    void updateBigmek() {
	if (lvl.getBigmek() != null) {
	    lvl.getBigmek().getBounding().scroll(scrollingLeft, scrollingRight);
	}
    }

    public void updateEnemies() {
	if (lvl.getEnemy() != null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		lvl.getEnemy()[i].update(scrollingLeft, scrollingRight);
	    }
    }

    void updateSweg() {
	if (lvl.getSweg() != null)
	    for (int i = 0; i < lvl.getSweg().length; i++) {
		lvl.getSweg()[i].getBounding().scroll(scrollingLeft, scrollingRight);
	    }
    }

    public Rectangle getBounding() {
	return bounding;
    }

    boolean checkRespawn(KeyHandler keyHandler) {

	if (alive)
	    return false;

	if (lifes < 0)
	    return false;

	timeDead += 15;

	if (keyHandler.getSpace() && timeDead > Config.getPlayerRespawnTime()) {

	    alive = true;

	    createMessage("respawn  now am angri as fuk");
	    timeDead = 0;
	    return true;
	}

	return false;
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

    void move(KeyHandler keyHandler) {

	if (alive) {
	    if (keyHandler.getLeft() && !onRightSide && !scrollingLeft) {
		f_posx -= Config.getPlayerMoveSpeed();
		isLookingRight = false;
	    }

	    if (keyHandler.getRight() && !onLeftSide && !scrollingRight) {
		f_posx += Config.getPlayerMoveSpeed();
		isLookingRight = true;
	    }
	}
	// schwerkraft
	if (!onTop)
	    f_posy += Config.getGravity();

    }

    void jump(KeyHandler keyHandler) {
	if (alive)
	    if (onTop && (!onBot && keyHandler.getSpace() && !alreadyJumped && jumpReleased)) {
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
			createMessage("monies");
			swegCollected += 1;
		    }
	    }
    }

    void checkBigMek() {
	if (lvl.getBigmek() != null && !lvl.getBigmek().getCollected())
	    if (bounding.intersects(lvl.getBigmek().getBounding())) {
		lvl.getBigmek().setCollected();
		createMessage("press enter to enter lvl two");

	    }

    }

    public boolean getLvlUp(KeyHandler keyHandler) {
	if (lvl.getBigmek() == null)
	    return false;
	return (lvl.getBigmek().getCollected() && keyHandler.getEnter());
    }

    void checkEnemies() {
	if (lvl.getEnemy() != null)
	    for (int i = 0; i < lvl.getEnemy().length; i++) {
		// gegner töten
		if (lvl.getEnemy()[i].getAlive())
		    if (botBounding.intersects(lvl.getEnemy()[i].getTopBounding())) {
			lvl.getEnemy()[i].kill();
			createMessage("lel rekt");
			kills += 1;
		    }

		// feststellen ob tot
		if (alive)
		    if (bounding.intersects(lvl.getEnemy()[i].getBounding()) && lvl.getEnemy()[i].getAlive()) {
			alive = false;
			lifes -= 1;
			if (lifes > 0)
			    createMessage("u got rekt 111  press spaec to respawn");
			if (lifes < 1)
			    createMessage("g8 nao dis is mai end");
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

	return null;
    }

    void scroll() {
	scrollingLeft = false;
	scrollingRight = false;
	if (bounding.x > 0.6 * Config.getScreenX() && !onLeftSide && alive) {
	    scrollingRight = true;
	    f_posx -= 0.2;
	}
	if (bounding.x < 0.4 * Config.getScreenX() && !onRightSide && alive) {
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

    public Message createMessage(String messageString) {
	return message = new Message(messageString);
    }

    public Message getNewMessage() {
	Message messageToReturn = null;

	messageToReturn = message;
	message = null;
	return messageToReturn;
    }

}
