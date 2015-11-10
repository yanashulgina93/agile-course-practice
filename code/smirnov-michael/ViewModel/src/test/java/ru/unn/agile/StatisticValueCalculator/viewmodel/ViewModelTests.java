package ru.unn.agile.StatisticValueCalculator.viewmodel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @Test
    public void descriptionOfCalculatedStatisticIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getDescriptionOfCalculatedStatistic(), "");
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
    public void descriptionOfAddEventErrorIsEmptyWhenJustStarted() {
        assertEquals(viewModel.getErrorOfAddEvent(), "");
    }

    @Test
    public void selectedStatisticIsEqualToUsersChoice() {
        StatisticNames selectedStatistic = StatisticNames.CENTRAL_MOMENT;

        viewModel.setSelectedStatistic(selectedStatistic);

        assertEquals(viewModel.getSelectedStatistic(), selectedStatistic);
    }
    
    @Test
    public void eventAddFieldIsHidedWhenSelectedStatisticIsNotProbability() {
        viewModel.setSelectedStatistic(StatisticNames.ENUMERATION);
        assertFalse(viewModel.isEventAddFieldVisible());
    }

    @Test
    public void selectedStatisticIsEnumerationWhenJustStarted() {
        assertEquals(viewModel.getSelectedStatistic(), StatisticNames.ENUMERATION);
    }

    @Test
    public void rowAddValueIsEqualToOneWhenJustStarted() {
        assertEquals(viewModel.getRowAddValue(), "1.0");
    }
}
