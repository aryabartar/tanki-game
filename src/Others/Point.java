package Others;

public class Point {
    private int X ;
    private  int Y ;
    private int timeToRemove ; // this field is used to save time to remove sth from this point

    public Point (int X , int Y) {
        this.X = X ;
        this.Y = Y ;

        timeToRemove = 100 ;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getTimeToRemove() {
        return timeToRemove;
    }

    public void reduceTimeToRemove (int thisValue) {
        timeToRemove -= thisValue ;
    }
}
