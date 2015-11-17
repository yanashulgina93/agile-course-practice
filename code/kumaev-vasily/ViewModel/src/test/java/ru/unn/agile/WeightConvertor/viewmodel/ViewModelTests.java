package ru.unn.agile.WeightConvertor.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.WeightConvertor.Model.WeightUnit;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetDefaultValue() {
        assertEquals("", viewModel.valueProperty().get());
    }

    @Test
    public void canSetDefaultInputUnit() {
        assertEquals(WeightUnit.GRAM, viewModel.inputUnitProperty().get());
    }

    @Test
    public void canSetDefaultOutputUnit() {
        assertEquals(WeightUnit.KILOGRAM, viewModel.outputUnitProperty().get());
    }

    @Test
    public void canSetDefaultResult() {
        assertEquals("", viewModel.resultProperty().get());
    }

    @Test
    public void canSetDefaultStatus() {
        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsWaitingWhenConvertWithEmptyFields() {
        viewModel.valueProperty().set("");

        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenTextFieldAreCorrectFill() {
        viewModel.valueProperty().set("4");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canReportBadFormat() {
        viewModel.valueProperty().set("a");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertButtonIsDisableInitially() {
        assertTrue(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void convertButtonIsDisableWhenFormatIsBad() {
        viewModel.valueProperty().set("4");
        viewModel.valueProperty().set("a");

        assertTrue(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenFormatIsWrong() {
        viewModel.valueProperty().set("7,1");

        assertTrue(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        viewModel.valueProperty().set("4");

        assertFalse(viewModel.convertationDisableProperty().get());
    }

    @Test
    public void canSetAddUnits() {
        viewModel.inputUnitProperty().set(WeightUnit.CENTNER);
        assertEquals(WeightUnit.CENTNER, viewModel.inputUnitProperty().get());
    }

    @Test
    public void addIsDefaultUnits() {
        assertEquals(WeightUnit.KILOGRAM, viewModel.outputUnitProperty().get());
    }

    @Test
    public void convertationFromKilogramToGramHasCorrectResult() {
        viewModel.inputUnitProperty().set(WeightUnit.KILOGRAM);
        viewModel.outputUnitProperty().set(WeightUnit.GRAM);
        viewModel.valueProperty().set("1");

        viewModel.convert();

        assertEquals("1000.0", viewModel.resultProperty().get());
    }

    @Test
    public void canSetSuccessMessage() {
        viewModel.valueProperty().set("1");

        viewModel.convert();

        assertEquals(Status.SUCCESS.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.valueProperty().set("#kochan");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenSetProperData() {
        viewModel.valueProperty().set("1");

        assertEquals(Status.READY.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void convertationFromCentnerToGramHasCorrectResult() {
        viewModel.inputUnitProperty().set(WeightUnit.CENTNER);
        viewModel.outputUnitProperty().set(WeightUnit.GRAM);
        viewModel.valueProperty().set("0.01");

        viewModel.convert();

        assertEquals("1000.0", viewModel.resultProperty().get());
    }

    @Test
    public void convertationFromPoundToKilogramHasCorrectResult() {
        viewModel.inputUnitProperty().set(WeightUnit.POUND);
        viewModel.outputUnitProperty().set(WeightUnit.KILOGRAM);
        viewModel.valueProperty().set("2.2");

        viewModel.convert();

        assertEquals("1.0", viewModel.resultProperty().get());
    }

}
