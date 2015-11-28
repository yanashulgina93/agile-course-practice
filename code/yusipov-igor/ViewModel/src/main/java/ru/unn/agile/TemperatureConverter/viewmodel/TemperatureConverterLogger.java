package ru.unn.agile.TemperatureConverter.viewmodel;

import java.util.List;

public interface TemperatureConverterLogger {

    void log(String msg);

    List<String> getFullLog();
}
