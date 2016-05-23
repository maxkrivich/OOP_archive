package client;

import compute.Task;
import java.math.BigDecimal;

public class Pi implements Task<BigDecimal>
{

    private static final long serialVersionUID = 227L;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int digits;

    public Pi(int digits)
    {
        this.digits = digits;
    }

    public BigDecimal execute()
    {
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits)
    {
        return BigDecimal.valueOf(Math.PI);
    }

}
