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

    @Test (expected = ArithmeticException.class)
    public void cannotCrossProductWithDefaultVectors() {
        Vector3 firstVector  = new Vector3(viewModel.getCoordX0AsDouble(),
                                           viewModel.getCoordY0AsDouble(),
                                           viewModel.getCoordZ0AsDouble());
        Vector3 secondVector = new Vector3(viewModel.getCoordX1AsDouble(),
                                           viewModel.getCoordY1AsDouble(),
                                           viewModel.getCoordZ1AsDouble());

        firstVector.cross(secondVector);
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
