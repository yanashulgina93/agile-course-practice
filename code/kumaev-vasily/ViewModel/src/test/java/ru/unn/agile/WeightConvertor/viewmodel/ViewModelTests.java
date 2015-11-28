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
    public void statusIsWaitingWhenConvertWithEmptyField() {
        viewModel.valueProperty().set("");

        viewModel.convert();

        assertEquals(Status.WAITING.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void statusIsReadyWhenTextFieldIsCorrectFill() {
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
        assertTrue(viewModel.conversionDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisableWhenWeChangeInputValueFromValidToBad() {
        viewModel.valueProperty().set("4");
        viewModel.valueProperty().set("a");

        assertTrue(viewModel.conversionDisabledProperty().get());
    }

    @Test
    public void convertButtonIsDisabledWhenInputValueHasCommaInsteadOfDot() {
        viewModel.valueProperty().set("7,1");

        assertTrue(viewModel.getConversionDisabled());
    }

    @Test
    public void convertButtonIsEnabledWithCorrectInput() {
        viewModel.valueProperty().set("4");

        assertFalse(viewModel.conversionDisabledProperty().get());
    }

    @Test
    public void canSetInputUnit() {
        viewModel.inputUnitProperty().set(WeightUnit.CENTNER);

        assertEquals(WeightUnit.CENTNER, viewModel.inputUnitProperty().get());
    }

    @Test
    public void outputUnitIsDefaultUnit() {
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
    public void canSetBadFormatMessageWhenNumberIsNegative() {
        viewModel.valueProperty().set("-1");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.statusProperty().get());
    }

    @Test
    public void canSetBadFormatMessage() {
        viewModel.valueProperty().set("#kochan");

        assertEquals(Status.BAD_FORMAT.toString(), viewModel.getStatus());
    }

    @Test
    public void isGetUnitsEqualsToUnitsPropertyGet() {
        assertEquals(viewModel.unitsProperty().get(), viewModel.getUnits());
    }

    @Test
    public void convertationFromCentnerToGramHasCorrectResult() {
        viewModel.inputUnitProperty().set(WeightUnit.CENTNER);
        viewModel.outputUnitProperty().set(WeightUnit.GRAM);
        viewModel.valueProperty().set("0.01");

        viewModel.convert();

        assertEquals("1000.0", viewModel.getResult());
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
