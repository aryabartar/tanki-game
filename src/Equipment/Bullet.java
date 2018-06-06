package Equipment;

import Engine.GameFrame;
import Equipment.Tank;

public class Bullet {

    private int locX;
    private int locY;

    public static final int BULLET_SPEED = 18;
    public static final int DAMAGE = 2;


    private final int pixelX = 39;
    private final int pixelY = 10;
    private final double radian;

    private final boolean isFromEnemy ;

    public Bullet(int locX, int locY, double radian , boolean isFromEnemy) {

        this.locX = locX +
                (int) ((Tank.getGunXPixels()-15) * Math.cos(radian));
        this.locY = locY +
                (int) ((Tank.getGunXPixels()-15) * Math.sin(radian));
        this.radian = radian;
        this.isFromEnemy = isFromEnemy ;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void move() {
        locX += Math.cos(radian) * BULLET_SPEED;
        locY += Math.sin(radian) * BULLET_SPEED;
    }

    public double getRadian() {
        return radian;
    }

    public boolean checkAlive () {
        if ((locX > 0) && (locX < GameFrame.GAME_WIDTH) && (locY > 0) && (locY < GameFrame.GAME_HEIGHT))
            return true ;
        else
            return  false ;
    }

    public boolean isFromEnemy() {
        return isFromEnemy;
    }
}
