package Engine;

import Blocks.Block;
import EnemyTanks.EnemyTank;
import Equipment.Bullet;
import Equipment.*;
import Equipment.Rocket;
import Others.Point;
import Serialization.Serialize;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.Serializable;
import java.util.ArrayList;

public class GameStateSuperInfo implements Serializable {
    public ArrayList<Bullet> bullets;
    public ArrayList<Rocket> rockets;
    public ArrayList<EnemyTank> enemyTanks;
    public ArrayList<Block> blocks;
    public ArrayList<Equipment> equipments;
    public ArrayList<Point> destroyedTankTemporaryTrashPoints;
    public ArrayList<MovingSmile> movingSmiles;

    public GameStateSuperInfo() {
        bullets = new ArrayList<>();
        rockets = new ArrayList<>();
        enemyTanks = new ArrayList<>();
        blocks = new ArrayList<>();
        equipments = new ArrayList<>();
        destroyedTankTemporaryTrashPoints = new ArrayList<>();
        movingSmiles = new ArrayList<>();
    }
}
