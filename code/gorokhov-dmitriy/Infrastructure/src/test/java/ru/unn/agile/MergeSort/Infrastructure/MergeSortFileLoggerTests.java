package ru.unn.agile.MergeSort.Infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ru.unn.agile.MergeSort.ViewModel.MergeSortRegexMatcher.matches;

public class MergeSortFileLoggerTests {
    @Before
    public void setUp() {
        fileLogger = new MergeSortFileLogger(OUTPUT_FILE_NAME);
    }

    @Test
    public void canCreateFileWithOutputFileName() {
        assertTrue(new File(OUTPUT_FILE_NAME).isFile());
    }

    @Test
    public void canCreateFileLoggerWithOutputFileName() {
        assertNotNull(fileLogger);
    }

    @Test
    public void canWriteRecordWithFileLogger() {
        fileLogger.writeRecord("Sample");

        assertEquals(1, fileLogger.getRecordsCount());
    }

    @Test
    public void canReadRecordWithFileLogger() {
        fileLogger.writeRecord("Sample");

        assertThat(fileLogger.readRecord(0), matches(".*" + "Sample" + ".*"));
    }

    @Test
    public void canWriteSeveralRecordsWithFileLogger() {
        fileLogger.writeRecord("Sample1");
        fileLogger.writeRecord("Sample2");
        fileLogger.writeRecord("Sample3");

        assertEquals(3, fileLogger.getRecordsCount());
    }

    @Test
    public void canReadRecordsListFromFileLogger() {
        fileLogger.writeRecord("Sample1");
        fileLogger.writeRecord("Sample2");

        assertEquals(2, fileLogger.getRecordsList().size());
    }

    @Test
    public void isReadRecordReturnNullWhenRecordNumberIsOutOfBounds() {
        fileLogger.writeRecord("Sample1");
        fileLogger.writeRecord("Sample2");

        assertNull(fileLogger.readRecord(2));
    }

    @Test
    public void isSecondRecordMarkedWithCorrectNumber() {
        fileLogger.writeRecord("Sample1");
        fileLogger.writeRecord("Sample2");

        assertThat(fileLogger.readRecord(1), matches(".*" + "[1]" + ".*" + "Sample" + ".*"));
    }

    private static final String OUTPUT_FILE_NAME = "./MergeSortFileLoggerTests.log";
    private MergeSortFileLogger fileLogger;
}



