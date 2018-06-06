package EnemyTanks;

public class DynamicTankEasy extends EnemyTank {

    private final int fromThisXLocation;
    private final int fromThisYLocation;

    private final int toThisXLocation;
    private final int toThisYLocation;

    private boolean movesDown;
    private boolean movesRight;

    public DynamicTankEasy(int locX, int locY, int toThisXLocation, int toThisYLocation) {
        super(10, locX, locY, 4);

        this.fromThisXLocation = locX;
        this.fromThisYLocation = locY;

        this.toThisXLocation = toThisXLocation;
        this.toThisYLocation = toThisYLocation;

        movesDown = true;
        movesRight = true;

        moveAutomatic();
    }

    public void moveAutomatic() {
        if (((fromThisXLocation == toThisXLocation) || (fromThisYLocation == toThisYLocation)) &&
                (fromThisYLocation <= toThisYLocation) && (fromThisXLocation <= toThisXLocation)) {

            if (fromThisXLocation == toThisXLocation) {
                if (movesDown == true) {
                    setLocation(locX, locY + 2, this);
                } else {
                    setLocation(locX, locY - 2, this);
                }
                if (locY > toThisYLocation)
                    movesDown = false;

                else if (locY < fromThisYLocation)
                    movesDown = true;

            } else {

                if (movesRight == true) {
                    setLocation(locX + 2, locY, this);
                } else {
                    setLocation(locX - 2, locY, this);
                }
                if (locX > toThisXLocation)
                    movesRight = false;

                else if (locX < fromThisXLocation)
                    movesRight = true;
            }
        } else {
            System.err.println("You'r inputs should have at least one same position in either x or y . :D");
        }
    }

}
