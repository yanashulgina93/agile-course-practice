package ru.unn.agile.Vec3.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vec3Test {
    @Test
    public void defaultInitialize() {
        final Vec3 aVec = new Vec3();

        assert aVec.isEqual(0.0, 0.0, 0.0);
    }

    @Test
    public void initializeFromValues() {
        final Vec3 aVec = new Vec3(6.0, 6.0, 6.0);

        assert aVec.isEqual(6.0, 6.0, 6.0);
    }

    @Test
    public void initializeFromArray() {
        final double[] anArray = {9.0, 9.0, 9.0};
        final Vec3     aVec    = new Vec3(anArray);

        assert aVec.isEqual(9.0, 9.0, 9.0);
    }

    @Test
    public void isEqualTwoVectors() {
        final double[] anArray = {9.0, 9.0, 9.0};
        final Vec3 aVecOne = new Vec3(anArray);
        final Vec3 aVecTwo = new Vec3(9.0, 9.0, 9.0);

        assert aVecOne.isEqual(aVecTwo);
    }

    @Test
    public void isCorrectDotProduct() {
        final Vec3 aVecOne = new Vec3(8.0, 0.0, 6.0);
        final Vec3 aVecTwo = new Vec3(5.0, 0.0, 4.0);

        final double anExpectedDotProduct = 64.0;
        final double anActualDotProduct   = aVecOne.dot(aVecTwo);

        assertEquals(anExpectedDotProduct, anActualDotProduct, Precision.confusion());
    }

    @Test
    public void isCorrectNorm() {
        final Vec3 aVec = new Vec3(8.0, 0.0, 6.0);

        final double anExpectedNorm = 10.0;
        final double anActualNorm   = aVec.norm();

        assertEquals(anExpectedNorm, anActualNorm, Precision.confusion());
    }

    @Test
    public void isCorrectNormalize() {
        Vec3 anActualVec         = new Vec3(8.0, 0.0, 6.0);
        final Vec3 anExpectedVec = new Vec3(0.8, 0.0, 0.6);

        anActualVec.normalize();

        assert anActualVec.isEqual(anExpectedVec);
    }

    @Test (expected = ArithmeticException.class)
    public void normIsEqualZero() {
        Vec3 aVec = new Vec3(0.0, 0.0, 0.0);

        aVec.normalize();
    }

    @Test
    public void isCorrectCrossProduct() {
        final Vec3 anAxisX = new Vec3(1.0, 0.0, 0.0);
        final Vec3 anAxisY = new Vec3(0.0, 1.0, 0.0);

        final Vec3 anAxisZ = anAxisX.cross(anAxisY);

        assert anAxisZ.isEqual(0.0, 0.0, 1.0);
    }
}

