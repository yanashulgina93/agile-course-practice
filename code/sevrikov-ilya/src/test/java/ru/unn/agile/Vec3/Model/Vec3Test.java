package ru.unn.agile.Vec3.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vec3Test {
    private Vec3 firstVec;
    private Vec3 secondVec;

    @Test
    public void canDefaultInitialize() {
        firstVec = new Vec3();

        assert firstVec.equals(0.0, 0.0, 0.0);
    }

    @Test
    public void canInitializeFromValues() {
        firstVec = new Vec3(6.0, 6.0, 6.0);

        assert firstVec.equals(6.0, 6.0, 6.0);
    }

    @Test
    public void canInitializeFromArray() {
        firstVec = new Vec3(new double[]{9.0, 9.0, 9.0});

        assert firstVec.equals(9.0, 9.0, 9.0);
    }

    @Test
    public void isEqualTwoVectors() {
        firstVec  = new Vec3(new double[] {9.0, 9.0, 9.0});
        secondVec = new Vec3(9.0, 9.0, 9.0);

        assert firstVec.equals(secondVec);
    }

    @Test
    public void isCorrectDotProduct() {
        firstVec  = new Vec3(8.0, 6.0, 6.0);
        secondVec = new Vec3(5.0, 6.0, 4.0);

        final double expectedDotProduct = 100.0;
        final double actualDotProduct   = firstVec.dot(secondVec);

        assertEquals(expectedDotProduct, actualDotProduct, Precision.confusion());
    }

    @Test
    public void isCorrectNorm() {
        firstVec = new Vec3(9.0, 2.0, 6.0);

        final double expectedNorm = 11.0;
        final double actualNorm   = firstVec.norm();

        assertEquals(expectedNorm, actualNorm, Precision.confusion());
    }

    @Test
    public void isCorrectNormalize() {
        final double invNorm = 1.0 / Math.sqrt(14.0);
        firstVec             = new Vec3(1.0, 2.0, 3.0);
        secondVec            = new Vec3(1.0 * invNorm, 2.0 * invNorm, 3.0 * invNorm);

        firstVec.normalize();

        assert firstVec.equals(secondVec);
    }

    @Test (expected = ArithmeticException.class)
    public void normIsEqualZero() {
        firstVec = new Vec3(0.0, 0.0, 0.0);

        firstVec.normalize();
    }

    @Test
    public void isCorrectCrossProduct() {
        firstVec  = new Vec3(1.0, 0.0, 0.0);
        secondVec = new Vec3(0.0, 1.0, 0.0);

        final Vec3 anAxisZ = firstVec.cross(secondVec);

        assert anAxisZ.equals(0.0, 0.0, 1.0);
    }

    @Test (expected = NullPointerException.class)
    public void isCorrectEqualsNotNullObjectWithNullObject() {
        assert firstVec.equals(null);
    }
}

