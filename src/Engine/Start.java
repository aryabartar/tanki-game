package Engine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends MouseAdapter {
    JFrame frame=new JFrame();
    JLabel label=new JLabel();
    JLabel label2=new JLabel();
    private int mouseX;
    private int mouseY;

    public Start(){
        frame.setSize(1363,770);
        label.setSize(1363,770);
        label2.setSize(1363,770);
        label2.addMouseListener(this);
        label.addMouseListener(this);
        Icon icon_page1=new ImageIcon(getClass().getResource("page1.jpg"));
        Icon icon_page2=new ImageIcon(getClass().getResource("page2.jpg"));
        label.setIcon(icon_page1);
        label2.setIcon(icon_page2);
        frame.add(label);
        frame.setVisible(true);

    }
    @Override
    public void mousePressed(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println(mouseX);
        System.out.println(mouseY);
        if(mouseX<255 && mouseX>113 && mouseY<442 && mouseY>360) {
            System.out.println("Co-op");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();



        }
        if(mouseX<239 && mouseX>129 && mouseY<322 && mouseY>257) {
            System.out.println("play");
            frame.remove(label);
            frame.add(label2);
            frame.invalidate();
            frame.validate();
            frame.repaint();

        }
        if(mouseX<231 && mouseX>130 && mouseY<579 && mouseY>514) {
            System.out.println("Exit");
            System.exit(0);
        }


    }
}
