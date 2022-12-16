public class RungeKutta3Double {
    private final double alpha;
    private final double beta;
    private final double gama;
    private double Ca;
    private double Cb;
    private double Cc;
    private double t;
    private double dt;
    private double tmax;

    private double Ca(double t, double Ca, double Cb, double Cc) {
        return -this.alpha * Ca * Cc + Cb;
    }

    private double Cb(double t, double Ca, double Cb, double Cc) {
        return this.beta * Ca * Cc - Cb;
    }

    private double Cc(double t, double Ca, double Cb, double Cc) {
        return -this.gama * Ca * Cc + Cb - 2.0 * Cc;
    }

    public RungeKutta3Double(double t, double dt, double tmax, double Ca, double Cb, double Cc) {
        this.t = t;
        this.dt = dt;
        this.tmax = tmax;
        this.Ca = Ca;
        this.Cb = Cb;
        this.Cc = Cc;
        this.alpha = 10.0;
        this.beta = 10.0;
        this.gama = 10.0;
    }

    public RungeKutta3Double(double t, double dt, double tmax, double Ca, double Cb, double Cc, double alpha, double beta, double gama) {
        this.t = t;
        this.dt = dt;
        this.tmax = tmax;
        this.Ca = Ca;
        this.Cb = Cb;
        this.Cc = Cc;
        this.alpha = alpha;
        this.beta = beta;
        this.gama = gama;
    }

    private void step() {
        double k1_Ca = this.Ca(this.t, this.Ca, this.Cb, this.Cc);
        double k1_Cb = this.Cb(this.t, this.Ca, this.Cb, this.Cc);
        double k1_Cc = this.Cc(this.t, this.Ca, this.Cb, this.Cc);
        double k2_Ca = this.Ca(this.t + this.dt / 2.0, this.Ca + k1_Ca * (this.dt / 2.0), this.Cb + k1_Cb * (this.dt / 2.0), this.Cc + k1_Cc * (this.dt / 2.0));
        double k2_Cb = this.Cb(this.t + this.dt / 2.0, this.Ca + k1_Ca * (this.dt / 2.0), this.Cb + k1_Cb * (this.dt / 2.0), this.Cc + k1_Cc * (this.dt / 2.0));
        double k2_Cc = this.Cc(this.t + this.dt / 2.0, this.Ca + k1_Ca * (this.dt / 2.0), this.Cb + k1_Cb * (this.dt / 2.0), this.Cc + k1_Cc * (this.dt / 2.0));
        double k3_Ca = this.Ca(this.t + this.dt, this.Ca - k1_Ca * this.dt + 2.0 * k2_Ca * this.dt, this.Cb - k1_Cb * this.dt + 2.0 * k2_Cb * this.dt, this.Cc - k2_Cc * this.dt + 2.0 * k2_Cc * this.dt);
        double k3_Cb = this.Cb(this.t + this.dt, this.Ca - k1_Ca * this.dt + 2.0 * k2_Ca * this.dt, this.Cb - k1_Cb * this.dt + 2.0 * k2_Cb * this.dt, this.Cc - k2_Cc * this.dt + 2.0 * k2_Cc * this.dt);
        double k3_Cc = this.Cc(this.t + this.dt, this.Ca - k1_Ca * this.dt + 2.0 * k2_Ca * this.dt, this.Cb - k1_Cb * this.dt + 2.0 * k2_Cb * this.dt, this.Cc - k2_Cc * this.dt + 2.0 * k2_Cc * this.dt);
        this.Ca += this.dt / 6.0 * (k1_Ca + 4.0 * k2_Ca + k3_Ca);
        this.Cb += this.dt / 6.0 * (k1_Cb + 4.0 * k2_Cb + k3_Cb);
        this.Cc += this.dt / 6.0 * (k1_Cc + 4.0 * k2_Cc + k3_Cc);
        this.t += this.dt;
    }

    public void run() {
        while(true) {
            if (this.t <= this.tmax) {
                System.out.printf("Passo t = %.4f: Ca = %.6f, Cb = %.6f, Cc = %.6f\n", this.t, this.Ca, this.Cb, this.Cc);
                this.step();
                if (!(this.Ca < 0.0) && !(this.Cb < 0.0) && !(this.Cc < 0.0)) {
                    continue;
                }

                System.out.printf("O sistema desestabilizou para o t = %.6f\n", this.t);
                System.out.printf("Passo t = %.4f: Ca = %.6f, Cb = %.6f, Cc = %.6f\n", this.t, this.Ca, this.Cb, this.Cc);
            }

            return;
        }
    }
}
