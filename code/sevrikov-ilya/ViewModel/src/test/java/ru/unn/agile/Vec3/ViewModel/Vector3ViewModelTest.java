package ru.unn.agile.Vec3.ViewModel;

import org.junit.After;
import org.junit.Before;
import ru.unn.agile.Vec3.Model.Vector3;
import org.junit.Test;

import java.util.Formatter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Vector3ViewModelTest {
    private Vector3ViewModel viewModel;

    @Before
    public void Initialize() {
        viewModel = new Vector3ViewModel(new FakeLogger());
    }

    @After
    public void Shutdown() {
        viewModel = null;
    }

    @Test
    public void canGetNormOfDefaultFirstVector() {
        viewModel.Compute(Vector3Operation.GET_NORM_FIRST_VECTOR);

        assertEquals(viewModel.getResultOfLastAction(), "0.0");
    }

    @Test
    public void canGetNormOfDefaultSecondVector() {
        viewModel.Compute(Vector3Operation.GET_NORM_SECOND_VECTOR);

        assertEquals(viewModel.getResultOfLastAction(), "0.0");
    }

    @Test
    public void cannotNormalizeOfDefaultFirstVector() {
        viewModel.Compute(Vector3Operation.NORMAlIZE_FIRST_VECTOR);

        assertEquals(viewModel.getStatus(), Vector3ViewModelStatus.FIRST_VECTOR_SMALL_NUMBERS);
    }

    @Test
    public void cannotNormalizeOfDefaultSecondVector() {
        viewModel.Compute(Vector3Operation.NORMALIZE_SECOND_VECTOR);

        assertEquals(viewModel.getStatus(), Vector3ViewModelStatus.SECOND_VECTOR_SMALL_NUMBERS);
    }

    @Test
    public void canGetNormOfSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double norm = vector.getNorm();

        setFirstVectorInViewModel(vector);

        viewModel.Compute(Vector3Operation.GET_NORM_FIRST_VECTOR);

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(norm));
    }

    @Test
    public void canGetNormOfSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        double  norm   = vector.getNorm();

        setSecondVectorInViewModel(vector);

        viewModel.Compute(Vector3Operation.GET_NORM_SECOND_VECTOR);

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(norm));
    }

    @Test
    public void canNormalizeSpecificFirstVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setFirstVectorInViewModel(vector);

        viewModel.Compute(Vector3Operation.NORMAlIZE_FIRST_VECTOR);
        vector.normalize();

        assertEquals(viewModel.getResultOfLastAction(), vector.toString());
    }

    @Test
    public void canNormalizeSpecificSecondVector() {
        Vector3 vector = new Vector3(1.0, 2.0, 3.0);

        setSecondVectorInViewModel(vector);

        viewModel.Compute(Vector3Operation.NORMALIZE_SECOND_VECTOR);
        vector.normalize();

        assertEquals(viewModel.getResultOfLastAction(), vector.toString());
    }

    @Test
    public void canCalculateDotProduct() {
        Vector3 firstVector  = new Vector3(1.0, 2.0, 3.0);
        Vector3 secondVector = new Vector3(3.0, 2.0, 1.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.Compute(Vector3Operation.CALCULATE_DOT_PRODUCT);
        double dot = firstVector.dot(secondVector);

        assertEquals(viewModel.getResultOfLastAction(), Double.toString(dot));
    }

    @Test
    public void getErrorByCalculateCrossProductWithDefaultVectors() {
        viewModel.Compute(Vector3Operation.CALCULATE_CROSS_PRODUCT);

        assertEquals(viewModel.getStatus(), Vector3ViewModelStatus.COPLANAR_VECTORS);
    }

    @Test
    public void canCalculateCrossProductFromSpecificVectors() {
        Vector3 firstVector  = new Vector3(1.0, 0.0, 0.0);
        Vector3 secondVector = new Vector3(0.0, 1.0, 0.0);

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);

        viewModel.Compute(Vector3Operation.CALCULATE_CROSS_PRODUCT);
        Vector3 crossProduct = firstVector.cross(secondVector);

        assertEquals(viewModel.getResultOfLastAction(), crossProduct.toString());
    }

    @Test
    public void isCorrectFirstComponentOfFistVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX0(value);

        assertEquals(viewModel.getCoordX0(), value);
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY0(value);

        assertEquals(viewModel.getCoordY0(), value);
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ0(value);

        assertEquals(viewModel.getCoordZ0(), value);
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordX1(value);

        assertEquals(viewModel.getCoordX1(), value);
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordY1(value);

        assertEquals(viewModel.getCoordY1(), value);
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromStringValue() {
        String value = "666.0";

        viewModel.setCoordZ1(value);

        assertEquals(viewModel.getCoordZ1(), value);
    }

    @Test
    public void isCorrectFirstComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX0(value);

        assertEquals(viewModel.getCoordX0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY0(value);

        assertEquals(viewModel.getCoordY0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfFirstVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ0(value);

        assertEquals(viewModel.getCoordZ0AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectFirstComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordX1(value);

        assertEquals(viewModel.getCoordX1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectSecondComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordY1(value);

        assertEquals(viewModel.getCoordY1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test
    public void isCorrectThirdComponentOfSecondVectorFromDoubleValue() {
        double value = 666.0;

        viewModel.setCoordZ1(value);

        assertEquals(viewModel.getCoordZ1AsDouble(), value, Double.MIN_VALUE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void viewModelConstructorThrowExceptionWithNullLogger() {
       new Vector3ViewModel(null);
    }

    @Test
    public void canCreateViewModelWithFakeLogger() {
        FakeLogger fakeLogger = new FakeLogger();
        Vector3ViewModel viewModel = new Vector3ViewModel(fakeLogger);

        assertNotNull(viewModel);
    }

    @Test
    public void viewModelCanLogGetNormOfFirstVector() {
        final Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        final Vector3Operation operation = Vector3Operation.GET_NORM_FIRST_VECTOR;

        setFirstVectorInViewModel(vector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
    }

    @Test
    public void viewModelCanLogGetNormOfSecondVector() {
        final Vector3 vector = new Vector3(4.0, 5.0, 6.0);
        final Vector3Operation operation = Vector3Operation.GET_NORM_SECOND_VECTOR;

        setSecondVectorInViewModel(vector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
    }

    @Test
    public void viewModelCanLogNormalizeFirstVector() {
        final Vector3 vector = new Vector3(1.0, 2.0, 3.0);
        final Vector3Operation operation = Vector3Operation.NORMAlIZE_FIRST_VECTOR;

        setFirstVectorInViewModel(vector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
    }

    @Test
    public void viewModelCanLogNormalizeSecondVector() {
        final Vector3 vector = new Vector3(4.0, 5.0, 6.0);
        final Vector3Operation operation = Vector3Operation.NORMALIZE_SECOND_VECTOR;

        setSecondVectorInViewModel(vector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
    }

    @Test
    public void viewModelCanLogDotProduct() {
        final Vector3 firstVector = new Vector3(1.0, 2.0, 3.0);
        final Vector3 secondVector = new Vector3(4.0, 5.0, 6.0);
        final Vector3Operation operation = Vector3Operation.CALCULATE_DOT_PRODUCT;

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
    }

    @Test
    public void viewModelCanLogCrossProduct() {
        final Vector3 firstVector = new Vector3(1.0, 0.0, 0.0);
        final Vector3 secondVector = new Vector3(0.0, 1.0, 0.0);
        final Vector3Operation operation = Vector3Operation.CALCULATE_CROSS_PRODUCT;

        setFirstVectorInViewModel(firstVector);
        setSecondVectorInViewModel(secondVector);
        viewModel.Compute(operation);

        String outputMessage = getOutputMessage(operation);

        assertEquals(viewModel.getLog().size(), 1);
        assertEquals(viewModel.getLog().get(0), outputMessage);
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

    private String getOutputMessage(final Vector3Operation operation) {
        StringBuilder stringBuilder = new StringBuilder();
        Formatter formatter = new Formatter(stringBuilder, Locale.ENGLISH);

        return formatter.format(viewModel.OUTPUT_MESSAGE_FORMAT,
                operation.toString(), viewModel.getStatus(),
                viewModel.getCoordX0(), viewModel.getCoordY0(), viewModel.getCoordZ0(),
                viewModel.getCoordX1(), viewModel.getCoordY1(), viewModel.getCoordZ1(),
                viewModel.getResultOfLastAction()).toString();
    }
}
