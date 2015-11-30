package ru.unn.agile.PercentAccretion.viewmodel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercentAccretionViewModelTest {

    private PercentAccretionViewModel viewModel;

    @Before
    public void initialize() {
        viewModel = new PercentAccretionViewModel();
    }

    @Test
    public void byDefaultCalculateBtnIsDisabled() {
        assertFalse(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenFieldsAreEmptyShowErrorMessage() {
        assertEquals("Please fill fields!", viewModel.getErrorMessage());
    }

    @Test
    public void whenFillAllFieldsCalculateBtnIsEnabled() {
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setInitialSum("1");
        assertTrue(viewModel.isCalculateButtonEnabled());
    }

    @Test
    public void whenFillAllFieldsClearErrorMessage() {
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setInitialSum("1");
        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void whenOneOfFieldsIsFilledIncorrectShowErrorMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("a");
        assertEquals("Please enter correct values!", viewModel.getErrorMessage());
    }

    @Test
    public void whenCorrectWrongValueOfFieldClearErrorMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("a");
        viewModel.setCountOfYears("1");
        assertEquals("", viewModel.getErrorMessage());
    }

    @Test
    public void whenOneOfFieldsIsClearedShowErrorMessage() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("1");
        viewModel.setCountOfYears("1");
        viewModel.setCountOfYears("");
        assertEquals("Please fill fields!", viewModel.getErrorMessage());
    }

    @Test
    public void whenCalculateSimplePercentSumShowResult() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType("simple");
        viewModel.calculateResultSum();
        String expectedValue = "3.0";
        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenCalculateComplexPercentSumShowResult() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType("complex");
        viewModel.calculateResultSum();
        String expectedValue = "4.0";
        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenSumIsCalculatedAndOneOfFieldsIsChangedClearSum() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType("simple");
        viewModel.calculateResultSum();
        viewModel.setInitialSum("2");
        String expectedValue = "";
        assertEquals(expectedValue, viewModel.getResultSum());
    }

    @Test
    public void whenSumIsCalculatedAndPercentTypeIsChangedClearSum() {
        viewModel.setInitialSum("1");
        viewModel.setPercentRate("100");
        viewModel.setCountOfYears("2");
        viewModel.setPercentType("simple");
        viewModel.calculateResultSum();
        viewModel.setPercentType("complex");
        String expectedValue = "";
        assertEquals(expectedValue, viewModel.getResultSum());
    }

}
