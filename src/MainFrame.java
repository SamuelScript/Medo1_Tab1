import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel panel = new JPanel();
    private GChart gteste1 = new GChart("Titulo", 1);
    private GChart gteste2 = new GChart("Interessante", 2);

    public void addGChart(GChart chart) {
        //getContentPane().add(chart);
    }
    
    public MainFrame() {
        super("Teste");
        setContentPane(panel);
        setSize(new Dimension(900, 600));
        setDefaultCloseOperation(3);
        setLocationRelativeTo((Component) null);

        GridBagLayout layout = new GridBagLayout();
        layout.columnWidths = new int[]{0, 0};
        layout.rowHeights = new int[]{0, 0};
        layout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        layout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        panel.setLayout(layout);

        GridBagConstraints gbc_1 = new GridBagConstraints();
        gbc_1.fill = GridBagConstraints.BOTH;
        gbc_1.gridwidth = 1;
        gbc_1.insets = new Insets(10, 1, 0, 2);
        gbc_1.gridx = 0;
        gbc_1.gridy = 0;
        panel.add(gteste1, gbc_1);

        GridBagConstraints gbc_2 = new GridBagConstraints();
        gbc_2.fill = GridBagConstraints.BOTH;
        gbc_2.gridwidth = 1;
        gbc_2.insets = new Insets(10, 1, 0, 2);
        gbc_2.gridx = 1;
        gbc_2.gridy = 0;
        panel.add(gteste2, gbc_2);

        gteste1.setData(0, new double[]{0.0, 1.0}, new double[]{0.0, 1.0});
        gteste2.setData(0, new double[]{0.0, 1.0}, new double[]{0.0, 1.0});
        gteste2.setData(1, new double[]{0.0, 1.0}, new double[]{1.0, 0.0});
    }
}