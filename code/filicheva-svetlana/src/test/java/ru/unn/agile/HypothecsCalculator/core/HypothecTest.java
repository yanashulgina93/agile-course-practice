package ru.unn.agile.HypothecsCalculator.core;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HypothecTest {

    private final double delta = 0.001;

    private final Hypothec hypothec;
    private final double rightMonthlyPayment;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new Hypothec.Builder(1800000.0, 18).build(),
                        100000.0
                },
        });
    }

    public HypothecTest(final Hypothec hypothec, final double rightMonthlyPayment) {
        this.hypothec = hypothec;
        this.rightMonthlyPayment = rightMonthlyPayment;
    }

    @Test
    public void canGetMonthlyPayment() {
        final double monthlyPayment = hypothec.computeMonthlyPayment();

        assertEquals(rightMonthlyPayment, monthlyPayment, delta);
    }
}
