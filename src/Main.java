import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;

import java.awt.*;
import java.util.Arrays;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        MainFrame main = new MainFrame();
        main.setVisible(true);
        RungeKutta3Double runge = new RungeKutta3Double(0.0, 0.01, 1.0, 10.0, 0.0, 10.0);
        //runge.run();

    }
}