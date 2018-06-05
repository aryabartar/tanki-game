public class Tank {

    private int locX;
    private int locY;

    private int endLocX;
    private int endLocY;

    private int gunLocX;
    private int gunLocY;

    private final int xPixels = 128;
    private final int yPixels = 128;
    private final int gunXPixels = 128 ;
    private final int gunYPixels = 40 ;


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
}
