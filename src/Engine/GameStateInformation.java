package Engine;

import Blocks.Block;
import Blocks.Chariot;
import Blocks.DestroyableBlock;
import Blocks.UnDestroyableBlock;
import EnemyTanks.DynamicTankEasy;
import EnemyTanks.EnemyTank;
import EnemyTanks.StaticTankEasy;
import EnemyTanks.StaticTankHard;
import Equipment.* ;
import Others.Point;

import java.util.ArrayList;

public class GameStateInformation {
    private static ArrayList<Bullet> bullets;
    private static ArrayList<Rocket> rockets;
    private static ArrayList<EnemyTank> enemyTanks;
    private static ArrayList<Block> blocks;
    private static ArrayList<Equipment> equipments;
    private static ArrayList<Point> destroyedTankTemporaryTrashPoints;
    private static ArrayList<MovingSmile> movingSmiles;
    private Tank mainTank ;

    public GameStateInformation () {
        mainTank = new Tank() ;

        setArrayLists();
        addMapObjects();
    }


    private void addMapObjects() {

        //add tanks here
        enemyTanks.add(new StaticTankEasy(600, 100));
        enemyTanks.add(new DynamicTankEasy(300, 500, 500, 500));
        enemyTanks.add(new DynamicTankEasy(1500, 1300, 1800, 1300));
        enemyTanks.add(new StaticTankHard(1500, 1100));
        enemyTanks.add(new StaticTankEasy(1300, 1200));
        enemyTanks.add(new StaticTankHard(1200, 200));
        enemyTanks.add(new StaticTankHard(700, 1200));
        enemyTanks.add(new StaticTankHard(2600, 1300));

        enemyTanks.add(new StaticTankHard(1850, 800));
        enemyTanks.add(new DynamicTankEasy(2600, 130, 2600, 250));


        // add blocks here
        blocks.add(new UnDestroyableBlock(400, 100));
        blocks.add(new UnDestroyableBlock(400, 200));
        blocks.add(new UnDestroyableBlock(400, 300));

        blocks.add(new DestroyableBlock(100, 300));
        blocks.add(new DestroyableBlock(200, 300));
        blocks.add(new DestroyableBlock(300, 300));
        blocks.add(new DestroyableBlock(500, 100));

        blocks.add(new UnDestroyableBlock(400, 900));
        blocks.add(new UnDestroyableBlock(500, 900));
        blocks.add(new UnDestroyableBlock(600, 900));
        blocks.add(new UnDestroyableBlock(400, 1000));
        blocks.add(new UnDestroyableBlock(400, 1100));
        blocks.add(new UnDestroyableBlock(500, 1100));
        blocks.add(new UnDestroyableBlock(600, 1100));
        blocks.add(new UnDestroyableBlock(600, 1000));

        blocks.add(new UnDestroyableBlock(100, 900));
        blocks.add(new UnDestroyableBlock(100, 1000));
        blocks.add(new DestroyableBlock(200, 900));
        blocks.add(new DestroyableBlock(300, 900));

        blocks.add(new UnDestroyableBlock(600, 1200));
        blocks.add(new UnDestroyableBlock(600, 1300));
        blocks.add(new UnDestroyableBlock(600, 1400));
        blocks.add(new UnDestroyableBlock(600, 1500));

        blocks.add(new DestroyableBlock(600, 1600));
        blocks.add(new DestroyableBlock(600, 1700));
        blocks.add(new DestroyableBlock(600, 1800));

        blocks.add(new UnDestroyableBlock(700, 1300));
        blocks.add(new UnDestroyableBlock(700, 1400));
        blocks.add(new UnDestroyableBlock(700, 1500));
        blocks.add(new UnDestroyableBlock(800, 1300));
        blocks.add(new UnDestroyableBlock(800, 1400));
        blocks.add(new UnDestroyableBlock(800, 1500));
        blocks.add(new UnDestroyableBlock(900, 1300));
        blocks.add(new UnDestroyableBlock(900, 1400));
        blocks.add(new UnDestroyableBlock(900, 1500));
        blocks.add(new UnDestroyableBlock(1000, 1300));
        blocks.add(new UnDestroyableBlock(1000, 1400));
        blocks.add(new UnDestroyableBlock(1000, 1500));
        blocks.add(new UnDestroyableBlock(1100, 1300));
        blocks.add(new UnDestroyableBlock(1100, 1400));
        blocks.add(new UnDestroyableBlock(1100, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1300));
        blocks.add(new UnDestroyableBlock(1200, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1300));
        blocks.add(new UnDestroyableBlock(1300, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1500));
        blocks.add(new UnDestroyableBlock(1400, 1400));

        blocks.add(new DestroyableBlock(1400, 1500));
        blocks.add(new DestroyableBlock(1400, 1300));

        blocks.add(new UnDestroyableBlock(1400, 1200));
        blocks.add(new UnDestroyableBlock(1400, 1100));
        blocks.add(new UnDestroyableBlock(1400, 1000));
        blocks.add(new UnDestroyableBlock(1500, 1000));
        blocks.add(new DestroyableBlock(1600, 1000));
        blocks.add(new DestroyableBlock(1700, 1000));
        blocks.add(new UnDestroyableBlock(1800, 1000));
        blocks.add(new UnDestroyableBlock(1900, 1000));
        blocks.add(new UnDestroyableBlock(2000, 1000));
        blocks.add(new UnDestroyableBlock(2100, 1000));

        blocks.add(new UnDestroyableBlock(1400, 900));
        blocks.add(new UnDestroyableBlock(1500, 900));
        blocks.add(new DestroyableBlock(1600, 900));
        blocks.add(new DestroyableBlock(1700, 900));
        blocks.add(new UnDestroyableBlock(1800, 900));
        blocks.add(new UnDestroyableBlock(1900, 900));
        blocks.add(new UnDestroyableBlock(2000, 900));
        blocks.add(new UnDestroyableBlock(2100, 900));

        blocks.add(new DestroyableBlock(1300, 1000));
        blocks.add(new DestroyableBlock(1200, 1000));
        blocks.add(new DestroyableBlock(1100, 1000));
        blocks.add(new DestroyableBlock(1000, 1000));
        blocks.add(new DestroyableBlock(900, 1000));
        blocks.add(new DestroyableBlock(800, 1000));
        blocks.add(new DestroyableBlock(700, 1000));
        blocks.add(new DestroyableBlock(700, 900));
        blocks.add(new DestroyableBlock(800, 900));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(1100, 900));
        blocks.add(new DestroyableBlock(1200, 900));
        blocks.add(new DestroyableBlock(1300, 900));

        blocks.add(new Chariot(900, 100));
        blocks.add(new DestroyableBlock(900, 600));
        blocks.add(new DestroyableBlock(1000, 600));
        blocks.add(new DestroyableBlock(900, 700));
        blocks.add(new DestroyableBlock(1000, 700));
        blocks.add(new DestroyableBlock(900, 800));
        blocks.add(new DestroyableBlock(1000, 800));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(900, 500));
        blocks.add(new DestroyableBlock(1000, 500));

        for (int i = 4; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(1600, i * 100));
            blocks.add(new UnDestroyableBlock(1700, i * 100));
            blocks.add(new UnDestroyableBlock(2000, i * 100));
            blocks.add(new UnDestroyableBlock(2100, i * 100));
        }

        blocks.add(new DestroyableBlock(1800, 400));
        blocks.add(new DestroyableBlock(1900, 400));


        blocks.add(new UnDestroyableBlock(2400, 400));
        blocks.add(new UnDestroyableBlock(2500, 400));
        blocks.add(new UnDestroyableBlock(2600, 400));
        blocks.add(new UnDestroyableBlock(2600, 500));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new UnDestroyableBlock(2400, 500));
        blocks.add(new UnDestroyableBlock(2400, 600));
        blocks.add(new UnDestroyableBlock(2500, 600));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new DestroyableBlock(2500, 500));

        blocks.add(new UnDestroyableBlock(2600, 300));
        blocks.add(new UnDestroyableBlock(2600, 200));
        blocks.add(new UnDestroyableBlock(2600, 100));
        blocks.add(new DestroyableBlock(2400, 100));
        blocks.add(new DestroyableBlock(2400, 200));
        blocks.add(new DestroyableBlock(2400, 300));

        blocks.add(new UnDestroyableBlock(2200, 1000));
        blocks.add(new UnDestroyableBlock(2300, 1000));
        blocks.add(new UnDestroyableBlock(2400, 1000));
        blocks.add(new UnDestroyableBlock(2500, 1000));
        blocks.add(new UnDestroyableBlock(2900, 1000));
        blocks.add(new UnDestroyableBlock(2600, 1000));
        blocks.add(new UnDestroyableBlock(2700, 1000));
        blocks.add(new UnDestroyableBlock(2800, 1000));
        blocks.add(new UnDestroyableBlock(2500, 1200));
        blocks.add(new UnDestroyableBlock(2500, 1300));
        blocks.add(new UnDestroyableBlock(2500, 1100));

        blocks.add(new DestroyableBlock(2900, 900));
        blocks.add(new DestroyableBlock(2900, 600));
        blocks.add(new DestroyableBlock(2900, 500));
        blocks.add(new DestroyableBlock(2900, 400));
        blocks.add(new DestroyableBlock(2900, 300));
        blocks.add(new DestroyableBlock(2900, 200));
        blocks.add(new DestroyableBlock(2900, 100));

        blocks.add(new Chariot(3100, 200));
        blocks.add(new DestroyableBlock(3100, 700));
        blocks.add(new DestroyableBlock(3100, 800));
        blocks.add(new UnDestroyableBlock(3100, 900));
        blocks.add(new UnDestroyableBlock(3100, 1000));
        blocks.add(new UnDestroyableBlock(3100, 1100));
        blocks.add(new DestroyableBlock(3100, 1200));
        blocks.add(new DestroyableBlock(3100, 1300));
        blocks.add(new DestroyableBlock(3100, 1500));
        blocks.add(new DestroyableBlock(3100, 1600));
        blocks.add(new UnDestroyableBlock(3400, 200));
        blocks.add(new UnDestroyableBlock(3400, 300));
        blocks.add(new UnDestroyableBlock(3400, 400));
        blocks.add(new UnDestroyableBlock(3400, 500));
        blocks.add(new UnDestroyableBlock(3400, 600));
        blocks.add(new UnDestroyableBlock(3400, 700));
        blocks.add(new UnDestroyableBlock(3400, 800));
        blocks.add(new UnDestroyableBlock(3400, 900));
        blocks.add(new UnDestroyableBlock(3400, 1000));
        blocks.add(new UnDestroyableBlock(3400, 1100));
        blocks.add(new UnDestroyableBlock(3400, 1200));
        blocks.add(new UnDestroyableBlock(3400, 1300));
        blocks.add(new UnDestroyableBlock(3400, 1400));
        blocks.add(new UnDestroyableBlock(3300, 1400));
        blocks.add(new UnDestroyableBlock(3200, 1400));
        blocks.add(new UnDestroyableBlock(3100, 1400));
        blocks.add(new UnDestroyableBlock(3000, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1500));
        blocks.add(new UnDestroyableBlock(2900, 1600));
        blocks.add(new UnDestroyableBlock(2800, 1600));
        blocks.add(new UnDestroyableBlock(2700, 1600));
        blocks.add(new UnDestroyableBlock(2600, 1600));
        blocks.add(new UnDestroyableBlock(2500, 1600));
        blocks.add(new UnDestroyableBlock(2400, 1600));
        blocks.add(new UnDestroyableBlock(2300, 1600));
        blocks.add(new DestroyableBlock(2200, 1600));
        blocks.add(new UnDestroyableBlock(2100, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1500));
        blocks.add(new UnDestroyableBlock(2000, 1400));
        blocks.add(new UnDestroyableBlock(2000, 1300));
        blocks.add(new DestroyableBlock(3400, 1500));
        blocks.add(new DestroyableBlock(3400, 1600));
        blocks.add(new DestroyableBlock(3400, 1700));
        blocks.add(new DestroyableBlock(3400, 1800));
        blocks.add(new DestroyableBlock(3400, 1900));


        for (int i = 0; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(3700, 1000 + i * 100));
        }
        enemyTanks.add(new DynamicTankEasy(3700, 500, 3700, 900));
        blocks.add(new DestroyableBlock(3700, 400));
        blocks.add(new DestroyableBlock(3700, 300));
        blocks.add(new DestroyableBlock(3700, 200));
        blocks.add(new DestroyableBlock(3700, 100));


        blocks.add(new DestroyableBlock(3800, 400));
        blocks.add(new DestroyableBlock(3900, 400));
        blocks.add(new DestroyableBlock(4000, 400));
        blocks.add(new DestroyableBlock(4100, 400));

        blocks.add(new UnDestroyableBlock(4100, 300));
        blocks.add(new UnDestroyableBlock(4100, 200));
        blocks.add(new UnDestroyableBlock(4100, 100));

        blocks.add(new UnDestroyableBlock(4100, 400));
        blocks.add(new UnDestroyableBlock(4100, 500));
        blocks.add(new UnDestroyableBlock(4100, 600));
        blocks.add(new UnDestroyableBlock(4100, 700));
        blocks.add(new UnDestroyableBlock(4100, 800));
        blocks.add(new UnDestroyableBlock(4100, 900));
        blocks.add(new UnDestroyableBlock(4100, 1000));
        blocks.add(new UnDestroyableBlock(4100, 1100));
        blocks.add(new UnDestroyableBlock(4100, 1200));
        blocks.add(new UnDestroyableBlock(4100, 1300));
        blocks.add(new UnDestroyableBlock(4100, 1400));
        blocks.add(new UnDestroyableBlock(4100, 1500));


        blocks.add(new UnDestroyableBlock(4200, 1500));
        blocks.add(new UnDestroyableBlock(4300, 1500));
        blocks.add(new UnDestroyableBlock(4400, 1500));

        enemyTanks.add(new DynamicTankEasy(120, 1800, 350, 1800));
        enemyTanks.add(new StaticTankEasy(3000, 100));
        enemyTanks.add(new StaticTankEasy(4000, 100));
        enemyTanks.add(new StaticTankEasy(4400, 1100));


        equipments.add(new UpdateWeapon(300, 1000));
        equipments.add(new UpdateWeapon(900, 1200));
        equipments.add(new UpdateWeapon(2500, 100));


        equipments.add(new Repair(3800, 100));
        equipments.add(new Repair(2400, 1800));


        //Add moving smiles here :D
        movingSmiles.add(new MovingSmile(500, 1000));
        movingSmiles.add(new MovingSmile(1100, 800));
        movingSmiles.add(new MovingSmile(2400, 1700));
        movingSmiles.add(new MovingSmile(4400, 500));
        movingSmiles.add(new MovingSmile(4800, 500));

        setBorders();

    }


    public void setLevelTwoMap() {
        setArrayLists();
        mainTank = new Tank() ;
        GameState.firstLevel = false ;

        enemyTanks.add(new StaticTankEasy(600, 100));
        enemyTanks.add(new DynamicTankEasy(300, 500, 500, 500));
        enemyTanks.add(new DynamicTankEasy(1600, 1300, 2200, 1300));

        enemyTanks.add(new StaticTankHard(1500, 1100));

        enemyTanks.add(new StaticTankEasy(1300, 1200));
        enemyTanks.add(new StaticTankHard(1200, 200));

        enemyTanks.add(new StaticTankHard(800, 1300));
        enemyTanks.add(new StaticTankHard(2600, 1300));

        enemyTanks.add(new StaticTankHard(1850, 800));
        enemyTanks.add(new DynamicTankEasy(2600, 130, 2600, 250));


        // add blocks here
        blocks.add(new UnDestroyableBlock(400, 100));
        blocks.add(new UnDestroyableBlock(400, 200));
        blocks.add(new UnDestroyableBlock(400, 300));

        blocks.add(new DestroyableBlock(100, 300));
        blocks.add(new DestroyableBlock(200, 300));
        blocks.add(new DestroyableBlock(300, 300));
        blocks.add(new DestroyableBlock(500, 100));

        blocks.add(new UnDestroyableBlock(400, 900));
        blocks.add(new UnDestroyableBlock(500, 900));
        blocks.add(new UnDestroyableBlock(600, 900));
        blocks.add(new UnDestroyableBlock(400, 1000));
        blocks.add(new UnDestroyableBlock(400, 1100));
        blocks.add(new UnDestroyableBlock(500, 1100));
        blocks.add(new UnDestroyableBlock(600, 1100));
        blocks.add(new UnDestroyableBlock(600, 1000));

        blocks.add(new UnDestroyableBlock(100, 900));
        blocks.add(new UnDestroyableBlock(100, 1000));
        blocks.add(new DestroyableBlock(200, 900));
        blocks.add(new DestroyableBlock(300, 900));

        blocks.add(new UnDestroyableBlock(600, 1200));
        blocks.add(new UnDestroyableBlock(600, 1300));
        blocks.add(new UnDestroyableBlock(600, 1400));
        blocks.add(new UnDestroyableBlock(600, 1500));

        blocks.add(new DestroyableBlock(600, 1600));
        blocks.add(new DestroyableBlock(600, 1700));
        blocks.add(new DestroyableBlock(600, 1800));

        blocks.add(new UnDestroyableBlock(700, 1300));
        blocks.add(new UnDestroyableBlock(700, 1400));
        blocks.add(new UnDestroyableBlock(700, 1500));
        blocks.add(new UnDestroyableBlock(800, 1300));
        blocks.add(new UnDestroyableBlock(800, 1400));
        blocks.add(new UnDestroyableBlock(800, 1500));
        blocks.add(new UnDestroyableBlock(900, 1300));
        blocks.add(new UnDestroyableBlock(900, 1400));
        blocks.add(new UnDestroyableBlock(900, 1500));
        blocks.add(new UnDestroyableBlock(1000, 1300));
        blocks.add(new UnDestroyableBlock(1000, 1400));
        blocks.add(new UnDestroyableBlock(1000, 1500));
        blocks.add(new UnDestroyableBlock(1100, 1300));
        blocks.add(new UnDestroyableBlock(1100, 1400));
        blocks.add(new UnDestroyableBlock(1100, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1500));
        blocks.add(new UnDestroyableBlock(1200, 1300));
        blocks.add(new UnDestroyableBlock(1200, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1300));
        blocks.add(new UnDestroyableBlock(1300, 1400));
        blocks.add(new UnDestroyableBlock(1300, 1500));
        blocks.add(new UnDestroyableBlock(1400, 1400));

        blocks.add(new DestroyableBlock(1400, 1500));
        blocks.add(new DestroyableBlock(1400, 1300));

        blocks.add(new UnDestroyableBlock(1400, 1200));
        blocks.add(new UnDestroyableBlock(1400, 1100));
        blocks.add(new UnDestroyableBlock(1400, 1000));
        blocks.add(new UnDestroyableBlock(1500, 1000));
        blocks.add(new DestroyableBlock(1600, 1000));
        blocks.add(new DestroyableBlock(1700, 1000));
        blocks.add(new UnDestroyableBlock(1800, 1000));
        blocks.add(new UnDestroyableBlock(1900, 1000));
        blocks.add(new UnDestroyableBlock(2000, 1000));
        blocks.add(new UnDestroyableBlock(2100, 1000));

        blocks.add(new UnDestroyableBlock(1400, 900));
        blocks.add(new UnDestroyableBlock(1500, 900));
        blocks.add(new DestroyableBlock(1600, 900));
        blocks.add(new DestroyableBlock(1700, 900));
        blocks.add(new UnDestroyableBlock(1800, 900));
        blocks.add(new UnDestroyableBlock(1900, 900));
        blocks.add(new UnDestroyableBlock(2000, 900));
        blocks.add(new UnDestroyableBlock(2100, 900));

        blocks.add(new DestroyableBlock(1300, 1000));
        blocks.add(new DestroyableBlock(1200, 1000));
        blocks.add(new DestroyableBlock(1100, 1000));
        blocks.add(new DestroyableBlock(1000, 1000));
        blocks.add(new DestroyableBlock(900, 1000));
        blocks.add(new DestroyableBlock(800, 1000));
        blocks.add(new DestroyableBlock(700, 1000));
        blocks.add(new DestroyableBlock(700, 900));
        blocks.add(new DestroyableBlock(800, 900));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(1100, 900));
        blocks.add(new DestroyableBlock(1200, 900));
        blocks.add(new DestroyableBlock(1300, 900));

        blocks.add(new Chariot(900, 100));
        blocks.add(new DestroyableBlock(900, 600));
        blocks.add(new DestroyableBlock(1000, 600));
        blocks.add(new DestroyableBlock(900, 700));
        blocks.add(new DestroyableBlock(1000, 700));
        blocks.add(new DestroyableBlock(900, 800));
        blocks.add(new DestroyableBlock(1000, 800));
        blocks.add(new DestroyableBlock(900, 900));
        blocks.add(new DestroyableBlock(1000, 900));
        blocks.add(new DestroyableBlock(900, 500));
        blocks.add(new DestroyableBlock(1000, 500));

        for (int i = 1; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(1600, i * 100));
            blocks.add(new UnDestroyableBlock(1700, i * 100));
            blocks.add(new UnDestroyableBlock(2000, i * 100));
            blocks.add(new UnDestroyableBlock(2100, i * 100));
        }
        blocks.add(new DestroyableBlock(1800, 400));
        blocks.add(new DestroyableBlock(1900, 400));


        blocks.add(new UnDestroyableBlock(2400, 400));
        blocks.add(new UnDestroyableBlock(2500, 400));
        blocks.add(new UnDestroyableBlock(2600, 400));
        blocks.add(new UnDestroyableBlock(2600, 500));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new UnDestroyableBlock(2400, 500));
        blocks.add(new UnDestroyableBlock(2400, 600));
        blocks.add(new UnDestroyableBlock(2500, 600));
        blocks.add(new UnDestroyableBlock(2600, 600));
        blocks.add(new DestroyableBlock(2500, 500));

        blocks.add(new UnDestroyableBlock(2600, 300));
        blocks.add(new UnDestroyableBlock(2600, 200));
        blocks.add(new UnDestroyableBlock(2600, 100));
        blocks.add(new DestroyableBlock(2400, 100));
        blocks.add(new DestroyableBlock(2400, 200));
        blocks.add(new DestroyableBlock(2400, 300));

        blocks.add(new UnDestroyableBlock(2200, 1000));
        blocks.add(new UnDestroyableBlock(2300, 1000));
        blocks.add(new UnDestroyableBlock(2400, 1000));
        blocks.add(new UnDestroyableBlock(2500, 1000));
        blocks.add(new UnDestroyableBlock(2900, 1000));
        blocks.add(new UnDestroyableBlock(2600, 1000));
        blocks.add(new UnDestroyableBlock(2700, 1000));
        blocks.add(new UnDestroyableBlock(2800, 1000));
        blocks.add(new DestroyableBlock(2500, 1200));
        blocks.add(new DestroyableBlock(2500, 1300));
        blocks.add(new DestroyableBlock(2500, 1100));

        blocks.add(new DestroyableBlock(2900, 900));
        blocks.add(new DestroyableBlock(2900, 600));
        blocks.add(new DestroyableBlock(2900, 500));
        blocks.add(new DestroyableBlock(2900, 400));
        blocks.add(new DestroyableBlock(2900, 300));
        blocks.add(new DestroyableBlock(2900, 200));
        blocks.add(new DestroyableBlock(2900, 100));

        blocks.add(new Chariot(3100, 200));
        blocks.add(new DestroyableBlock(3100, 700));
        blocks.add(new DestroyableBlock(3100, 800));
        blocks.add(new UnDestroyableBlock(3100, 900));
        blocks.add(new UnDestroyableBlock(3100, 1000));
        blocks.add(new UnDestroyableBlock(3100, 1100));
        blocks.add(new DestroyableBlock(3100, 1200));
        blocks.add(new DestroyableBlock(3100, 1300));
        blocks.add(new DestroyableBlock(3100, 1500));
        blocks.add(new DestroyableBlock(3100, 1600));
        blocks.add(new UnDestroyableBlock(3400, 200));
        blocks.add(new UnDestroyableBlock(3400, 300));
        blocks.add(new UnDestroyableBlock(3400, 400));
        blocks.add(new UnDestroyableBlock(3400, 500));
        blocks.add(new UnDestroyableBlock(3400, 600));
        blocks.add(new UnDestroyableBlock(3400, 700));
        blocks.add(new UnDestroyableBlock(3400, 800));
        blocks.add(new UnDestroyableBlock(3400, 900));
        blocks.add(new UnDestroyableBlock(3400, 1000));
        blocks.add(new UnDestroyableBlock(3400, 1100));
        blocks.add(new UnDestroyableBlock(3400, 1200));
        blocks.add(new UnDestroyableBlock(3400, 1300));
        blocks.add(new UnDestroyableBlock(3400, 1400));
        blocks.add(new UnDestroyableBlock(3300, 1400));
        blocks.add(new UnDestroyableBlock(3200, 1400));
        blocks.add(new UnDestroyableBlock(3100, 1400));
        blocks.add(new UnDestroyableBlock(3000, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1400));
        blocks.add(new UnDestroyableBlock(2900, 1500));
        blocks.add(new UnDestroyableBlock(2900, 1600));
        blocks.add(new UnDestroyableBlock(2800, 1600));
        blocks.add(new UnDestroyableBlock(2700, 1600));
        blocks.add(new UnDestroyableBlock(2600, 1600));
        blocks.add(new UnDestroyableBlock(2500, 1600));
        blocks.add(new UnDestroyableBlock(2400, 1600));
        blocks.add(new UnDestroyableBlock(2300, 1600));
        blocks.add(new DestroyableBlock(2200, 1600));
        blocks.add(new UnDestroyableBlock(2100, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1600));
        blocks.add(new UnDestroyableBlock(2000, 1500));
        blocks.add(new UnDestroyableBlock(2000, 1400));
        blocks.add(new UnDestroyableBlock(2000, 1300));


        blocks.add(new DestroyableBlock(3400, 1500));
        blocks.add(new DestroyableBlock(3400, 1600));
        blocks.add(new DestroyableBlock(3400, 1700));
        blocks.add(new DestroyableBlock(3400, 1800));
        blocks.add(new DestroyableBlock(3400, 1900));


        for (int i = 0; i < 10; i++) {
            blocks.add(new UnDestroyableBlock(3700, 1000 + i * 100));
        }
        enemyTanks.add(new DynamicTankEasy(3700, 500, 3700, 900));
        enemyTanks.add(new DynamicTankEasy(4900, 1500, 4900, 1900));
        blocks.add(new DestroyableBlock(3700, 400));
        blocks.add(new DestroyableBlock(3700, 300));
        blocks.add(new DestroyableBlock(3700, 200));
        blocks.add(new DestroyableBlock(3700, 100));


        blocks.add(new DestroyableBlock(3800, 400));
        blocks.add(new DestroyableBlock(3900, 400));
        blocks.add(new DestroyableBlock(4000, 400));
        blocks.add(new DestroyableBlock(4100, 400));

        blocks.add(new UnDestroyableBlock(4100, 300));
        blocks.add(new UnDestroyableBlock(4100, 200));
        blocks.add(new UnDestroyableBlock(4100, 100));

        blocks.add(new UnDestroyableBlock(4100, 400));
        blocks.add(new UnDestroyableBlock(4100, 500));
        blocks.add(new UnDestroyableBlock(4100, 600));
        blocks.add(new UnDestroyableBlock(4100, 700));
        blocks.add(new DestroyableBlock(4100, 800));
        blocks.add(new UnDestroyableBlock(4100, 900));
        blocks.add(new UnDestroyableBlock(4100, 1000));
        blocks.add(new UnDestroyableBlock(4100, 1100));
        blocks.add(new UnDestroyableBlock(4100, 1200));
        blocks.add(new UnDestroyableBlock(4100, 1300));
        blocks.add(new DestroyableBlock(4100, 1400));
        blocks.add(new UnDestroyableBlock(4100, 1500));


        blocks.add(new UnDestroyableBlock(4200, 1500));
        blocks.add(new UnDestroyableBlock(4300, 1500));
        blocks.add(new UnDestroyableBlock(4400, 1500));
        blocks.add(new UnDestroyableBlock(4400, 1600));
        blocks.add(new DestroyableBlock(4400, 1700));


        enemyTanks.add(new DynamicTankEasy(300, 1800, 800, 1800));


        enemyTanks.add(new StaticTankEasy(3000, 100));
        enemyTanks.add(new StaticTankEasy(4000, 100));
        enemyTanks.add(new StaticTankEasy(4400, 1100));
        enemyTanks.add(new StaticTankEasy(3400, 1100));
        enemyTanks.add(new StaticTankEasy(4400, 1100));
        enemyTanks.add(new StaticTankEasy(4400, 1100));


        //equipments.add(new UpdateWeapon(300, 1000));
        equipments.add(new UpdateWeapon(700, 1200));
        equipments.add(new UpdateWeapon(2500, 100));


        // equipments.add(new Repair(3800, 100));
        equipments.add(new Repair(2400, 1800));


        //Add moving smiles here :D
        movingSmiles.add(new MovingSmile(500, 1000));
        movingSmiles.add(new MovingSmile(1100, 800));
        movingSmiles.add(new MovingSmile(2400, 1700));
        movingSmiles.add(new MovingSmile(4400, 500));
        movingSmiles.add(new MovingSmile(4800, 500));
        movingSmiles.add(new MovingSmile(4400, 500));
        movingSmiles.add(new MovingSmile(1800, 500));
        movingSmiles.add(new MovingSmile(3800, 500));
        movingSmiles.add(new MovingSmile(800, 500));
        movingSmiles.add(new MovingSmile(2000, 500));


        setBorders();

    }

    private void setBorders() {

        for (int i = 0; i <= 49; i++) {
            blocks.add(new UnDestroyableBlock(i * 100, 0));
            blocks.add(new UnDestroyableBlock(i * 100, 1900));

            if (i < 20) {
                blocks.add(new UnDestroyableBlock(0, i * 100));
                blocks.add(new UnDestroyableBlock(4900, i * 100));
            }
        }

    }

    private void setArrayLists() {
        bullets = new ArrayList<>();
        rockets = new ArrayList<>();
        enemyTanks = new ArrayList<>();
        blocks = new ArrayList<>();
        equipments = new ArrayList<>();
        destroyedTankTemporaryTrashPoints = new ArrayList<>();
        movingSmiles = new ArrayList<>();
    }

    public static ArrayList<MovingSmile> getMovingSmiles() {
        return movingSmiles;
    }

    public static ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    public static ArrayList<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static ArrayList<Rocket> getRockets() {
        return rockets;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<Point> getDestroyedTankTemporaryTrashPoints() {
        return destroyedTankTemporaryTrashPoints;
    }

    public Tank getMainTank() {
        return mainTank;
    }
}
