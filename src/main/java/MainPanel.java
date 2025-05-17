import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel extends JPanel {
    private int width;
    private int height;
    private int x;
    private int y;

    public MainPanel(int width, int height, int x, int y){

        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        setBounds(x, y, width, height);
        this.setLayout(null);
        this.setBackground(new Color(155, 157, 159));

        Waiter waiter = new Waiter();

        Fork fork1 = new Fork(1, 400, 250);
        Fork fork2 = new Fork(2, 400, 450);
        Fork fork3 = new Fork(3, 650, 450);
        Fork fork4 = new Fork(4, 680, 280);
        Fork fork5 = new Fork(5, 450, 530);

        Philosof philosof1 = new Philosof("Boris",  fork1, fork2, waiter ,300 , 140);
        Philosof philosof2 = new Philosof("Shai",      fork2, fork3, waiter,  300 ,500);
        Philosof philosof3 = new Philosof("Alba",  fork3, fork4, waiter,690 , 530);
        Philosof philosof4 = new Philosof("Binyamin",  fork4, fork5, waiter,800 , 320);
        Philosof philosof5 = new Philosof("Dvora",       fork5, fork1, waiter,700 ,100);


        this.add(fork1);
        this.add(fork2);
        this.add(fork3);
        this.add(fork4);
        this.add(fork5);

        this.add(philosof1);
        this.add(philosof2);
        this.add(philosof3);
        this.add(philosof4);
        this.add(philosof5);

        List<Philosof> philosofList = List.of(philosof1, philosof2, philosof3, philosof4, philosof5);

        HudStatus hud = new HudStatus(philosofList);
        hud.setBounds(10, 490, 300, 150);
        this.add(hud);


        new Thread(()->{
            Utils.sleep(100);
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(0xB88149));
        g.fillOval(width / 2 - 225, height / 2 - 225, 450, 450);
    }
}
