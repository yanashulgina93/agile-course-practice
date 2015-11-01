package ru.unn.agile.pomodoro;

import java.awt.event.ActionListener;

public interface ITimer {
    void start(ActionListener tickListener);

    void stop();

}
