import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DFDT {
    public static final int SCALE = 10;

    public DFDT() {
    }

    public static BigDecimal Ca(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        BigDecimal alpha = new BigDecimal(-10, new MathContext(10, RoundingMode.HALF_UP));
        alpha = alpha.multiply(Ca).multiply(Cc).add(Cb);
        return alpha;
    }

    public static BigDecimal Cb(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        BigDecimal beta = new BigDecimal(10, new MathContext(10, RoundingMode.HALF_UP));
        return beta.multiply(Ca).multiply(Cc).subtract(Cb);
    }

    public static BigDecimal Cc(BigDecimal t, BigDecimal Ca, BigDecimal Cb, BigDecimal Cc) {
        BigDecimal gama = new BigDecimal(-10, new MathContext(10, RoundingMode.HALF_UP));
        gama = gama.multiply(Ca).multiply(Cc).add(Cb).subtract(Cc.multiply(BigDecimal.valueOf(2L)));
        return gama;
    }
}