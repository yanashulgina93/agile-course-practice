package ru.unn.agile.PercentAccretion.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercentAccretionTest {

    @Test
    public void canCalculateSimplePercent() {
        assertEquals("150.0",Double.toString(PercentAccretion.calculateSimplePercent(100,50,1)));
    }

    @Test
    public void canCalculateComplexPercent() {
        assertEquals("337.5",Double.toString(PercentAccretion.calculateComplexPercent(100, 50, 3)));
    }
}
