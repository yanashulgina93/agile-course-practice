package ru.unn.agile.Vec3.ViewModel;

import ru.unn.agile.Vec3.Model.Vector3;

public class Vector3ViewModel {
    private String coordX0;
    private String coordY0;
    private String coordZ0;

    private String coordX1;
    private String coordY1;
    private String coordZ1;

    private String  resultOfLastAction;

    public Vector3ViewModel() {
        coordX0 = "0.0";
        coordY0 = "0.0";
        coordZ0 = "0.0";
        coordX1 = "0.0";
        coordY1 = "0.0";
        coordZ1 = "0.0";
    }

    public void setCoordX0(final String coordX0) {
        this.coordX0 = coordX0;
    }

    public void setCoordX0(final double coordX0) {
        this.coordX0 = Double.toString(coordX0);
    }

    public void setCoordY0(final String coordY0) {
        this.coordY0 = coordY0;
    }

    public void setCoordY0(final double coordY0) {
        this.coordY0 = Double.toString(coordY0);
    }

    public void setCoordZ0(final String coordZ0) {
        this.coordZ0 = coordZ0;
    }

    public void setCoordZ0(final double coordZ0) {
        this.coordZ0 = Double.toString(coordZ0);
    }

    public void setCoordX1(final String coordX1) {
        this.coordX1 = coordX1;
    }

    public void setCoordX1(final double coordX1) {
        this.coordX1 = Double.toString(coordX1);
    }

    public void setCoordY1(final String coordY1) {
        this.coordY1 = coordY1;
    }

    public void setCoordY1(final double coordY1) {
        this.coordY1 = Double.toString(coordY1);
    }

    public void setCoordZ1(final String coordZ1) {
        this.coordZ1 = coordZ1;
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
            resultOfLastAction = vector.toString();
        } catch (ArithmeticException exception) {
            resultOfLastAction = exception.getLocalizedMessage();
        }

        setComponentsOfFirstVector(vector);
    }

    public void normalizeSecondVector() {
        Vector3 vector = getSecondVector();

        try {
            vector.normalize();
            resultOfLastAction = vector.toString();
        } catch (ArithmeticException exception) {
            resultOfLastAction = exception.getLocalizedMessage();
        }

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
