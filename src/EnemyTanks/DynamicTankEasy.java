package EnemyTanks;

public class DynamicTankEasy extends EnemyTank {

    private final int fromThisXLocation ;
    private final int fromThisYLocation ;

    private final int toThisXLocation ;
    private final int toThisYLocation ;
    public DynamicTankEasy(int locX , int locY , int toThisXLocation , int toThisYLocation) {
        super(10 , locX , locY , 4);

        this.fromThisXLocation = locX ;
        this.fromThisYLocation = locY ;

        this.toThisXLocation = toThisXLocation ;
        this.toThisYLocation = toThisYLocation ;
    }

    public void moveAutomaticHorizontally () {
        setLocation(locX +1 , locY, this);
        System.out.println(locX + " " + locY);
    }

}
