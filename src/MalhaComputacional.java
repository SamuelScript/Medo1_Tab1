public class MalhaComputacional {
    private double dx;
    private double L;
    private double Te;
    private double Td;
    private double D;
    private double h;

    public MalhaComputacional(double dx, double h,double L, double Te,double Td, double D) {
        this.dx = dx;
        this.h = h;
        this.L = L;
        this.Te = Te;
        this.Td = Td;
        this.D = D;
    }

    public double[][] gerar_matriz_A(int n) {
        double[][] A = new double[n][n];
        for(int i = 0; i < n; i++) {
            A[i][i] = 2*D + Math.pow(dx,2)*h;
            if(i+1 < n){
                A[i+1][i] = -D;
                A[i][i+1] = -D;
            }
        }
        return A;
    }

    public double[] gerar_vetor_b(int n) {
        double[] b = new double[n];
        for(int i = 1; i < n-1; i++) b[i] = 0;
        b[0] += D*Te;
        b[n-1] += D*Td;
        return b;
    }
}
