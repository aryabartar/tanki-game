package Engine; /*** In The Name of Allah ***/

import Blocks.Block;
import Blocks.Chariot;
import Blocks.DestroyableBlock;
import Blocks.UnDestroyableBlock;
import EnemyTanks.*;
import Equipment.*;
import Network.GameClient;
import Network.GameServer;
import Others.Geometry;
import Others.Point;
import Serialization.Serialize;
import javafx.scene.layout.BackgroundImage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author ... :D
 */
public class GameState implements Serializable {

    //	public int locX, locY, diam;
    public static boolean gameOver;
    public static boolean firstLevel;
    public static int difficultyLevel = 2; // use this later
    public boolean fullyFinished;

    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mouseLeftClicked, mouseRightClicked; //mouseRightClicked : false => bullet / true => rocket
    private int mouseX, mouseY; // for clicking
    private int click = 0;
    private int mouseMotionX, mouseMotionY; //for mouse motion
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;


    private Tank mainTank;
    private GameClient gameClient;
    private GameServer gameServer;
    private GameStateInformation gameStateInformation;

    private static ArrayList<Bullet> bullets;
    private static ArrayList<Rocket> rockets;
    private static ArrayList<EnemyTank> enemyTanks;
    private static ArrayList<Block> blocks;
    private static ArrayList<Equipment> equipments;
    private static ArrayList<Point> destroyedTankTemporaryTrashPoints;
    private static ArrayList<MovingSmile> movingSmiles;

    public GameState() {
        gameStateInformation = new GameStateInformation();
        setArrayLists();


        firstLevel = true;
        fullyFinished = false;

        mainTank = new Tank();
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mouseLeftClicked = false;
        mouseRightClicked = false;

        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        playBackgroundMusic();
    }

    private void initClientServer() {
        boolean optionPaneNumber;
        optionPaneNumber = JOptionPane.showConfirmDialog(null, "Do you want to run the server ?") == 0;


        GameServer gameServer;
        GameClient gameClient;

        if (optionPaneNumber == true) {
            gameServer = new GameServer(null);
            gameServer.start();
        }

        gameClient = new GameClient(null, "localhost");
        gameClient.start();

        gameClient.sendData("ping".getBytes());
    }


    /**
     * The method which updates the game state.
     */
    public void update() {
        if (mouseLeftClicked) {

            if (mouseRightClicked == false) {//bullet

                if (mainTank.getBulletsNumber() > 0)
                    bullets.add(new Bullet(mainTank.getTankCenterX(), mainTank.getTankCenterY(), mainTank.getGunAndBodyRadian(), false));
                mainTank.reduceBulletNumber();
            } else {//rocket
                if (mainTank.getRocketsNumber() > 0)
                    rockets.add(new Rocket(mainTank.getTankCenterX(), mainTank.getTankCenterY(), mainTank.getGunAndBodyRadian(), false));
                mainTank.reduceRocketNumbers();
            }
            mouseLeftClicked = !mouseLeftClicked;
        }

        if (keyUP)
            mainTank.moveLocY(-11);
        if (keyDOWN)
            mainTank.moveLocY(11);
        if (keyLEFT)
            mainTank.moveLocX(-11);
        if (keyRIGHT)
            mainTank.moveLocX(11);

        try {

            //moves bullets
            for (Bullet bullet : bullets) {
                if (bullet != null)
                    bullet.move();
            }
        } catch (ConcurrentModificationException e) {

        }

        try {
            for (Rocket rocket : rockets) {
                if (rocket != null)
                    rocket.move();

            }
        } catch (ConcurrentModificationException e) {

        }

        setMainTankAndGunRadian();
        setEnemyTanksRadian();

        moveDynamicTanks();
        updateAlphaInEquipments();
        checkShootHits();
        removeDeadBullets();
        removeDeadTanks();
        removeDestroyedBlocks();
        checkHitToEquipments();
        renderDestroyedTankPoints();
        attackMovingSmiles();
        findSmileFacesIntersects();
        checkTheGameFinish();

    }

    private void checkTheGameFinish() {
        Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());
        Rectangle door = new Rectangle(4800, 1800, 100, 100);

        //erfan kamel she
        if ((door.intersects(mainTankRec)) && (firstLevel == false)) {
            ImageIcon icon = new ImageIcon("./pictures/skull.png");
            JOptionPane.showMessageDialog(null,
                    "LEVEL 2 IS FINISHED !", "  FINISHED!",
                    JOptionPane.INFORMATION_MESSAGE, icon);
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.die();
            }
        }

        if ((door.intersects(mainTankRec)) && (firstLevel == true)) {
            ImageIcon icon = new ImageIcon("./pictures/skull.png");
            JOptionPane.showMessageDialog(null,
                    "LEVEL 1 IS COMPLETED !", "  FINISHED!",
                    JOptionPane.INFORMATION_MESSAGE, icon);
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.die();
            }

            gameStateInformation.setLevelTwoMap();
            mainTank = gameStateInformation.getMainTank() ;
            setArrayLists();

            keyRIGHT = false;
            keyUP = false;
            keyLEFT = false;
            keyDOWN = false;
        }

    }


    private void setArrayLists() {
        mainTank = gameStateInformation.getMainTank();
        bullets = gameStateInformation.getBullets();
        rockets = gameStateInformation.getRockets();
        enemyTanks = gameStateInformation.getEnemyTanks();
        blocks = gameStateInformation.getBlocks();
        equipments = gameStateInformation.getEquipments();
        destroyedTankTemporaryTrashPoints = gameStateInformation.getDestroyedTankTemporaryTrashPoints();
        movingSmiles = gameStateInformation.getMovingSmiles();
    }


    private void findSmileFacesIntersects() {
        for (int i = 0; i < movingSmiles.size(); i++) {
            Rectangle smileFaceRec = new Rectangle(movingSmiles.get(i).getLocX(), movingSmiles.get(i).getLocY(), MovingSmile.xPixels, MovingSmile.yPixels);
            Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());

            if (smileFaceRec.intersects(mainTankRec)) {
                Point tempPoint = new Point(movingSmiles.get(i).getLocX(), movingSmiles.get(i).getLocY());
                destroyedTankTemporaryTrashPoints.add(tempPoint);
                mainTank.reduceHealth(MovingSmile.DAMAGE);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/MineBoom.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                movingSmiles.remove(i);
                i--;
            }
        }
    }

    private void attackMovingSmiles() {
        for (MovingSmile movingSmile : movingSmiles) {
            movingSmile.attackToThisLocation(mainTank.getTankCenterX(), mainTank.getTankCenterY());

        }
    }

    private void renderDestroyedTankPoints() {
        for (int i = 0; i < destroyedTankTemporaryTrashPoints.size(); i++) {
            destroyedTankTemporaryTrashPoints.get(i).reduceTimeToRemove(3);

            if (destroyedTankTemporaryTrashPoints.get(i).getTimeToRemove() < 1)
                destroyedTankTemporaryTrashPoints.remove(i);
        }
    }

    private void checkHitToEquipments() {

        for (int i = 0; i < equipments.size(); i++) {
            try {

                Rectangle mainTankRec = new Rectangle(mainTank.getLocX(), mainTank.getLocY(), mainTank.getxPixels(), mainTank.getyPixels());
                Rectangle equipmentRec = new Rectangle(equipments.get(i).getLocX(), equipments.get(i).getLocY(), equipments.get(i).getxPixels(), equipments.get(i).getyPixels());

                if (mainTankRec.intersects(equipmentRec)) {

                    if (equipments.get(i) instanceof Cartridge) {
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/select.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        mainTank.addToBullets(50);
                        mainTank.addToRockets(30);
                    }
                    if (equipments.get(i) instanceof UpdateWeapon) {
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/agree.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        mainTank.updateWeapon();
                    }
                    if (equipments.get(i) instanceof Repair) {
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/repair.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        mainTank.addToHealth(30);
                    }

                    equipments.remove(i);
                    i--;
                }
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }

    private void updateAlphaInEquipments() {
        for (Equipment equipment : equipments) {
            equipment.updateAlpha();
        }
    }

    private void removeDestroyedBlocks() {

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).isAlive() == false) {
                blocks.remove(i);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/softwall.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                i--;
            }
        }

    }

    private void moveDynamicTanks() {
        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank instanceof DynamicTankEasy) {
                ((DynamicTankEasy) enemyTank).moveAutomatic(mainTank);
            }
            if (enemyTank instanceof DynamicTankHard) {
                ((DynamicTankHard) enemyTank).moveAutomatic(mainTank);
            }
        }
    }

    private void setEnemyTanksRadian() {
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.setGunAndBodyRadian(Geometry.radian(enemyTank.getTankCenterX(), enemyTank.getTankCenterY(),
                    mainTank.getTankCenterX(), mainTank.getTankCenterY()));
        }
    }

    private void setMainTankAndGunRadian() {
        int mainX = getMainTank().getTankCenterX() - GameFrame.TANK_IN_MAP_X;
        int mainY = getMainTank().getTankCenterY() - GameFrame.TANK_IN_MAP_Y;

        mainTank.setGunAndBodyRadian(Geometry.radian(getMainTank().getTankCenterX() - mainX, getMainTank().getTankCenterY() - mainY,
                getMouseMotionX(), getMouseMotionY()));

    }


    public KeyListener getKeyListener() {
        return keyHandler;
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/motor1.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();

                if (GameLoop.isPaused == false)
                    clip.open(audioInputStream);

                clip.start();
                clip.loop(0);
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }


            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    keyUP = true;
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = true;
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = true;
                    break;

                case KeyEvent.VK_ESCAPE:
                    GameLoop.isPaused = !GameLoop.isPaused;
                    System.out.println(GameLoop.isPaused);
                    break;

                case KeyEvent.VK_1:
                    mainTank.addToHealth(30);
                    break;

                case KeyEvent.VK_2:
                    mainTank.updateWeapon();
                    break;

                case KeyEvent.VK_3: {
                    mainTank.addToBullets(100);
                    mainTank.addToRockets(60);
                    break;
                }

                case KeyEvent.VK_4: {
                    int X = mainTank.getLocX();
                    int Y = mainTank.getLocY();
                    blocks.add(new DestroyableBlock(X + 140, Y));
                    break;
                }

                case KeyEvent.VK_5: {
                    int X = mainTank.getLocX();
                    int Y = mainTank.getLocY();
                    blocks.add(new DestroyableBlock(X, Y + 140));
                    break;
                }

                case KeyEvent.VK_6: {
                    GameFrame.isCheated = !GameFrame.isCheated;
                }

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_W:
                    keyUP = false;
                    break;
                case KeyEvent.VK_S:
                    keyDOWN = false;
                    break;
                case KeyEvent.VK_A:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_D:
                    keyRIGHT = false;
                    break;
            }
        }
    }

    /**
     * This method will check if the bullets/rockets hit the enemy .
     */
    private void checkShootHits() {
        for (EnemyTank enemyTank : enemyTanks) {
            for (int i = 0; i < rockets.size(); i++) {
                try {
                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > enemyTank.getLocX()) && (rockets.get(i).getLocX() < enemyTank.getEndLocX()) &&
                                (rockets.get(i).getLocY() > enemyTank.getLocY()) && (rockets.get(i).getLocY() < enemyTank.getEndLocY())) {
                            if (rockets.get(i).isFromEnemy() == false) {
                                try {
                                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemyshot.wav").getAbsoluteFile());
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioInputStream);
                                    clip.start();
                                    clip.loop(0);
                                } catch (Exception ex) {
                                    System.out.println("Error with playing sound.");
                                    ex.printStackTrace();
                                }
                                enemyTank.reduceHealth(Rocket.DAMAGE + mainTank.getGunLevel() * 2);
                            }
                            rockets.remove(i);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }

        for (int j = 0; j < movingSmiles.size(); j++) {
            for (int i = 0; i < rockets.size(); i++) {
                try {
                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > movingSmiles.get(j).getLocX()) && (rockets.get(i).getLocX() < movingSmiles.get(j).getLocX() + MovingSmile.xPixels) &&
                                (rockets.get(i).getLocY() > movingSmiles.get(j).getLocY()) && (rockets.get(i).getLocY() < movingSmiles.get(j).getLocY() + MovingSmile.yPixels)) {
                            if (rockets.get(i).isFromEnemy() == false) {
                                System.out.printf("tir mizanam3");
                                Point tempPoint = new Point(movingSmiles.get(j).getLocX(), movingSmiles.get(j).getLocY());
                                destroyedTankTemporaryTrashPoints.add(tempPoint);
                                movingSmiles.remove(j);
                                rockets.remove(i);
                                j--;
                                break;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }

        }

        for (int j = 0; j < movingSmiles.size(); j++) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > movingSmiles.get(j).getLocX()) && (bullets.get(i).getLocX() < movingSmiles.get(j).getLocX() + MovingSmile.xPixels) &&
                            (bullets.get(i).getLocY() > movingSmiles.get(j).getLocY()) && (bullets.get(i).getLocY() < movingSmiles.get(j).getLocY() + MovingSmile.yPixels)) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            System.out.printf("tir mizanam2");
                            Point tempPoint = new Point(movingSmiles.get(j).getLocX(), movingSmiles.get(j).getLocY());
                            destroyedTankTemporaryTrashPoints.add(tempPoint);
                            movingSmiles.remove(j);
                            j--;
                            bullets.remove(i);
                            break;
                        }
                    }
                }
            }

        }


        for (EnemyTank enemyTank : enemyTanks) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > enemyTank.getLocX()) && (bullets.get(i).getLocY() > enemyTank.getLocY()) &&
                            (bullets.get(i).getLocX() < enemyTank.getEndLocX()) && (bullets.get(i).getLocY() < enemyTank.getEndLocY())) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            try {
                                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemyshot.wav").getAbsoluteFile());
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioInputStream);
                                clip.start();
                                clip.loop(0);
                            } catch (Exception ex) {
                                System.out.println("Error with playing sound.");
                                ex.printStackTrace();
                            }
                            enemyTank.reduceHealth(Bullet.DAMAGE + mainTank.getGunLevel() * 3);
                        }
                        bullets.remove(i);
                    }
                }
            }
        }


        for (Block block : blocks) {
            for (int i = 0; i < rockets.size(); i++) {
                try {


                    if (rockets.get(i) != null) {
                        if ((rockets.get(i).getLocX() > block.getLocX()) && (rockets.get(i).getLocX() < block.getEndX()) &&
                                (rockets.get(i).getLocY() > block.getLocY()) && (rockets.get(i).getLocY() < block.getEndY())) {

                            if (rockets.get(i).isFromEnemy() == false) {
                                block.reduceHealth(Rocket.DAMAGE);
                            }

                            rockets.remove(i);

                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }
        }

        for (Block block : blocks) {
            for (int i = 0; i < bullets.size(); i++) {

                if (bullets.get(i) != null) {
                    if ((bullets.get(i).getLocX() > block.getLocX()) && (bullets.get(i).getLocY() > block.getLocY()) &&
                            (bullets.get(i).getLocX() < block.getEndX()) && (bullets.get(i).getLocY() < block.getEndY())) {
                        if (bullets.get(i).isFromEnemy() == false) {
                            block.reduceHealth(Bullet.DAMAGE);
                        }
                        bullets.remove(i);
                    }
                }
            }
        }


        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i) != null) {
                if ((rockets.get(i).getLocX() > mainTank.getLocX()) && (rockets.get(i).getLocX() < mainTank.getEndLocX()) &&
                        (rockets.get(i).getLocY() > mainTank.getLocY()) && (rockets.get(i).getLocY() < mainTank.getEndLocY())) {

                    if (rockets.get(i).isFromEnemy() == true) {

                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemyshot.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        mainTank.reduceHealth(Rocket.DAMAGE);
                    }
                    rockets.remove(i);
                }
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i) != null) {
                if ((bullets.get(i).getLocX() > mainTank.getLocX()) && (bullets.get(i).getLocY() > mainTank.getLocY()) &&
                        (bullets.get(i).getLocX() < mainTank.getEndLocX()) && (bullets.get(i).getLocY() < mainTank.getEndLocY())) {
                    if (bullets.get(i).isFromEnemy() == true) {
                        try {
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemyshot.wav").getAbsoluteFile());
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        } catch (Exception ex) {
                            System.out.println("Error with playing sound.");
                            ex.printStackTrace();
                        }
                        mainTank.reduceHealth(Bullet.DAMAGE);
                    }
                    bullets.remove(i);
                }
            }
        }
    }

    private void removeDeadBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i) != null)
                if (bullets.get(i).checkAlive() == false) {
                    bullets.remove(i);
                    i -= 1;
                }
        }
        for (int i = 0; i < rockets.size(); i++) {
            if (rockets.get(i) != null)
                if (rockets.get(i).checkAlive() == false) {
                    rockets.remove(i);
                    i--;
                }
        }
    }

    public void removeDeadTanks() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            if (enemyTanks.get(i).getHealth() < 1) {
                Point tempPoint = new Point(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY());
                destroyedTankTemporaryTrashPoints.add(tempPoint);

                //Random equipment
                SecureRandom random = new SecureRandom();
                int rand = random.nextInt(7);

                if (rand == 0) {
                    equipments.add(new Cartridge(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 1) {
                    equipments.add(new Repair(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 2) {
                    equipments.add(new UpdateWeapon(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }
                if (rand == 3) {
                    movingSmiles.add(new MovingSmile(enemyTanks.get(i).getLocX(), enemyTanks.get(i).getLocY()));
                }


                enemyTanks.remove(i);
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/enemydestroyed.wav").getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(0);
                } catch (Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                i--;
            }
        }
    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            if (SwingUtilities.isLeftMouseButton(e)) {
                if ((GameLoop.isPaused == true) && ((mouseX > 132) && (mouseX < 391) && (mouseY > 330) && (mouseY < 400))) {
                    GameLoop.isPaused = false;
                    playSelectMusic();
                }
                if ((GameLoop.isPaused == true) && ((mouseX > 132) && (mouseX < 280) && (mouseY > 613) && (mouseY < 678))) {
                    playSelectMusic();
                    System.exit(0);
                }
                if ((GameLoop.isPaused == true) && ((mouseX > 134) && (mouseX < 310) && (mouseY > 470) && (mouseY < 540))) {
                    Serialize.serializeObject(this);
                }


                mouseLeftClicked = true;
                if (click % 2 == 1) {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/heavygun.wav").getAbsoluteFile());

                        if (GameLoop.isPaused == false) {
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }


                } else {
                    try {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/EnemyBulletToMyTank.wav").getAbsoluteFile());
                        Clip clip = AudioSystem.getClip();
                        if (GameLoop.isPaused == false) {
                            clip.open(audioInputStream);
                            clip.start();
                            clip.loop(0);
                        }
                    } catch (Exception ex) {
                        System.out.println("Error with playing sound.");
                        ex.printStackTrace();
                    }

                }
            }
            if (SwingUtilities.isRightMouseButton(e)) {
                mouseRightClicked = !mouseRightClicked;
                click++;
            }
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            mouseLeftClicked = false;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
//			mouseX = e.getX();
//			mouseY = e.getY();
        }

        // for moving mouse !
        @Override
        public void mouseMoved(MouseEvent e) {
            mouseMotionX = e.getX();
            mouseMotionY = e.getY();
        }
    }

    public Tank getMainTank() {
        return mainTank;
    }

    public int getMouseMotionX() {
        return mouseMotionX;
    }

    public int getMouseMotionY() {
        return mouseMotionY;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public static ArrayList<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void addToBullets(Bullet bullet) {
        bullets.add(bullet);
    }

    public static void addToRockets(Rocket rocket) {
        try {
            rockets.add(rocket);
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public static ArrayList<Point> getDestroyedTankTemporaryTrashPoints() {
        return destroyedTankTemporaryTrashPoints;
    }

    public static ArrayList<MovingSmile> getMovingSmiles() {
        return movingSmiles;
    }

    public static void setDifficultyLevel(int difficultyLevel) {
        GameState.difficultyLevel = difficultyLevel;
    }

    private void playBackgroundMusic() {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./sound/background.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
        clip.start();
    }


    private void playSelectMusic() {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(
                    new File("./sound/tick.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        gainControl.setValue(-3.0f); // Reduce volume by 10 decibels.
        clip.start();
    }
}