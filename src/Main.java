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

class Trabalho {
    public static void t1part1(GChart g) {
        RungeKutta3Double runge = new RungeKutta3Double(0.0, 0.01, 1.0, 10.0, 0.0, 10.0, 10.0, 10.0, 10.0);
        runge.run(g);
    }
    public static void t1part2(GChart g) {
        int nodes = 7;
        MalhaComputacional mcomp = new MalhaComputacional(5.0/(nodes-1),(27)*(0.000001),5.0,0.1,0.0,(9)*(0.000001));
        double[][] matrix = mcomp.gerar_matriz_A(nodes-2);//Número de nós internos
        double[] vetor_solucao = mcomp.gerar_vetor_b(nodes-2);
        TDM tdm = new TDM(matrix,vetor_solucao);
        tdm.solve();
        g.addData(0,0,0.1);
        for(int i = 0; i < nodes - 2; i++) g.addData(0, i+1, vetor_solucao[i]);
        g.addData(0,nodes - 1,0.0);
        g.setLabel(0,"Pontos da malha ao longo de L");
    }
}