package Engine; /*** In The Name of Allah ***/

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

/**
 * Program start.
 * 
 * @author Seyed Mohammad Ghaffarian
 */
public class Main {

	
    public static void main(String[] args) throws LineUnavailableException {
		// Initialize the global thread-pool
        ThreadPool.init();



        //Start start=new Start();
        //ftchdgs





		// After the player clicks 'PLAY' ...
       // if(start.isFlag2()==true) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    GameFrame frame = new GameFrame("Tanki !");
                    frame.setLocationRelativeTo(null); // put frame at center of screen
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.initBufferStrategy();
                    // Create and execute the game-loop
                    GameLoop game = new GameLoop(frame);
                    game.init();
                    ThreadPool.execute(game);
                    // and the game starts ...
                }
            });


    }


}
