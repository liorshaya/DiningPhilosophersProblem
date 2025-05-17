import javax.swing.*;
import java.awt.*;

public class Fork extends JPanel {
    private int number;
    private Philosof heldBy;

    public Fork(int number, int x, int y){
        this.setBounds(x, y, 30, 10);
        this.setOpaque(false);

        this.number = number;
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
    }

    public int getNumber(){
        return this.number;
    }

    public Philosof getHeldBy(){
        return this.heldBy;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
