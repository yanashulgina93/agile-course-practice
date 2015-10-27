package ru.unn.agile.LengthConvertor.Model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LengthConvertorTest {
    private final double delta = 0.001;
    private LengthConvertor lengthConv1 = new LengthConvertor();
    private LengthConvertor lengthConv2 = new LengthConvertor();

    // @Test
    // public void failingTest()
    // {
    //     fail();
    // }

    @Test
    public void canCreateLengthClassWithInitialValues() {
        Length length = new Length(1, LengthUnit.Meter);
        assertNotNull(length);
    }

    @Test
    public void areEqualResultsEqual() {
        Length length1 = new Length(1, LengthUnit.Meter);
        Length res1 = lengthConv1.convert(length1, LengthUnit.Yard);
        assertTrue(res1.isEqual(lengthConv2.convert(
                new Length(1, LengthUnit.Meter), LengthUnit.Yard)));
    }

    @Test
    public void areResultsWithDifferentValuesNotEqual() {
        Length length1 = new Length(1, LengthUnit.Meter);
        Length res1 = lengthConv1.convert(length1, LengthUnit.Yard);
        Length length2 = new Length(2, LengthUnit.Meter);
        Length res2 = lengthConv2.convert(length2, LengthUnit.Yard);
        assertFalse(res1.isEqual(res2));
    }

    @Test
    public void areResultsWithDifferentOutputUnitNotEqual() {
        Length length1 = new Length(1, LengthUnit.Meter);
        Length res1 = lengthConv1.convert(length1, LengthUnit.Yard);
        Length length2 = new Length(1, LengthUnit.Foot);
        Length res2 = lengthConv2.convert(length2, LengthUnit.Yard);
        assertFalse(res1.isEqual(res2));
    }

    @Test
    public void canConvertKMetersToMeters() {
        Length length = new Length(1, LengthUnit.KMeter);
        Length res = lengthConv1.convert(length, LengthUnit.Meter);
        assertEquals(1000.0, res.getValue(), delta);
    }

    @Test
    public void canConvertYardsToInches() {
        Length length = new Length(5, LengthUnit.Yard);
        Length res = lengthConv1.convert(length, LengthUnit.Inch);
        assertEquals(180.0, res.getValue(), delta);
    }

    @Test
    public void areValuesEqual() {
        Length length = new Length(347.23, LengthUnit.Yard);
        assertEquals(347.23, length.getValue(), delta);
    }

    @Test
    public void areLengthUnitsEqual() {
        Length length = new Length(347.64, LengthUnit.Meter);
        assertEquals(LengthUnit.Meter, length.getUnit());
    }

    @Test
    public void canSetAnotherValue() {
        Length length = new Length(3442.23, LengthUnit.Yard);
        length.setValue(100000.23);
        assertEquals(100000.23, length.getValue(), delta);
    }

    @Test
    public void canSetAnotherUnit() {
        Length length = new Length(3442.23, LengthUnit.Yard);
        length.setUnitInput(LengthUnit.CMeter);
        assertEquals(LengthUnit.CMeter, length.getUnit());
    }

}
