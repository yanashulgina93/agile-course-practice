package ru.unn.agile.Vec3.ViewModel;

import ru.unn.agile.Vec3.Model.Vector3;

public class Vector3ViewModel {
    private static final String DEFAULT_COORD_X0 = "0.0";
    private static final String DEFAULT_COORD_Y0 = "0.0";
    private static final String DEFAULT_COORD_Z0 = "0.0";

    private static final String DEFAULT_COORD_X1 = "0.0";
    private static final String DEFAULT_COORD_Y1 = "0.0";
    private static final String DEFAULT_COORD_Z1 = "0.0";

    private String coordX0;
    private String coordY0;
    private String coordZ0;

    private String coordX1;
    private String coordY1;
    private String coordZ1;

    private String  resultOfLastAction;

    public Vector3ViewModel() {
        coordX0 = DEFAULT_COORD_X0;
        coordY0 = DEFAULT_COORD_Y0;
        coordZ0 = DEFAULT_COORD_Z0;
        coordX1 = DEFAULT_COORD_X1;
        coordY1 = DEFAULT_COORD_Y1;
        coordZ1 = DEFAULT_COORD_Z1;
    }

    public String getDefaultCoordX0() {
        return DEFAULT_COORD_X0;
    }

    public String getDefaultCoordY0() {
        return DEFAULT_COORD_Y0;
    }

    public String getDefaultCoordZ0() {
        return DEFAULT_COORD_Z0;
    }

    public String getDefaultCoordX1() {
        return DEFAULT_COORD_X1;
    }

    public String getDefaultCoordY1() {
        return DEFAULT_COORD_Y1;
    }

    public String getDefaultCoordZ1() {
        return DEFAULT_COORD_Z1;
    }

    public void setCoordX0(final String coordX0) {
        try {
            this.coordX0 = Double.valueOf(coordX0).toString();
        } catch (NumberFormatException e) {
            this.coordX0 = getDefaultCoordX0();
        }
    }

    public void setCoordX0(final double coordX0) {
        this.coordX0 = Double.toString(coordX0);
    }

    public void setCoordY0(final String coordY0) {
        try {
            this.coordY0 = Double.valueOf(coordY0).toString();
        } catch (NumberFormatException e) {
            this.coordY0 = getDefaultCoordY0();
        }
    }

    public void setCoordY0(final double coordY0) {
        this.coordY0 = Double.toString(coordY0);
    }

    public void setCoordZ0(final String coordZ0) {
        try {
            this.coordZ0 = Double.valueOf(coordZ0).toString();
        } catch (NumberFormatException e) {
            this.coordZ0 = getDefaultCoordZ0();
        }
    }

    public void setCoordZ0(final double coordZ0) {
        this.coordZ0 = Double.toString(coordZ0);
    }

    public void setCoordX1(final String coordX1) {
        try {
            this.coordX1 = Double.valueOf(coordX1).toString();
        } catch (NumberFormatException e) {
            this.coordX1 = getDefaultCoordX1();
        }
    }

    public void setCoordX1(final double coordX1) {
        this.coordX1 = Double.toString(coordX1);
    }

    public void setCoordY1(final String coordY1) {
        try {
            this.coordY1 = Double.valueOf(coordY1).toString();
        } catch (NumberFormatException e) {
            this.coordY1 = getDefaultCoordY1();
        }
    }

    public void setCoordY1(final double coordY1) {
        this.coordY1 = Double.toString(coordY1);
    }

    public void setCoordZ1(final String coordZ1) {
        try {
            this.coordZ1 = Double.valueOf(coordZ1).toString();
        } catch (NumberFormatException e) {
            this.coordZ1 = getDefaultCoordZ1();
        }
    }

    public void setCoordZ1(final double coordZ1) {
        this.coordZ1 = Double.toString(coordZ1);
    }

    public String getCoordX0() {
        return coordX0;
    }

    public double getCoordX0AsDouble() {
        return Double.parseDouble(coordX0);
    }

    public String getCoordY0() {
        return coordY0;
    }

    public double getCoordY0AsDouble() {
        return Double.parseDouble(coordY0);
    }

    public String getCoordZ0() {
        return coordZ0;
    }

    public double getCoordZ0AsDouble() {
        return Double.parseDouble(coordZ0);
    }

    public String getCoordX1() {
        return coordX1;
    }

    public double getCoordX1AsDouble() {
        return Double.parseDouble(coordX1);
    }

    public String getCoordY1() {
        return coordY1;
    }

    public double getCoordY1AsDouble() {
        return Double.parseDouble(coordY1);
    }

    public String getCoordZ1() {
        return coordZ1;
    }

    public double getCoordZ1AsDouble() {
        return Double.parseDouble(coordZ1);
    }

    public void getNormOfFirstVector() {
        Vector3 vector = getFirstVector();

        resultOfLastAction = Double.toString(vector.getNorm());
    }

    public void getNormOfSecondVector() {
        Vector3 vector = getSecondVector();

        resultOfLastAction = Double.toString(vector.getNorm());
    }

    public void normalizeFirstVector() {
        Vector3 vector = getFirstVector();

        try {
            vector.normalize();
        } catch (ArithmeticException exception) {
            resultOfLastAction = exception.getLocalizedMessage();

            return;
        }

        resultOfLastAction = vector.toString();

        setComponentsOfFirstVector(vector);
    }

    public void normalizeSecondVector() {
        Vector3 vector = getSecondVector();

        try {
            vector.normalize();
        } catch (ArithmeticException exception) {
            resultOfLastAction = exception.getLocalizedMessage();

            return;
        }

        resultOfLastAction = vector.toString();

        setComponentsOfSecondVector(vector);
    }

    public void getDotProduct() {
        Vector3 firstVector  = getFirstVector();
        Vector3 secondVector = getSecondVector();

        resultOfLastAction = Double.toString(firstVector.dot(secondVector));
    }

    public void getCrossProduct() {
        Vector3 firstVector  = getFirstVector();
        Vector3 secondVector = getSecondVector();

        try {
            resultOfLastAction = firstVector.cross(secondVector).toString();
        } catch (ArithmeticException exception) {
            resultOfLastAction = exception.getLocalizedMessage();
        }
    }

    public String getResultOfLastAction() {
        return resultOfLastAction;
    }

    private Vector3 getFirstVector() {
        return new Vector3(Double.parseDouble(coordX0),
                           Double.parseDouble(coordY0),
                           Double.parseDouble(coordZ0));
    }

    private Vector3 getSecondVector() {
        return new Vector3(Double.parseDouble(coordX1),
                           Double.parseDouble(coordY1),
                           Double.parseDouble(coordZ1));
    }

    private void setComponentsOfFirstVector(final Vector3 vector) {
        coordX0 = Double.toString(vector.x());
        coordY0 = Double.toString(vector.y());
        coordZ0 = Double.toString(vector.z());
    }

    private void setComponentsOfSecondVector(final Vector3 vector) {
        coordX1 = Double.toString(vector.x());
        coordY1 = Double.toString(vector.y());
        coordZ1 = Double.toString(vector.z());
    }
}
