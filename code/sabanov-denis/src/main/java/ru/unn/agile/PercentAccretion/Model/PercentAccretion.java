package ru.unn.agile.PercentAccretion.Model;

public class PercentAccretion {

    private PercentAccretion() { }

    public static double calculateSimplePercent(final double initialSum, final double percentRate, final int time) {
        return initialSum * (1 + percentRate * 0.01 * time);
    }

    public static double calculateComplexPercent(final double initialSum, final double percentRate, final int time) {
        return initialSum * Math.pow(1 + percentRate * 0.01, time);
    }
}
