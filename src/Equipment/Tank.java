package Equipment;

import EnemyTanks.EnemyTank;
import Engine.GameFrame;
import Engine.GameState;

import java.awt.*;
import java.util.ArrayList;

public class Tank {

    private int locX;
    private int locY;

    private int endLocX;
    private int endLocY;

    private int gunLocX;
    private int gunLocY;

    private final int xPixels = 128;
    private final int yPixels = 128;
    private final static int gunXPixels = 128;
    private final static int gunYPixels = 40;


    private double gunAndBodyRadian; //this is tank body and gun radian .

    public Tank() {
        initLocations();
    }

    /**
     * This method will initialize tank starting location .
     */
    private void initLocations() {
        locX = 100;
        locY = 100;

        setEndingLocation();
        setGunLocation();
    }

    /**
     * This method will set button-right location (tank is 128*128 pixels) .
     */
    private void setEndingLocation() {
        endLocX = locX + xPixels;
        endLocY = locY + yPixels;
    }

    private void setGunLocation() {
        gunLocX = locX + xPixels / 2;
        gunLocY = locY + yPixels / 2 - gunYPixels / 2;

    }

    public int getEndLocX() {
        return endLocX;
    }

    public int getEndLocY() {
        return endLocY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocation(int locX, int locY) {
        ArrayList<EnemyTank> enemyTanks = GameState.getEnemyTanks();
        boolean canMove = true;

        for (EnemyTank enemyTank : enemyTanks) {

            Rectangle r = new Rectangle(locX,locY,xPixels,yPixels);
            Rectangle p = new Rectangle(enemyTank.getLocX(),enemyTank.getLocY(),enemyTank.getGunXPixels()-20,enemyTank.getyPixels());

            // Assuming there is an intersect method
            if (r.intersects(p))
                canMove = false ;

        }

        if (canMove == true) {
            this.locX = locX;
            this.locY = locY;

            endLocX = locX + xPixels;
            endLocY = locY + yPixels;

            setGunLocation();
        }

    }

    private boolean canMove() {
        ArrayList<EnemyTank> enemyTanks = GameState.getEnemyTanks();
        boolean canMove = true;

        for (EnemyTank tank : enemyTanks) {
            if ((locX < tank.getEndLocX()) && (endLocX > tank.getLocX()) && (locY < tank.getEndLocY()) && (endLocY > tank.getLocY())) {

            }
        }
        return canMove;
    }

    public void moveLocX(int moveX) {
        this.setLocation(locX + moveX, locY);
    }

    public void moveLocY(int moveY) {
        this.setLocation(locX, locY + moveY);
    }

    public int getGunLocX() {
        return gunLocX;
    }

    public int getGunLocY() {
        return gunLocY;
    }

    public int getTankCenterX() {
        return locX + xPixels / 2;
    }

    public int getTankCenterY() {
        return locY + yPixels / 2;
    }

    public void setGunAndBodyRadian(double gunAndBodyRadian) {
        this.gunAndBodyRadian = gunAndBodyRadian;
    }

    public double getGunAndBodyRadian() {
        return gunAndBodyRadian;
    }

    public static int getGunXPixels() {
        return gunXPixels;
    }

    public static int getGunYPixels() {
        return gunYPixels;
    }
}
