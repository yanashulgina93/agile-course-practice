package ru.unn.agile.LengthConvertor.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthConvertorTest {
    private final double delta = 0.001;

    // @Test
    // public void failingTest()
    // {
    //     fail();
    // }

    @Test
    public void areEqualResultsEqual() {
        LengthConvertor z1 = new LengthConvertor();
        Length res_z1 = z1.Convert({1, LengthUnit.Meter}, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length res_z2 = z2.Convert({1, LengthUnit.Meter}, LengthUnit.Yard);
        assertTrue(res_z1.equals(rez_z2));
    }

    @Test
    public void areResultsWithDifferentValuesNotEqual() {
        LengthConvertor z1 = new LengthConvertor();
        Length res_z1 = z1.Convert({1, LengthUnit.Meter}, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length res_z2 = z2.Convert({2, LengthUnit.Meter}, LengthUnit.Yard);
        assertFalse(res_z1.equals(rez_z2));
    }

    @Test
    public void areResultsWithDifferentOutputUnitNotEqual() {
        LengthConvertor z1 = new LengthConvertor();
        Length res_z1 = z1.Convert({1, LengthUnit.Meter}, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length res_z2 = z2.Convert({1, LengthUnit.Foot}, LengthUnit.Yard);
        assertFalse(res_z1.equals(rez_z2));
    }

    @Test
    public void canConvertKMetersToMeters() {
        LengthConvertor z = new LengthConvertor();
        Length res_z = z.Convert({1, LengthUnit.KMeter}, LengthUnit.Meter);
        assertEquals({1000.0, Meter}, res_z);
    }
}
