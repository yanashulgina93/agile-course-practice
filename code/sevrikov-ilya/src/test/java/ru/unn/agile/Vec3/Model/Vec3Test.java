package ru.unn.agile.Vec3.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vec3Test {

    private Vec3TestState myState = new Vec3TestState();

    @Test
    public void canDefaultInitialize() {
        myState.setFirstVec(0.0, 0.0, 0.0);

        assert myState.getFirstVec().isEqual(0.0, 0.0, 0.0);
    }

    @Test
    public void canInitializeFromValues() {
        myState.setFirstVec(6.0, 6.0, 6.0);

        assert myState.getFirstVec().isEqual(6.0, 6.0, 6.0);
    }

    @Test
    public void canInitializeFromArray() {
        myState.setFirstVec(new double[]{9.0, 9.0, 9.0});

        assert myState.getFirstVec().isEqual(9.0, 9.0, 9.0);
    }

    @Test
    public void isEqualTwoVectors() {
        myState.setFirstVec(new double[] {9.0, 9.0, 9.0}).setSecondVec(9.0, 9.0, 9.0);

        assert myState.getFirstVec().isEqual(myState.getSecondVec());
    }

    @Test
    public void isCorrectDotProduct() {
        myState.setFirstVec(8.0, 6.0, 6.0).setSecondVec(5.0, 6.0, 4.0);

        final double anExpectedDotProduct = 100.0;
        final double anActualDotProduct   = myState.getFirstVec().dot(myState.getSecondVec());

        assertEquals(anExpectedDotProduct, anActualDotProduct, Precision.confusion());
    }

    @Test
    public void isCorrectNorm() {
        myState.setFirstVec(9.0, 2.0, 6.0);

        final double anExpectedNorm = 11.0;
        final double anActualNorm   = myState.getFirstVec().norm();

        assertEquals(anExpectedNorm, anActualNorm, Precision.confusion());
    }

    @Test
    public void isCorrectNormalize() {
        final double anInvNorm     = 1.0 / Math.sqrt(14.0);
        myState.setFirstVec(1.0, 2.0, 3.0)
               .setSecondVec(1.0 * anInvNorm, 2.0 * anInvNorm, 3.0 * anInvNorm);

        myState.getFirstVec().normalize();

        assert myState.getFirstVec().isEqual(myState.getSecondVec());
    }

    @Test (expected = ArithmeticException.class)
    public void normIsEqualZero() {
        myState.setFirstVec(0.0, 0.0, 0.0);

        myState.getFirstVec().normalize();
    }

    @Test
    public void isCorrectCrossProduct() {
        myState.setFirstVec(1.0, 0.0, 0.0).setSecondVec(0.0, 1.0, 0.0);

        final Vec3 anAxisZ = myState.getFirstVec().cross(myState.getSecondVec());

        assert anAxisZ.isEqual(0.0, 0.0, 1.0);
    }
}

