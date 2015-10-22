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
        Length l1 = new Length();
        l1.setValue(1);
        l1.setUnitInput(LengthUnit.Meter);
        Length res_z1 = z1.convert(l1, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length l2 = new Length();
        l2.setValue(1);
        l2.setUnitInput(LengthUnit.Meter);
        Length res_z2 = z2.convert(l2, LengthUnit.Yard);
        assertTrue(res_z1.equals(res_z2));
    }

    @Test
    public void areResultsWithDifferentValuesNotEqual() {
        LengthConvertor z1 = new LengthConvertor();
        Length l1 = new Length();
        l1.setValue(1);
        l1.setUnitInput(LengthUnit.Meter);
        Length res_z1 = z1.convert(l1, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length l2 = new Length();
        l2.setValue(2);
        l2.setUnitInput(LengthUnit.Meter);
        Length res_z2 = z2.convert(l2, LengthUnit.Yard);
        assertFalse(res_z1.equals(res_z2));
    }

    @Test
    public void areResultsWithDifferentOutputUnitNotEqual() {
        LengthConvertor z1 = new LengthConvertor();
        Length l1 = new Length();
        l1.setValue(1);
        l1.setUnitInput(LengthUnit.Meter);
        Length res_z1 = z1.convert(l1, LengthUnit.Yard);
        LengthConvertor z2 = new LengthConvertor();
        Length l2 = new Length();
        l2.setValue(1);
        l2.setUnitInput(LengthUnit.Foot);
        Length res_z2 = z2.convert(l2, LengthUnit.Yard);
        assertFalse(res_z1.equals(res_z2));
    }

    @Test
    public void canConvertKMetersToMeters() {
        LengthConvertor z = new LengthConvertor();
        Length l = new Length();
        l.setValue(1);
        l.setUnitInput(LengthUnit.KMeter);
        Length res_z = z.convert(l, LengthUnit.Meter);
        assertEquals(1000.0, res_z.getValue());
    }
}
