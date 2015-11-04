package ru.unn.agile.Vec3.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vec3Test {
    private Vec3 myFirstVec;
    private Vec3 mySecondVec;

    @Test
    public void canDefaultInitialize() {
        myFirstVec = new Vec3();

        assert myFirstVec.equals(0.0, 0.0, 0.0);
    }

    @Test
    public void canInitializeFromValues() {
        myFirstVec = new Vec3(6.0, 6.0, 6.0);

        assert myFirstVec.equals(6.0, 6.0, 6.0);
    }

    @Test
    public void canInitializeFromArray() {
        myFirstVec = new Vec3(new double[]{9.0, 9.0, 9.0});

        assert myFirstVec.equals(9.0, 9.0, 9.0);
    }

    @Test
    public void isEqualTwoVectors() {
        myFirstVec  = new Vec3(new double[] {9.0, 9.0, 9.0});
        mySecondVec = new Vec3(9.0, 9.0, 9.0);

        assert myFirstVec.equals(mySecondVec);
    }

    @Test
    public void isCorrectDotProduct() {
        myFirstVec  = new Vec3(8.0, 6.0, 6.0);
        mySecondVec = new Vec3(5.0, 6.0, 4.0);

        final double anExpectedDotProduct = 100.0;
        final double anActualDotProduct   = myFirstVec.dot(mySecondVec);

        assertEquals(anExpectedDotProduct, anActualDotProduct, Precision.confusion());
    }

    @Test
    public void isCorrectNorm() {
        myFirstVec = new Vec3(9.0, 2.0, 6.0);

        final double anExpectedNorm = 11.0;
        final double anActualNorm   = myFirstVec.norm();

        assertEquals(anExpectedNorm, anActualNorm, Precision.confusion());
    }

    @Test
    public void isCorrectNormalize() {
        final double anInvNorm = 1.0 / Math.sqrt(14.0);
        myFirstVec             = new Vec3(1.0, 2.0, 3.0);
        mySecondVec            = new Vec3(1.0 * anInvNorm, 2.0 * anInvNorm, 3.0 * anInvNorm);

        myFirstVec.normalize();

        assert myFirstVec.equals(mySecondVec);
    }

    @Test (expected = ArithmeticException.class)
    public void normIsEqualZero() {
        myFirstVec = new Vec3(0.0, 0.0, 0.0);

        myFirstVec.normalize();
    }

    @Test
    public void isCorrectCrossProduct() {
        myFirstVec  = new Vec3(1.0, 0.0, 0.0);
        mySecondVec = new Vec3(0.0, 1.0, 0.0);

        final Vec3 anAxisZ = myFirstVec.cross(mySecondVec);

        assert anAxisZ.equals(0.0, 0.0, 1.0);
    }

    @Test (expected = NullPointerException.class)
    public void isCorrectEqualsNotNullObjectWithNullObject() {
        assert myFirstVec.equals(null);
    }
}

