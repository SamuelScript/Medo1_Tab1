import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RungeKutta3BigDecimal {
    private final MathContext mc;
    private final BigDecimal alpha;
    private final BigDecimal beta;
    private final BigDecimal gama;
    private final BigDecimal dt;
    private final BigDecimal dt2;
    private final BigDecimal hdt;
    private final BigDecimal dtsix;
    private final BigDecimal tmax;
    private final BigDecimal two = new BigDecimal("2");
    private final BigDecimal four = new BigDecimal("4");
    private BigDecimal Ca;
    private BigDecimal Cb;
    private BigDecimal Cc;
    private BigDecimal t;
    private boolean finished = false;

    private BigDecimal Ca(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        return alpha.multiply(Ca, mc).multiply(Cc, mc).add(Cb, mc);
    }

    private BigDecimal Cb(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        return beta.multiply(Ca, mc).multiply(Cc, mc).subtract(Cb, mc);
    }

    private BigDecimal Cc(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        return gama.multiply(Ca, mc).multiply(Cc, mc).add(Cb, mc).subtract(two.multiply(Cc, mc), mc);
    }

    /*
    * Aviso sobre esta classe: Big Decimal é baseado em ponto fixo e por isso é muito lento.
    * Eu (Gabriel) e Samuel aplicamos algumas otimizações "não convencionais" para tentar minimizar o impacto no CPU.
    * Por isso pode parecer que muito do código e variáveis são redundantes.
    * */
    public RungeKutta3BigDecimal(String t, String dt, String tmax, String Ca, String Cb, String Cc, String alpha, String beta, String gama, int precision) {
        mc = new MathContext(precision, RoundingMode.HALF_UP);
        this.t = new BigDecimal(t, mc);
        this.dt = new BigDecimal(dt, mc);
        this.tmax = new BigDecimal(tmax, mc);
        this.Ca = new BigDecimal(Ca, mc);
        this.Cb = new BigDecimal(Cb, mc);
        this.Cc = new BigDecimal(Cc, mc);
        this.alpha = new BigDecimal("-"+alpha, mc); //Invertido o alpha inline para simplificar as operações posteriores
        this.beta = new BigDecimal(beta, mc);
        this.gama = new BigDecimal("-"+gama, mc); //Invertido o gama inline para simplificar as operações posteriores
        this.dt2 = this.dt.multiply(two);
        this.hdt = this.dt.divide(two, mc);
        dtsix = this.dt.divide(new BigDecimal("6"), mc);
    }

    private void step() {
        BigDecimal k1_Ca = Ca(t, Ca, Cb, Cc);
        BigDecimal k1_Cb = Cb(t, Ca, Cb, Cc);
        BigDecimal k1_Cc = Cc(t, Ca, Cb, Cc);

        BigDecimal mod_k2[]  = new BigDecimal[]{
                t.add(hdt, mc),
                Ca.add(k1_Ca.multiply(hdt, mc), mc),
                Cb.add(k1_Cb.multiply(hdt, mc), mc),
                Cc.add(k1_Cc.multiply(hdt, mc), mc)
        };

        BigDecimal k2_Ca = Ca(mod_k2[0], mod_k2[1], mod_k2[2], mod_k2[3]);
        BigDecimal k2_Cb = Cb(mod_k2[0], mod_k2[1], mod_k2[2], mod_k2[3]);
        BigDecimal k2_Cc = Cc(mod_k2[0], mod_k2[1], mod_k2[2], mod_k2[3]);


        BigDecimal mod_k3[]  = new BigDecimal[]{
                t.add(dt, mc),
                Ca.subtract((k1_Ca.multiply(dt, mc)).subtract(k2_Ca.multiply(dt2, mc), mc), mc),
                Cb.subtract((k1_Cb.multiply(dt, mc)).subtract(k2_Cb.multiply(dt2, mc), mc), mc),
                Cc.subtract((k1_Cc.multiply(dt, mc)).subtract(k2_Cc.multiply(dt2, mc), mc), mc)
        };

        BigDecimal k3_Ca = Ca(mod_k3[0],mod_k3[1],mod_k3[2],mod_k3[3]);
        BigDecimal k3_Cb = Cb(mod_k3[0],mod_k3[1],mod_k3[2],mod_k3[3]);
        BigDecimal k3_Cc = Cc(mod_k3[0],mod_k3[1],mod_k3[2],mod_k3[3]);

        Ca = Ca.add(dtsix.multiply(k1_Ca.add((k2_Ca.multiply(four, mc)).add(k3_Ca, mc), mc), mc), mc);
        Cb = Cb.add(dtsix.multiply(k1_Cb.add((k2_Cb.multiply(four, mc)).add(k3_Cb, mc), mc), mc), mc);
        Cc = Cc.add(dtsix.multiply(k1_Cc.add((k2_Cc.multiply(four, mc)).add(k3_Cc, mc), mc), mc), mc);
        t = mod_k3[0];
    }

    public void run(GChart g) {
        if(finished) return;
        g.setLabel(3, "Ca (BigDecimal)");
        g.setLabel(4, "Cb (BigDecimal)");
        g.setLabel(5, "Cc (BigDecimal)");
        while(true) {
            g.addData(3, t, Ca);
            g.addData(4, t, Cb);
            g.addData(5, t, Cc);
            if(t.compareTo(tmax) == -1) {
                step();
                if(Ca.signum() != -1 && Cb.signum() > -1 && Cc.signum() != -1) continue;

                System.out.printf("O sistema desestabilizou para o t = %.6f\n", t);
            }
            finished = true;
            System.out.printf("(Big Decimal) Passo t = %.4f: Ca = %.6f, Cb = %.6f, Cc = %.6f\n", t, Ca, Cb, Cc);
            return;
        }
    }
}
