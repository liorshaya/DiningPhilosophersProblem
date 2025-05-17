import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Philosof extends JPanel {
    public static final int THINKING = 1;
    public static final int WAITING_FOR_FORK_1 = 2;
    public static final int WAITING_FOR_FORK_2 = 3;
    public static final int EATING = 4;
    private Waiter waiter;

    private String name;
    private int status;
    private int eatingCount;

    private Fork fork1;
    private Fork fork2;

    public Philosof(String name, Fork fork1, Fork fork2, Waiter waiter, int x, int y){
        this.setBounds(x, y, 60, 60);
        this.setOpaque(false);


        this.name = name;
        this.status = THINKING;
        this.eatingCount = 0;
        this.fork1 = fork1;
        this.fork2 = fork2;
        this.waiter = waiter;

        this.start();
    }

    public String toString(){
        String statusText = "";
        switch (this.status){
            case THINKING -> statusText = "Thinking";
            case WAITING_FOR_FORK_1 -> statusText = "Waiting for fork 1";
            case WAITING_FOR_FORK_2 -> statusText = "Waiting for fork 1";
            case EATING -> statusText = "Eating";
        }
        return "Philosof " + this.name + " is currently " + statusText + " (total times he ate: " +
                this.eatingCount + ").";
    }



    private void start () {
        new Thread(() -> {
            Random random = new Random();
            while (true) {
                Utils.sleep(random.nextInt(5000));
                while (!waiter.requestPermission(this)){
                    Utils.sleep(100);
                }
                this.status = WAITING_FOR_FORK_1;
                while (this.fork1.getHeldBy() != null) {
                    Utils.sleep(100);
                }
                this.fork1.setHeldBy(this);
                Utils.sleep(random.nextInt(1000));
                this.status = WAITING_FOR_FORK_2;
                while (this.fork2.getHeldBy() != null) {
                    Utils.sleep(100);
                }
                this.fork2.setHeldBy(this);
                this.status = EATING;
                repaint();
                Utils.sleep(random.nextInt(1000));
                this.fork1.setHeldBy(null);
                this.fork2.setHeldBy(null);
                waiter.doneEating(this);
                this.eatingCount++;
                this.status = THINKING;
                repaint();

            }
        }).start();
    }



    public String getName(){
        return this.name;
    }

    public int getEatingCount(){
        return this.eatingCount;
    }

    public int getStatus(){
        return this.status;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.status == EATING){
            g.setColor(Color.red);
        }
        else{
            g.setColor(Color.green);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
