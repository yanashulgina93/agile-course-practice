package ru.unn.agile.pomodoro;

import java.awt.event.ActionListener;

public class MockTimer implements ITimer {
    private ActionListener tickListener;

    @Override
    public void start(final ActionListener tickListener) {
        this.tickListener = tickListener;
    }

    @Override
    public void stop() {
        final int mock = 1;
    }

    public void throwTicks(final int tickNumber) {
        for (int i = 0; i < tickNumber; i++) {
            tickListener.actionPerformed(null);
        }
    }
}

