package Blocks;

public class UnDestroyableBlock extends Block {
    public UnDestroyableBlock(int locX, int locY) {
        super(locX, locY);
    }

    @Override
    public void reduceHealth(int reduce) {
        //Nothing man :D :))
    }
}
