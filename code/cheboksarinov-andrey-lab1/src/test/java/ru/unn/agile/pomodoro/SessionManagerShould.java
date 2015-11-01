package ru.unn.agile.pomodoro;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionManagerShould {
    private SessionManager sessionManager;
    private MockTimer mockTimer;

    @Before
    public void setUp() {
        mockTimer = new MockTimer();
        sessionManager = new SessionManager(mockTimer);
    }

    @Test
    public void decreaseSecondByOneIfPomodoroStartAndOneSecondLeft() {
        sessionManager.startNewPomodoro();

       mockTimer.throwTicks(1);

        assertEquals(59, sessionManager.getSecond());
    }

    @Test
    public void decreaseMinuteByOneIfPomodoroStartAndOneMinuteLeft() {
        sessionManager.startNewPomodoro();

        tickOneMinute();

        assertEquals(24, sessionManager.getMinute());
    }

    @Test
    public void changeStatusToBreakWhenPomodoroIsOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals("Break", sessionManager.getStatus());
    }

    @Test
    public void secondsSetOnZeroWhenPomodoroIsOverAndNotTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals(0, sessionManager.getSecond());
    }

    @Test
    public void minutesSetOnFiveWhenPomodoroIsOverAndNotTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals(5, sessionManager.getMinute());
    }

    @Test
    public void addOneCompletedPomodoroWhenItIsOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();

        assertEquals(1, sessionManager.getPomodoroCount());
    }

    @Test
    public void changeStatusToWaitingWhenPomodoroAndRestAreOver() {
        sessionManager.startNewPomodoro();

        completePomodoro();
        completeRest();

        assertEquals("Waiting", sessionManager.getStatus());
    }

    @Test
    public void secondsSetOnZeroWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completeFourPomodoros();

        assertEquals(0, sessionManager.getSecond());
    }

    @Test
    public void minuteSetOnThirtyWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completeFourPomodoros();

        assertEquals(30, sessionManager.getMinute());
    }

    @Test
    public void statusSetOnBigBreakWhenPomodoroIsOverAndTimeForBigBreak() {
        sessionManager.startNewPomodoro();

        completeFourPomodoros();

        assertEquals("Big break", sessionManager.getStatus());
    }

    private void completeFourPomodoros() {
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
        completeRest();
        sessionManager.startNewPomodoro();
        completePomodoro();
    }

    private void completeRest() {
        mockTimer.throwTicks(60 * 5 + 1);
    }


    private void completePomodoro() {
        mockTimer.throwTicks(60 * 25 + 1);
    }

    private void tickOneMinute() {
        mockTimer.throwTicks(60);
    }
}
