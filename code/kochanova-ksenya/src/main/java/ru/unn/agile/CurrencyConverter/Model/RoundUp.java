package ru.unn.agile.CurrencyConverter.Model;


/**
 * Created by ksenyako on 26.10.2015.
 */
public class RoundUp {
    static final double HUNDREDTH_PATH  = 100;
    public RoundUp() {
    //empty
    }

    public double rounding(final double valueBeforeRound) {
        double tempValue = (int) Math.round(valueBeforeRound * HUNDREDTH_PATH);
        return (double) tempValue / HUNDREDTH_PATH;
    }
}

