package ru.unn.agile.pomodoro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionManager implements ActionListener {
    static final int POMODORO_MINUTE = 25;
    static final int POMODORO_SECOND = 0;
    static final int BREAK_MINUTE = 5;
    static final int BREAK_SECOND = 0;
    static final int BIG_BREAK_MINUTE = 30;
    static final int BIG_BREAK_SECOND = 0;
    private int secondCounter;
    private int minuteCounter;
    private int pomodoroCounter;

    private String status;

    private final ITimer internalTimer;

    public SessionManager(final ITimer internalTimer) {
        this.internalTimer = internalTimer;
        setStatus("Waiting");
        setTime(POMODORO_MINUTE, POMODORO_SECOND);
    }

    public void startNewPomodoro() {
        setTime(POMODORO_MINUTE, POMODORO_SECOND);
        setStatus("Pomodoro");
        internalTimer.start(this);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        tick();
    }

    private void setStatus(final String inStatus) {
        status = inStatus;
    }

    private void setTime(final int minute, final int second) {
        minuteCounter = minute;
        secondCounter = second;
    }

    public int getSecond() {
        return secondCounter;
    }

    public int getMinute() {
        return minuteCounter;
    }

    public String getStatus() {
        return status;
    }
    private void setRestTimer() {
        final int pomodorosForBigBreak = 4;
        if (pomodoroCounter % pomodorosForBigBreak == 0) {
            setTime(BIG_BREAK_MINUTE, BIG_BREAK_SECOND);
            setStatus("Big break");
            internalTimer.start(this);
        } else {
            setTime(BREAK_MINUTE, BREAK_SECOND);
            setStatus("Break");
            internalTimer.start(this);
        }
    }

    private void tick() {
        calculateNextSecond();
        if (secondCounter >= 0) {
            return;
        }
        setNextMinute();
        if (minuteCounter >= 0) {
            return;
        }
        if (status == "Pomodoro") {
            stopTimer();
            addOnePomodoroToday();
            setRestTimer();
            return;
        }
        setWaitStatus();
        stopTimer();
    }

    private void stopTimer() {
        internalTimer.stop();
    }

    private void calculateNextSecond() {
        setTime(minuteCounter, secondCounter - 1);
    }

    private void setNextMinute() {
        secondCounter = 0;
        final int secondWhenNewMinuteStarted = 59;
        setTime(minuteCounter - 1, secondWhenNewMinuteStarted);
    }

    private void setWaitStatus() {
        setTime(0, 0);
        setStatus("Waiting");
    }

    private void addOnePomodoroToday() {
        pomodoroCounter += 1;
    }


    public int getPomodoroCount() {
        return pomodoroCounter;
    }
}
