package Engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Start extends MouseAdapter {
    private JFrame frame;
    private JLabel label;
    private Clip clip;
    private JLabel label2;
    private int mouseX;
    private int mouseY;

    private boolean flag = false;
    private boolean flag2 = false;


    public Start() {

        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        JLabel label2 = new JLabel();


        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("./sound/start.wav").getAbsoluteFile());
            clip.open(audioInputStream);
            clip.start();
            clip.loop(2);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1363, 770);
        label.setSize(1363, 770);
        label2.setSize(1363, 770);
        label2.addMouseListener(this);
        label.addMouseListener(this);

        Icon icon_page1=new ImageIcon(getClass().getResource("page1.jpg"));
        Icon icon_page2 = new ImageIcon(getClass().getResource("page2.jpg"));

        label.setIcon(icon_page1);
        label2.setIcon(icon_page2);

        frame.add(label);
        frame.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {

        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println(mouseX);
        System.out.println(mouseY);
        //click on page2
        if (mouseX < 239 && mouseX > 129 && mouseY < 322 && mouseY > 257 && flag) {
            System.out.println("Easy");
            flag2 = true;
            clip.stop();
            frame.setVisible(false);


        }
        if (mouseX < 255 && mouseX > 113 && mouseY < 442 && mouseY > 360 && flag == true) {
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
            System.out.println("Normal");
            flag2 = true;

            clip.stop();
            frame.setVisible(false);


        }
        if (mouseX < 231 && mouseX > 130 && mouseY < 579 && mouseY > 514 && flag == true) {
            System.out.println("Hard");
            flag2 = true;
            clip.stop();
            frame.setVisible(false);

        }
        //click on page1
        if (mouseX < 255 && mouseX > 113 && mouseY < 442 && mouseY > 360 && flag == false) {
            System.out.println("Co-op");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();
            flag = true;

        }
        if (mouseX < 239 && mouseX > 129 && mouseY < 322 && mouseY > 257 && flag == false) {
            System.out.println("play");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();
            System.out.printf("phl2");
            flag = true;

        }
        if (mouseX < 231 && mouseX > 130 && mouseY < 579 && mouseY > 514 && flag == false) {
            System.out.println("Exit");
            System.exit(0);
            flag = true;
        }


    }

    public boolean isFlag2() {
        return flag2;
    }

}
