package ru.unn.agile.LeftistHeap.viewmodel;

import java.util.List;

public interface ILeftistHeapLogger {
    void addMessage(final String message);

    List<String> getLog();

    String getLogMessage(final int messageIndex);

    int getSize();
}
