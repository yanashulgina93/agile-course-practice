package ru.unn.agile.IntegrationMethods.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class TxtNumericalIntegrationLoggerTests {
    private TxtNumericalIntegrationLogger txtLogger;
    private final String NAME_OF_LOG_FILE = "./NumericalIntegrationLog.log";

    @Before
    public void setUp() {
        txtLogger = new TxtNumericalIntegrationLogger(NAME_OF_LOG_FILE);
    }

    @Test
    public void canFindLogFile() {
        try {
            new BufferedReader(new FileReader(NAME_OF_LOG_FILE));
        } catch (FileNotFoundException e) {
            fail("Error: log file wasn't found");
        }
    }

    @Test
    public void canWriteSomethingToLogFile() {
        String recordForAdding = "some action";

        txtLogger.addRecord(recordForAdding);
        String recordFromLogFile = txtLogger.getAllRecords().get(0);

        assertTrue(recordFromLogFile.contains(recordForAdding));
    }

    @Test
    public void canWriteSeveralLogMessage() {
        String[] recordsForAdding = {"the first action", "the second action", "the third action"};
        boolean areRecordsLogged = true;

        for(int i = 0; i < recordsForAdding.length; i++) {
            txtLogger.addRecord(recordsForAdding[i]);
        }
        List<String> recordsFromLogFile = txtLogger.getAllRecords();
        for (int i = 0; i < recordsFromLogFile.size(); i++) {
            if(!recordsFromLogFile.get(i).contains(recordsForAdding[i]))
                areRecordsLogged = false;
        }

        assertTrue(areRecordsLogged);
    }

    @Test
    public void canSaveDateAndTimeOfAction() {
        String recordForAdding = "some action";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        String dateAndTime = dateFormat.format(cal.getTime());

        txtLogger.addRecord(recordForAdding);
        String recordFromLogFile = txtLogger.getAllRecords().get(0);

        assertTrue(recordFromLogFile.contains(dateAndTime));
    }
}
