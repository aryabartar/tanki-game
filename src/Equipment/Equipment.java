package Equipment;

public class Equipment {

    private int locX ;
    private int locY ;

    private int centerX ;
    private int centerY ;

    private int endX ;
    private int endY ;

    private boolean isTaken ;

    private final int xPixels  ;
    private final int yPixels  ;

    public Equipment (int locX , int locY , int xPixels , int yPixels ) {
        this.locX = locX ;
        this.locY = locY ;

        endX = locX + xPixels ;
        endY = locY + yPixels ;

        centerX = locX + xPixels/2 ;
        centerY = locY + yPixels/2 ;

        this.xPixels = xPixels;
        this.yPixels = yPixels ;

        isTaken = false ;
    }

    public int getEndY() {
        return endY;
    }

    public int getEndX() {
        return endX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getLocY() {
        return locY;
    }

    public int getLocX() {
        return locX;
    }

    public int getxPixels() {
        return xPixels;
    }

    public int getyPixels() {
        return yPixels;
    }

    public boolean getIsTaken () {
        return isTaken ;
    }

    public void takeEquipment () {
        isTaken = true ;
    }
}
