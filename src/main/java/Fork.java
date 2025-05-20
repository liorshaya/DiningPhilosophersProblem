import javax.swing.*;
import java.awt.*;

public class Fork extends JPanel {
    private int number;
    private Philosof heldBy;
    private final int originalX;
    private final int originalY;


    public Fork(int number, int x, int y){
        this.number = number;
        this.originalX = x;
        this.originalY = y;

        if(this.number % 2 == 1){
            this.setBounds(x, y, 30, 10);
        }
        else {
            this.setBounds(x, y, 10, 30);

        }

        this.setOpaque(false);
        this.heldBy = null;
    }

    public String toString(){
        if (this.heldBy == null){
            return "This fork is not held by anyone!";
        }
        return "Fork " + this.number + " is currently held by " + this.heldBy.getName();
    }

    public void setHeldBy(Philosof philosof){
        this.heldBy = philosof;

        if (philosof != null) {
            int x = philosof.getX();
            int y = philosof.getY();
            this.setLocation(x + 20, y + 20);
        } else {
            this.setLocation(originalX, originalY);
        }

        repaint();
    }

    public int getNumber(){
        return this.number;
    }

    public Philosof getHeldBy(){
        return this.heldBy;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(heldBy == null){
            g.setColor(Color.DARK_GRAY);
        }
        else{
            g.setColor(Color.ORANGE);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
