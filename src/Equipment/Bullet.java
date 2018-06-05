package Equipment;

import Equipment.Tank;

public class Bullet {

    private int locX;
    private int locY;

    public static final int BULLET_SPEED = 18;

    private final int pixelX = 39;
    private final int pixelY = 10;
    private final double radian;

    public Bullet(int locX, int locY, double radian) {

        this.locX = locX +
                (int) ((Tank.getGunXPixels()-15) * Math.cos(radian));
        this.locY = locY +
                (int) ((Tank.getGunXPixels()-15) * Math.sin(radian));
        this.radian = radian;
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
}
