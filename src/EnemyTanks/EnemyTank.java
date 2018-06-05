package EnemyTanks;

import Engine.GameFrame;

public abstract class EnemyTank {

    protected int health ;
    protected boolean isAlive ;

    protected int locX;
    protected int locY;

    protected int endLocX;
    protected int endLocY;

    protected int gunLocX;
    protected int gunLocY;

    protected final int xPixels = 100;
    protected final int yPixels = 100;
    protected final int gunXPixels = 128 ;
    protected final int gunYPixels = 40 ;

    protected double gunAndBodyRadian ; //this is tank body and gun radian .

    public EnemyTank (int health , int locX , int locY) {
        this.health = health ;
        this.locX = locX ;
        this.locY = locY ;

        isAlive = true ;

        initLocations();
    }


    /**
     * This method will initialize tank starting location .
     */
    private void initLocations() {
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
        gunLocX = locX + xPixels / 2 ;
        gunLocY = locY + yPixels / 2 - gunYPixels/2;

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

    public void setLocX(int locX) {
        if ((locX >= 0) && ((locX + xPixels) <= GameFrame.GAME_WIDTH)) {
            this.locX = locX;
            endLocX = locX + xPixels;
            setGunLocation();
        }
    }

    public void setLocY(int locY) {
        if ((locY >= 0) && ((locY + yPixels) <= GameFrame.GAME_HEIGHT)) {
            this.locY = locY;
            endLocY = locY + yPixels;
            setGunLocation();
        }
    }

    public void moveLocX(int moveX) {
        this.setLocX(locX + moveX);
    }

    public void moveLocY(int moveY) {
        this.setLocY(locY + moveY);
    }

    public int getGunLocX() {
        return gunLocX;
    }

    public int getGunLocY() {
        return gunLocY;
    }

    public int getTankCenterX () {
        return locX + xPixels/2 ;
    }

    public int getTankCenterY () {
        return locY + yPixels/2 ;
    }

    public void setGunAndBodyRadian(double gunAndBodyRadian) {
        this.gunAndBodyRadian = gunAndBodyRadian;
    }

    public double getGunAndBodyRadian() {
        return gunAndBodyRadian;
    }

    public int getGunXPixels() {
        return gunXPixels;
    }

    public int getGunYPixels() {
        return gunYPixels;
    }

    public int getxPixels() {
        return xPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int reduce) {
        health -= reduce ;
        System.out.println("Reduce : " + reduce);
    }
}
