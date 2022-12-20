import javax.swing.UIManager;

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

        //Trabalho.t1part1(main.getGChart(0));
        Trabalho.t1part2(main.getGChart(1));
    }
}

class Trabalho {
    public static void t1part1(GChart g) {
        RungeKutta3Double runge = new RungeKutta3Double(0.0, 0.001, 5.0, 29.0, 0.0, 43.0, 9.0, 11.0, 9.5);
        runge.run(g);
        RungeKutta3BigDecimal rungebd = new RungeKutta3BigDecimal("0.0", "0.001", "5.0", "29.0", "0.0", "43.0", "9.0", "11.0", "9.5",20);
        rungebd.run(g);
    }
    public static void t1part2(GChart g) {
        int nodes[] = {5, 10, 20, 50, 100};
        double L = 5.0;

        for(int i=0; i<nodes.length; i++) {
            g.setLabel(i,"n = "+nodes[i]);
            g.addData(i,0,0.1); //Contorno Esquerdo
            g.addData(i, L,0.0); //Contorno Direito

            MalhaComputacional mcomp = new MalhaComputacional(L/(nodes[i]-1),3.9e-6,L,0.1,0.0,1.3e-6);
            double[][] matrix = mcomp.gerar_matriz_A(nodes[i]-2);//Número de nós internos
            double[] vetor_solucao = mcomp.gerar_vetor_b(nodes[i]-2);
            TDM tdm = new TDM(matrix,vetor_solucao);
            tdm.solve();
            for(int j = 0; j < nodes[i] - 2; j++) g.addData(i, (j+1)*(L/(nodes[i]-1)), vetor_solucao[j]);
        }
    }
}