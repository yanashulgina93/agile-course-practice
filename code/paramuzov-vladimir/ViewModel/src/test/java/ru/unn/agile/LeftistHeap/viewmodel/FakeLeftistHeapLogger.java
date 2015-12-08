package ru.unn.agile.LeftistHeap.viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class FakeLeftistHeapLogger implements ILeftistHeapLogger{
    private ArrayList<String> log = new ArrayList<>();

    @Override
    public void log(String message) {
        log.add("<" + getCurrentDateTime() + "> " + message);
    }

    @Override
    public List<String> getLog() {
        return log;
    }

    @Override
    public String getLogMessage(int messageIndex) {
        return log.get(messageIndex);
    }

    @Override
    public int getLogSize() {
        return log.size();
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        return dateFormat.format(Calendar.getInstance().getTime());
    }
}
