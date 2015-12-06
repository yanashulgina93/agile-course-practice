package ru.unn.agile.LeftistHeap.viewmodel;

import java.util.ArrayList;
import java.util.List;

public class FakeLeftistHeapLogger implements ILeftistHeapLogger{
    private ArrayList<String> log = new ArrayList<>();

    @Override
    public void addMessage(String message) {
        log.add(message);
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
    public int getSize() {
        return log.size();
    }
}
