import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    public MainFrame() {
        super("Teste");
        this.setSize(new Dimension(900, 600));
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component)null);
    }
}