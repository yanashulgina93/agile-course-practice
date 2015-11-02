package ru.unn.agile.pomodoro;

import javax.swing.*;
import java.awt.event.ActionListener;

public class TimerInApp implements ITimer {
    private javax.swing.Timer swingTimer;
    private ActionListener tickListener;
    @Override
    public void start() {
        final int oneSecond = 1000;
        swingTimer = new Timer(oneSecond, tickListener);
        swingTimer.start();
    }

    @Override
    public void stop() {
        swingTimer.stop();
    }

    @Override
    public void addTickActionListener(final ActionListener tickListener) {
        this.tickListener = tickListener;
    }
}
