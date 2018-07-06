package Engine;

import Blocks.Block;
import Blocks.DestroyableBlock;
import Blocks.UnDestroyableBlock;
import EnemyTanks.*;
import Equipment.*;
import Others.Point;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 */
public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 1000;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio

    private BufferedImage mainTankImage;
    private BufferedImage mainTankGun;
    private BufferedImage bulletImage;
    private BufferedImage rocketImage;
    private BufferedImage staticTankEasyBodyImage;
    private BufferedImage staticTankHardBodyImage;
    private BufferedImage dynamicTankEasyBodyImage;
    private BufferedImage dynamicTankHardBodyImage;
    private BufferedImage enemyTankGunImage;
    private BufferedImage unDestroyableBlockImage;
    private BufferedImage destroyableBlockImage;
    private BufferedImage updateWeaponImage;
    private BufferedImage repairImage;
    private BufferedImage cartridgeImage;
    private BufferedImage destroyedTankImage;
    private BufferedImage movingSmileImage;
    private BufferedImage movingSleepImage;

    private long lastRender;
    private ArrayList<Float> fpsHistory;
    private BufferStrategy bufferStrategy;

    private int mainX;
    private int mainY;

    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

        try {
            mainTankImage = ImageIO.read(new File("./pictures/tank-body.png"));
            mainTankGun = ImageIO.read(new File("./pictures/tank-gun.jpg"));
            bulletImage = ImageIO.read(new File("./pictures/bullet1.png"));
            rocketImage = ImageIO.read(new File("./pictures/bullet3.png"));
            staticTankEasyBodyImage = ImageIO.read(new File("./pictures/enemy-tank-body1.png"));
            staticTankHardBodyImage = ImageIO.read(new File("./pictures/enemy-tank-body4.png"));
            dynamicTankEasyBodyImage = ImageIO.read(new File("./pictures/enemy-tank-body2.png"));
            dynamicTankHardBodyImage = ImageIO.read(new File("./pictures/enemy-tank-body3.png"));
            enemyTankGunImage = ImageIO.read(new File("./pictures/enemy-gun.jpg"));
            unDestroyableBlockImage = ImageIO.read(new File("./pictures/wall1.png"));
            destroyableBlockImage = ImageIO.read(new File("./pictures/wall2.png"));
            updateWeaponImage = ImageIO.read(new File("./pictures/update-weapon.png"));
            repairImage = ImageIO.read(new File("./pictures/repair.png"));
            cartridgeImage = ImageIO.read(new File("./pictures/cartridge.png"));
            destroyedTankImage = ImageIO.read(new File("./pictures/destroyed-tank.png"));
            movingSmileImage = ImageIO.read(new File("./pictures/smile.png"));
            movingSleepImage = ImageIO.read(new File("./pictures/sleep.png"));


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {

        mainX = state.getMainTank().getTankCenterX() -888 ;
        mainY = state.getMainTank().getTankCenterY() -500 ;

        // Draw background
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


        for (Point destroyedTankPoint : state.getDestroyedTankTemporaryTrashPoints()) {
            Composite backupComposite = g2d.getComposite();

            int alpha = destroyedTankPoint.getTimeToRemove(); //draw half transparent
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha / 100);
            g2d.setComposite(ac);
            g2d.drawImage(destroyedTankImage, destroyedTankPoint.getX() - mainX, destroyedTankPoint.getY() - mainY, null);

            g2d.setComposite(backupComposite);
        }


        g2d.drawImage(mainTankImage, state.getMainTank().getLocX() - mainX, state.getMainTank().getLocY() - mainY, null);

        try {
            for (Bullet bullet : state.getBullets()) {
                try {
                    AffineTransform backup = g2d.getTransform();
                    AffineTransform trans = new AffineTransform();
                    trans.rotate(bullet.getRadian(), bullet.getLocX() - mainX, bullet.getLocY() - mainY); // the points to rotate around (the center in my example, your left side for your problem)

                    g2d.transform(trans);
                    g2d.drawImage(bulletImage, bullet.getLocX()- mainX, bullet.getLocY() - mainY, null);  // the actual location of the sprite
                    g2d.setTransform(backup); // restore previous transform
                } catch (NullPointerException | ConcurrentModificationException e) {

                }
            }
        } catch (ConcurrentModificationException e) {

        }

        try {
            for (Rocket rocket : state.getRockets()) {
                try {


                    AffineTransform trans = new AffineTransform();
                    AffineTransform backup = g2d.getTransform();
                    trans.rotate(rocket.getRadian(), rocket.getLocX() - mainX, rocket.getLocY() - mainY); // the points to rotate around (the center in my example, your left side for your problem)

                    g2d.transform(trans);
                    g2d.drawImage(rocketImage, rocket.getLocX() - mainX, rocket.getLocY() - mainY, null);  // the actual location of the sprite
                    g2d.setTransform(backup); // restore previous transform
                } catch (NullPointerException | ConcurrentModificationException e) {

                }
            }
        } catch (ConcurrentModificationException e) {

        }


        for (Equipment equipment : state.getEquipments()) {
            Composite backupComposite = g2d.getComposite();

            if (equipment instanceof UpdateWeapon) {
                double alpha = equipment.getAlpha(); //draw half transparent
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                g2d.setComposite(ac);
                g2d.drawImage(updateWeaponImage, equipment.getLocX() - mainX, equipment.getLocY() - mainY, null);
            }

            if (equipment instanceof Repair) {
                double alpha = equipment.getAlpha(); //draw half transparent
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                g2d.setComposite(ac);
                g2d.drawImage(repairImage, equipment.getLocX() - mainX, equipment.getLocY() - mainY, null);
            }
            if (equipment instanceof Cartridge) {
                double alpha = equipment.getAlpha(); //draw half transparent
                AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
                g2d.setComposite(ac);
                g2d.drawImage(cartridgeImage, equipment.getLocX() - mainX, equipment.getLocY() - mainY, null);
            }
            g2d.setComposite(backupComposite);

        }

        for (Block block : state.getBlocks()) {
            if (block instanceof UnDestroyableBlock) {
                g2d.drawImage(unDestroyableBlockImage, block.getLocX() - mainX, block.getLocY() - mainY, null);
            }
            if (block instanceof DestroyableBlock) {
                g2d.drawImage(destroyableBlockImage, block.getLocX() - mainX , block.getLocY() - mainY, null);
            }
        }

        for (EnemyTank enemyTank : state.getEnemyTanks()) {
            if (enemyTank instanceof StaticTankEasy) {
                g2d.drawImage(staticTankEasyBodyImage, enemyTank.getLocX() - mainX, enemyTank.getLocY() - mainY, null);
            }
            if (enemyTank instanceof StaticTankHard) {
                g2d.drawImage(staticTankHardBodyImage, enemyTank.getLocX() - mainX, enemyTank.getLocY() - mainY, null);
            }
            if (enemyTank instanceof DynamicTankEasy) {
                g2d.drawImage(dynamicTankEasyBodyImage, enemyTank.getLocX() - mainX, enemyTank.getLocY() - mainY, null);
            }
            if (enemyTank instanceof DynamicTankHard) {
                g2d.drawImage(dynamicTankHardBodyImage, enemyTank.getLocX() - mainX, enemyTank.getLocY() - mainY, null);
            }

            AffineTransform backup = g2d.getTransform();
            AffineTransform trans = new AffineTransform();
            trans.rotate(enemyTank.getGunAndBodyRadian(), enemyTank.getTankCenterX() - mainX, enemyTank.getTankCenterY() - mainY); // the points to rotate around (the center in my example, your left side for your problem)

            g2d.transform(trans);
            g2d.drawImage(enemyTankGunImage, enemyTank.getGunLocX() - mainX, enemyTank.getGunLocY() - mainY, null);  // the actual location of the sprite
            g2d.setTransform(backup); // restore previous transform
        }

        for (MovingSmile movingSmile : state.getMovingSmiles()) {
            if (movingSmile.isSmiling() == true)
                g2d.drawImage(movingSmileImage, movingSmile.getLocX() - mainX, movingSmile.getLocY() - mainY, null);
            else
                g2d.drawImage(movingSleepImage, movingSmile.getLocX() - mainX, movingSmile.getLocY() - mainY, null);
        }


        AffineTransform backup = g2d.getTransform();
        AffineTransform trans = new AffineTransform();
        trans.rotate(state.getMainTank().getGunAndBodyRadian(), state.getMainTank().getTankCenterX() - mainX, state.getMainTank().getTankCenterY() - mainY); // the points to rotate around (the center in my example, your left side for your problem)

        g2d.transform(trans);
        g2d.drawImage(mainTankGun, state.getMainTank().getGunLocX() - mainX, state.getMainTank().getGunLocY() - mainY, null);  // the actual location of the sprite
        g2d.setTransform(backup); // restore previous transform


        // Draw GAME OVER
        if (state.gameOver) {
            String str = "GAME OVER";
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD).deriveFont(64.0f));
            int strWidth = g2d.getFontMetrics().stringWidth(str);
            g2d.drawString(str, (GAME_WIDTH - strWidth) / 2, GAME_HEIGHT / 2);
        }
    }
}
