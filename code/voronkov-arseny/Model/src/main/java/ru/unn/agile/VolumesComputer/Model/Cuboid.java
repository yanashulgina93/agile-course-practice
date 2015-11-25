package ru.unn.agile.VolumesComputer.Model;

public class Cuboid implements ICubiform {
    private final double mWidth;
    private final double mHeight;
    private final double mLength;

    public Cuboid(final double width,
                  final double height,
                  final double length) {
        if (width < 0.0 || height < 0.0 || length < 0.0) {
            throw new NegativeParametersException();
        }
        mWidth = width;
        mHeight = height;
        mLength = length;
    }
    @Override
    public double getHeight() {
        return mHeight;
    }
    @Override
    public double getLength() {
        return mLength;
    }
    @Override
    public double getWidth() {
        return mWidth;
    }
}
