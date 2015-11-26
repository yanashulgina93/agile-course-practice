package ru.unn.agile.StatisticValueCalculator.model;

import org.junit.Test;
import java.security.InvalidParameterException;

public class WhenMomentOrderIsIncorrect {
    @Test(expected = InvalidParameterException.class)
    public void throwsWhenInstantiateRawMomentOfZeroOrder() {
        new RawMomentCalculator(0);
    }

    @Test(expected = InvalidParameterException.class)
    public void throwsWhenInstantiateRawMomentWithNegativeOrder() {
        new RawMomentCalculator(-5);
    }

    @Test(expected = InvalidParameterException.class)
    public void throwsWhenInstantiateCentralMomentWithNegativeOrder() {
        new CentralMomentCalculator(-3);
    }

    @Test(expected = InvalidParameterException.class)
    public void throwsWhenInstantiateCentralMomentOfZeroOrder() {
        new CentralMomentCalculator(0);
    }
}
