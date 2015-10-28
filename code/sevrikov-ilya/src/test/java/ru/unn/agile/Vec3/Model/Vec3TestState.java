package ru.unn.agile.Vec3.Model;

public class Vec3TestState {
    private Vec3 myFirstVec;
    private Vec3 mySecondVec;

    public Vec3TestState() {
        /* none */
    }

    public Vec3TestState setFirstVec(final double theX,
                                     final double theY,
                                     final double theZ) {
        myFirstVec = new Vec3(theX, theY, theZ);

        return this;
    }

    public Vec3TestState setFirstVec(final double[] theArray) {
        myFirstVec = new Vec3(theArray);

        return this;
    }

    public Vec3TestState setSecondVec(final double theX,
                                      final double theY,
                                      final double theZ) {
        mySecondVec = new Vec3(theX, theY, theZ);

        return this;
    }

    public Vec3TestState setSecondVec(final double[] theArray) {
        mySecondVec = new Vec3(theArray);

        return this;
    }

    public Vec3 getFirstVec() {
        return myFirstVec;
    }

    public Vec3 getSecondVec() {
        return mySecondVec;
    }
}
