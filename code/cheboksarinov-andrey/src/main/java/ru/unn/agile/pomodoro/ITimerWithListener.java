package ru.unn.agile.pomodoro;

import java.awt.event.ActionListener;

public interface ITimerWithListener {
    void start();

    void stop();

    void addTickActionListener(ActionListener tickListener);
}
