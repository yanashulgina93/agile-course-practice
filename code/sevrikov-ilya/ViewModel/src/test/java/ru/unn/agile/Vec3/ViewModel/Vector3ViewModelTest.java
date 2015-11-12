package ru.unn.agile.Vec3.ViewModel;

import ru.unn.agile.Vec3.Model.Vector3;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Vector3ViewModelTest {
    private Vector3ViewModel viewModel = new Vector3ViewModel();

    @Test
    public void canGetNormOfDefaultFirstVector() {
        viewModel.getNormOfFirstVector();

        assert viewModel.getResultOfLastAction().equals("0.0");
    }

    @Test
    public void canGetNormOfDefaultSecondVector() {
        viewModel.getNormOfSecondVector();

        assert viewModel.getResultOfLastAction().equals("0.0");
    }

    @Test
    public void cannotNormalizeOfDefaultFirstVector() {
        String error = getErrorOfNormalize();

        viewModel.normalizeFirstVector();

        assertTrue(viewModel.getResultOfLastAction().equals(error));
    }

    @Test
    public void cannotNormalizeOfDefaultSecondVector() {
        String error = getErrorOfNormalize();

        viewModel.normalizeSecondVector();

        assertTrue(viewModel.getResultOfLastAction().equals(error));
    }

    @Test
    public void canGetNormOfSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double  norm   = vector.getNorm();

        setFirstVectorInViewModel(vector);

        viewModel.getNormOfFirstVector();

        assertTrue(viewModel.getResultOfLastAction().equals(Double.toString(norm)));
    }

    @Test
    public void canGetNormOfSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double  norm   = vector.getNorm();

        setSecondVectorInViewModel(vector);

        viewModel.getNormOfSecondVector();

        assertTrue(viewModel.getResultOfLastAction().equals(Double.toString(norm)));
    }

    @Test
    public void canNormalizeSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setFirstVectorInViewModel(vector);

        viewModel.normalizeFirstVector();
        vector.normalize();

        assertTrue(viewModel.getResultOfLastAction().equals(vector.toString()));
    }

    @Test
    public void canNormalizeSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setSecondVectorInViewModel(vector);

        viewModel.normalizeSecondVector();
        vector.normalize();

        assertTrue(viewModel.getResultOfLastAction().equals(vector.toString()));
    }

    @Test
    public void canDotProduct() {
        Vector3 firstVector  = new Vector3(1.0, 2.0, 3.0);
        Vector3 secondVector = new Vector3(3.0, 2.0, 1.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.getDotProduct();
        double dot = firstVector.dot(secondVector);

        assertTrue(viewModel.getResultOfLastAction().equals(Double.toString(dot)));
    }

    @Test
    public void cannotCrossProductWithDefaultVectors() {
        String error = getErrorOfNormalize();

        viewModel.getCrossProduct();

        assertTrue(viewModel.getResultOfLastAction().equals(error));
    }

    @Test
    public void canCrossProductFromSpecificVectors() {
        Vector3 firstVector  = new Vector3(1.0, 0.0, 0.0);
        Vector3 secondVector = new Vector3(0.0, 1.0, 0.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.getCrossProduct();
        Vector3 crossProduct = firstVector.cross(secondVector);

        assertTrue(viewModel.getResultOfLastAction().equals(crossProduct.toString()));
    }

    @Test
    public void isCorrectFirstComponentOfFistVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX0(value);

        assertTrue(viewModel.getCoordX0().equals(value));
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY0(value);

        assertTrue(viewModel.getCoordY0().equals(value));
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ0(value);

        assertTrue(viewModel.getCoordZ0().equals(value));
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX1(value);

        assertTrue(viewModel.getCoordX1().equals(value));
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY1(value);

        assertTrue(viewModel.getCoordY1().equals(value));
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ1(value);

        assertTrue(viewModel.getCoordZ1().equals(value));
    }

    @Test
     public void isCorrectFirstComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX0(value);

        assertTrue(Math.abs(viewModel.getCoordX0AsDouble() - value) < Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY0(value);

        assertTrue(Math.abs(viewModel.getCoordY0AsDouble() - value) < Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ0(value);

        assertTrue(Math.abs(viewModel.getCoordZ0AsDouble() - value) < Double.MIN_VALUE);
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX1(value);

        assertTrue(Math.abs(viewModel.getCoordX1AsDouble() - value) < Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY1(value);

        assertTrue(Math.abs(viewModel.getCoordY1AsDouble() - value) < Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ1(value);

        assertTrue(Math.abs(viewModel.getCoordZ1AsDouble() - value) < Double.MIN_VALUE);
    }

    private String getErrorOfNormalize() {
        String error = "";

        try {
            Vector3 vector = new Vector3(0.0, 0.0, 0.0);

            vector.normalize();
        } catch (ArithmeticException exception) {
            error = exception.getLocalizedMessage();
        }

        return error;
    }

    private void setFirstVectorInViewModel(final Vector3 vector) {
        viewModel.setCoordX0(vector.x());
        viewModel.setCoordY0(vector.y());
        viewModel.setCoordZ0(vector.z());
    }

    private void setSecondVectorInViewModel(final Vector3 vector) {
        viewModel.setCoordX1(vector.x());
        viewModel.setCoordY1(vector.y());
        viewModel.setCoordZ1(vector.z());
    }
}
