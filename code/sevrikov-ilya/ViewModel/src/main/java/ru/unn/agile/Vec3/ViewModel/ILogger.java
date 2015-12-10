package ru.unn.agile.Vec3.ViewModel;

import java.util.List;

public interface ILogger {
    void pushMessage(final String message);

    void pushMessage(final String format, Object ... args);

    List<String> getLog();
}
