import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel panel = new JPanel();
    private GChart charts[];

    public GChart getGChart(int idx) {
        return charts[idx];
    }
    
    public MainFrame() {
        super("Trabalho 1");
        setContentPane(panel);
        setSize(new Dimension(1200, 600));
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0};
        layout.rowHeights = new int[]{0};
        layout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel.setLayout(layout);

        charts = new GChart[2];
        charts[0] = new GChart("Concentração dos 3 reagentes", 3);
        charts[1] = new GChart("Distribuição de um reagente", 1);

        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.fill = GridBagConstraints.BOTH;
        gbc_1.gridwidth = 1;
        gbc_1.insets = new Insets(10, 1, 0, 2);
        gbc_1.gridx = 0;
        gbc_1.gridy = 0;
        panel.add(charts[0], gbc_1);

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.fill = GridBagConstraints.BOTH;
        gbc_2.gridwidth = 1;
        gbc_2.insets = new Insets(10, 1, 0, 2);
        gbc_2.gridx = 1;
        gbc_2.gridy = 0;
        panel.add(charts[1], gbc_2);
    }
}