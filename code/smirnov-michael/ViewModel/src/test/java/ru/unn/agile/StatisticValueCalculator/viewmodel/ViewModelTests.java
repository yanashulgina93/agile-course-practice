package ru.unn.agile.StatisticValueCalculator.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void descriptionOfCalculatedStatisticIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getNameOfCalculatedStatistic(), "");
    }

    @Test
    public void valueOfCalculatedStatisticIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getValueOfCalculatedStatistic(), "");
    }

    @Test
    public void descriptionOfAddRowErrorIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getErrorOfAddRow(), "");
    }

    @Test
    public void descriptionOfAddStatisticParameterErrorIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getErrorOfAddStatisticParameter(), "");
    }

    @Test
    public void selectedStatisticIsEqualToUsersChoice() {
        StatisticInfo selectedStatistic = StatisticInfo.CENTRAL_MOMENT;

        viewModel.setSelectedStatistic(selectedStatistic);

        assertEquals(viewModel.getSelectedStatistic(), selectedStatistic);
    }
    
    @Test
    public void statisticParameterFieldIsHidedWhenSelectedStatisticIsNotProbability() {
        viewModel.setSelectedStatistic(StatisticInfo.ENUMERATION);
        assertFalse(viewModel.getStatisticParameterAddFieldVisible());
    }

    @Test
    public void statisticParameterFieldIsShowedWhenProbabilitySelected() {
        viewModel.setSelectedStatistic(StatisticInfo.PROBABILITY);
        assertTrue(viewModel.getStatisticParameterAddFieldVisible());
    }
    @Test
    public void selectedStatisticIsEnumerationWhenJustStarted() {
        assertEquals(viewModel.getSelectedStatistic(), StatisticInfo.ENUMERATION);
    }

    @Test
    public void rowAddValueIsEqualToOneWhenJustStarted() {
        assertEquals(viewModel.getRowAddValue(), "1.0");
    }

    @Test
    public void statisticParameterAddValueIsEqualToZeroWhenJustStarted() {
        assertEquals(viewModel.getStatisticParameterAddValue(), "0.0");
    }

    @Test
    public void addRowErrorIsHidedWhenAddRowFieldContainsNumber() {
        viewModel.setRowAddValue("225");

        assertEquals(viewModel.getErrorOfAddRow(), "");
    }
    @Test
    public void addRowErrorIsShownWhenAddRowFieldContainsNotNumber() {
        viewModel.setRowAddValue("abc123");

        assertEquals(viewModel.getErrorOfAddRow(), "The adding value must be a number");
    }
    @Test
    public void addRowErrorDisappearWhenResetNumberToAddRowField() {
        viewModel.setRowAddValue("abc123");
        viewModel.setRowAddValue("123");

        assertEquals(viewModel.getErrorOfAddRow(), "");
    }

    @Test
    public void addStatisticParameterErrorIsHidedWhenAddRowFieldContainsNumber() {
        viewModel.setStatisticParameterAddValue("111");

        assertEquals(viewModel.getErrorOfAddStatisticParameter(), "");
    }

    @Test
    public void addStatisticParameterErrorIsShownWhenAddRowFieldContainsNotNumber() {
        viewModel.setStatisticParameterAddValue("abc123");

        assertEquals(viewModel.getErrorOfAddStatisticParameter(),
                "The adding value must be a number");
    }
    @Test
    public void addStatisticParameterErrorDisappearWhenResetNumberToAddRowField() {
        viewModel.setStatisticParameterAddValue("abc123");
        viewModel.setStatisticParameterAddValue("123");

        assertEquals(viewModel.getErrorOfAddStatisticParameter(), "");
    }
}
