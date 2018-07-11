package EnemyTanks;

import Engine.GameState;

public class StaticTankHard extends EnemyTank{

    public StaticTankHard(int locX , int locY) {
        super(18 * GameState.difficultyLevel, locX , locY , 1);
    }

}
