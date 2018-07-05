package Equipment;

public class MovingSmile {
    private int locX;
    private int locY;

    public static final int xPixels = 60;
    public static final int yPixels = 73;
    public static final int speed = 10;

    public MovingSmile(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getLocY() {
        return locY;
    }

    public void attackToThisLocation(int x, int y) {
        locX++;
        locY++;
    }

}
