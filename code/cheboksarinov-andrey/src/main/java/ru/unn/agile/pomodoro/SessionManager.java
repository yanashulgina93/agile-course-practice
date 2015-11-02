package ru.unn.agile.pomodoro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionManager implements ActionListener {
    static final int POMODORO_MINUTE_COUNT = 25;
    static final int BREAK_MINUTE_COUNT = 5;
    static final int BIG_BREAK_MINUTE_COUNT = 30;
    private int secondCounter;
    private int minuteCounter;
    private int pomodoroCounter;

    private String status;

    private final ITimerWithListener internalTimer;

    public SessionManager(final ITimerWithListener internalTimer) {
        this.internalTimer = internalTimer;
        setStatus("Waiting");
        setTime(POMODORO_MINUTE_COUNT, 0);
    }

    public void startNewPomodoro() {
        setTime(POMODORO_MINUTE_COUNT, 0);
        setStatus("Pomodoro");
        internalTimer.addTickActionListener(this);
        internalTimer.start();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        updateSessionTime();
    }

    public int getPomodoroCount() {
        return pomodoroCounter;
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

    private void setStatus(final String inStatus) {
        status = inStatus;
    }

    private void setTime(final int minute, final int second) {
        minuteCounter = minute;
        secondCounter = second;
    }

    private void setRestTimer() {
        final int pomodorosForBigBreak = 4;
        if (isItTimeForBigBreak(pomodorosForBigBreak)) {
            setTime(BIG_BREAK_MINUTE_COUNT, 0);
            setStatus("Big break");
            internalTimer.start();
        } else {
            setTime(BREAK_MINUTE_COUNT, 0);
            setStatus("Break");
            internalTimer.start();
        }
    }

    private boolean isItTimeForBigBreak(final int pomodorosForBigBreak) {
        return pomodoroCounter % pomodorosForBigBreak == 0;
    }

    private void updateSessionTime() {
        calculateNextSecond();
        if (secondCounter >= 0) {
            return;
        }
        calculateNextMinute();
        if (minuteCounter >= 0) {
            return;
        }
        if (status == "Pomodoro") {
            internalTimer.stop();
            addOnePomodoroToday();
            setRestTimer();
            return;
        }
        setWaitStatus();
        internalTimer.stop();
    }

    private void calculateNextSecond() {
        setTime(minuteCounter, secondCounter - 1);
    }

    private void calculateNextMinute() {
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



}
