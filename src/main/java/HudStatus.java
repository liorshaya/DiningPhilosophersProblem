import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HudStatus extends JPanel {
    private final List<JLabel> labels;

    public HudStatus(List<Philosof> philosofList) {
        this.setLayout(new GridLayout(5, 1));
        this.setOpaque(false);
        this.labels = new ArrayList<>();

        for (Philosof p : philosofList) {
            JLabel label = new JLabel();
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setForeground(Color.BLACK);
            label.setText(p.getName() + " - initializing...");
            this.add(label);
            labels.add(label);
        }

        new Thread(() -> {
            while (true) {
                for (int i = 0; i < philosofList.size(); i++) {
                    Philosof p = philosofList.get(i);
                    String status = switch (p.getStatus()) {
                        case Philosof.THINKING -> "Thinking";
                        case Philosof.WAITING_FOR_FORK_1 -> "Waiting fork 1";
                        case Philosof.WAITING_FOR_FORK_2 -> "Waiting fork 2";
                        case Philosof.EATING -> "Eating";
                        default -> "Unknown";
                    };
                    labels.get(i).setText(p.getName() + ": " + status + " | Count: " + p.getEatingCount());
                }
                Utils.sleep(400);
            }
        }).start();
    }

}
