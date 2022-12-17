public class MalhaComputacional {
    private double dx;
    private double h;
    private double L;
    private double Ta;
    private double Te;
    private double Td;

    public MalhaComputacional( double dx, double h,double L,double Ta, double Te,double Td) {
        this.dx = dx;
        this.h = h;
        this.L = L;
        this.Ta = Ta;
        this.Te = Te;
        this.Td = Td;
    }
    public double[][] gerar_matriz_A(int n) {
        double[][] A = new double[n][n];
        for(int i = 0; i < n; i++) {
            A[i][i] = 2 + Math.pow(dx,2)*h;
            if(i+1 < n){
                A[i+1][i] = -1;
                A[i][i+1] = -1;
            }
        }
        return A;
    }
    public double[] gerar_vetor_b(int n) {
        double[] b = new double[n];
        for(int i = 0; i < n; i++) {
            b[i] = Math.pow(dx,2)*h*Ta;
            if(i%n == 0) b[i] += (i==0?Te:Td);
        }
        return b;
    }
    public void resolver_sistema(double[] Ti ) {

    }
}
