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
    private boolean finished = false;

    private double Ca(double t, double Ca, double Cb, double Cc) {
        return -alpha * Ca * Cc + Cb;
    }

    private double Cb(double t, double Ca, double Cb, double Cc) {
        return beta * Ca * Cc - Cb;
    }

    private double Cc(double t, double Ca, double Cb, double Cc) {
        return -gama * Ca * Cc + Cb - 2.0 * Cc;
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
        double k1_Ca = Ca(t, Ca, Cb, Cc);
        double k1_Cb = Cb(t, Ca, Cb, Cc);
        double k1_Cc = Cc(t, Ca, Cb, Cc);

        double hdt = dt/2;
        double k2_Ca = Ca(t + hdt, Ca + k1_Ca * hdt, Cb + k1_Cb * hdt, Cc + k1_Cc * hdt);
        double k2_Cb = Cb(t + hdt, Ca + k1_Ca * hdt, Cb + k1_Cb * hdt, Cc + k1_Cc * hdt);
        double k2_Cc = Cc(t + hdt, Ca + k1_Ca * hdt, Cb + k1_Cb * hdt, Cc + k1_Cc * hdt);

        double dt2 = dt*2;
        double k3_Ca = Ca(t + dt, Ca - k1_Ca * dt + k2_Ca * dt2, Cb - k1_Cb * dt + k2_Cb * dt2, Cc - k2_Cc * dt + k2_Cc * dt2);
        double k3_Cb = Cb(t + dt, Ca - k1_Ca * dt + k2_Ca * dt2, Cb - k1_Cb * dt + k2_Cb * dt2, Cc - k2_Cc * dt + k2_Cc * dt2);
        double k3_Cc = Cc(t + dt, Ca - k1_Ca * dt + k2_Ca * dt2, Cb - k1_Cb * dt + k2_Cb * dt2, Cc - k2_Cc * dt + k2_Cc * dt2);

        Ca += dt / 6.0 * (k1_Ca + 4.0 * k2_Ca + k3_Ca);
        Cb += dt / 6.0 * (k1_Cb + 4.0 * k2_Cb + k3_Cb);
        Cc += dt / 6.0 * (k1_Cc + 4.0 * k2_Cc + k3_Cc);
        t += dt;
    }

    public void run(GChart g) {
        if(finished) return;
        g.setLabel(0, "Ca");
        g.setLabel(1, "Cb");
        g.setLabel(2, "Cc");
        while(true) {
            if (t <= tmax) {
                System.out.printf("Passo t = %.4f: Ca = %.6f, Cb = %.6f, Cc = %.6f\n", t, Ca, Cb, Cc);
                step();
                g.addData(0, t, Ca);
                g.addData(1, t, Cb);
                g.addData(2, t, Cc);
                if (!(Ca < 0.0) && !(Cb < 0.0) && !(Cc < 0.0)) continue;

                System.out.printf("O sistema desestabilizou para o t = %.6f\n", t);
                System.out.printf("Passo t = %.4f: Ca = %.6f, Cb = %.6f, Cc = %.6f\n", t, Ca, Cb, Cc);
            }
            finished = true;
            return;
        }
    }
}
