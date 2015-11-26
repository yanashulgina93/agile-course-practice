package ru.unn.agile.StatisticValueCalculator.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class WhenStatisticDataIsNull {
    private final IStatisticValueCalculator calculator;

    @Parameters
    public static Collection<Object[]> getDataForTesting() {
        return Arrays.asList(new Object[][]{
                {new MeanCalculator()},
                {new VarianceCalculator()},
                {new ProbabilityOfEventCalculator(1.2)},
                {new CentralMomentCalculator(2)}});
    }

    public WhenStatisticDataIsNull(final IStatisticValueCalculator calculator) {
        this.calculator = calculator;
    }

    @Test(expected = NullPointerException.class)
    public void throwsWhenTryToCalculateStatisticValueOnNullData() {
        calculator.calculate(null);
    }
}
