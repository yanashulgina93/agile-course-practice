package ru.unn.agile.Vec3.Model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Vec3Test
{
    @Test
    public void DefaultInitialize()
    {
        final Vec3 aVec = new Vec3();

        assert(aVec.IsEqual(0.0, 0.0, 0.0));
    }

    @Test
    public void InitializeFromValues()
    {
        final Vec3 aVec = new Vec3(6.0, 6.0, 6.0);

        assert(aVec.IsEqual(6.0, 6.0, 6.0));
    }

    @Test
    public void InitializeFromArray()
    {
        final double[] anArray = {9.0, 9.0, 9.0};
        final Vec3     aVec    = new Vec3(anArray);

        assert(aVec.IsEqual(9.0, 9.0, 9.0));
    }

    @Test
    public void IsEqualTwoVectors()
    {
        final double[] anArray = {9.0, 9.0, 9.0};
        final Vec3 aVecOne = new Vec3(anArray);
        final Vec3 aVecTwo = new Vec3(9.0, 9.0, 9.0);

        assert(aVecOne.IsEqual(aVecTwo));
    }

    @Test
    public void IsCorrectDotProduct()
    {
        final Vec3 aVecOne = new Vec3(8.0, 0.0, 6.0);
        final Vec3 aVecTwo = new Vec3(5.0, 0.0, 4.0);

        final double anExpectedDotProduct = 64.0;
        final double anActualDotProduct   = aVecOne.Dot(aVecTwo);

        assertEquals(anExpectedDotProduct, anActualDotProduct, Precision.Confusion());
    }

    @Test
    public void IsCorrectNorm()
    {
        final Vec3 aVec = new Vec3(8.0, 0.0, 6.0);

        final double anExpectedNorm = 10.0;
        final double anActualNorm   = aVec.Norm();

        assertEquals(anExpectedNorm, anActualNorm, Precision.Confusion());
    }

    @Test
    public void IsCorrectNormalize()
    {
        Vec3 anActualVec         = new Vec3(8.0, 0.0, 6.0);
        final Vec3 anExpectedVec = new Vec3(0.8, 0.0, 0.6);

        anActualVec.Normalize();

        assert(anActualVec.IsEqual(anExpectedVec));
    }

    @Test (expected = ArithmeticException.class)
    public void NormIsEqualZero()
    {
        Vec3 aVec = new Vec3(0.0, 0.0, 0.0);

        aVec.Normalize();
    }

    @Test
    public void IsCorrectCrossProduct()
    {
        final Vec3 anAxisX = new Vec3(1.0, 0.0, 0.0);
        final Vec3 anAxisY = new Vec3(0.0, 1.0, 0.0);

        final Vec3 anAxisZ = anAxisX.Cross(anAxisY);

        assert(anAxisZ.IsEqual(0.0, 0.0, 1.0));
    }
}

