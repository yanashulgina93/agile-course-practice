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
        Length length = new Length(1, LengthUnit.METER);
        assertNotNull(length);
    }

    @Test
    public void areEqualResultsEqual() {
        Length length1 = new Length(1, LengthUnit.METER);
        Length res1 = lengthConv1.convert(length1, LengthUnit.YARD);
        assertTrue(res1.equals(lengthConv2.convert(
                new Length(1, LengthUnit.METER), LengthUnit.YARD)));
    }

    @Test
    public void areResultsWithDifferentValuesNotEqual() {
        Length length1 = new Length(1, LengthUnit.METER);
        Length res1 = lengthConv1.convert(length1, LengthUnit.YARD);
        Length length2 = new Length(2, LengthUnit.METER);
        Length res2 = lengthConv2.convert(length2, LengthUnit.YARD);
        assertFalse(res1.equals(res2));
    }

    @Test
    public void areResultsWithDifferentOutputUnitNotEqual() {
        Length length1 = new Length(1, LengthUnit.METER);
        Length res1 = lengthConv1.convert(length1, LengthUnit.YARD);
        Length length2 = new Length(1, LengthUnit.FOOT);
        Length res2 = lengthConv2.convert(length2, LengthUnit.YARD);
        assertFalse(res1.equals(res2));
    }

    @Test
    public void canConvertKMetersToMeters() {
        Length length = new Length(1, LengthUnit.KMETER);
        Length res = lengthConv1.convert(length, LengthUnit.METER);
        assertEquals(1000.0, res.getValue(), delta);
    }

    @Test
    public void canConvertYardsToInches() {
        Length length = new Length(5, LengthUnit.YARD);
        Length res = lengthConv1.convert(length, LengthUnit.INCH);
        assertEquals(180.0, res.getValue(), delta);
    }

    @Test
    public void areValuesEqual() {
        Length length = new Length(347.23, LengthUnit.YARD);
        assertEquals(347.23, length.getValue(), delta);
    }

    @Test
    public void areLengthUnitsEqual() {
        Length length = new Length(347.64, LengthUnit.METER);
        assertEquals(LengthUnit.METER, length.getUnit());
    }

    @Test
    public void canSetAnotherValue() {
        Length length = new Length(3442.23, LengthUnit.YARD);
        length.setValue(100000.23);
        assertEquals(100000.23, length.getValue(), delta);
    }

    @Test
    public void canSetAnotherUnit() {
        Length length = new Length(3442.23, LengthUnit.YARD);
        length.setUnitInput(LengthUnit.CMETER);
        assertEquals(LengthUnit.CMETER, length.getUnit());
    }

    @Test (expected = IllegalArgumentException.class)
    public void throwsOnNegativeValue() {
        Length length = new Length(-1.0, LengthUnit.YARD);
    }

}
