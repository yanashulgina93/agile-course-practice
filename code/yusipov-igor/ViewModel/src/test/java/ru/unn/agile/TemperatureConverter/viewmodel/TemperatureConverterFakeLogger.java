package ru.unn.agile.TemperatureConverter.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class TemperatureConverterFakeLogger implements TemperatureConverterLogger {

    private ArrayList<String> log = new ArrayList<String>();

    @Override
    public void log(final String s) {
        log.add(s);
    }

    @Override
    public List<String> getFullLog() {
        return log;
    }
}
