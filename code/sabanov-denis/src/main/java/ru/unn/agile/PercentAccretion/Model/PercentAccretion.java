package ru.unn.agile.PercentAccretion.Model;

public class PercentAccretion {

    private PercentAccretion() { }

    public static double calculateSumWithSimplePercent(final double initialSum,
                                                       final double percentRate,
                                                       final int countOfYears) {
        if((initialSum > 0) && (percentRate > 0) && (countOfYears > 0)) {
            return initialSum * (1 + percentRate * 0.01 * countOfYears);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithComplexPercent(final double initialSum,
                                                        final double percentRate,
                                                        final int countOfYears) {
        if((initialSum > 0) && (percentRate > 0) && (countOfYears > 0)) {
            return initialSum * Math.pow(1 + percentRate * 0.01, countOfYears);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithNominalPercentRate(final double initialSum,
                                                            final double percentRate,
                                                            final int percentPayingPerYear,
                                                            final int countOfYears) {
        if((initialSum > 0) && (percentRate > 0) && (countOfYears > 0) && (percentPayingPerYear > 0)) {
            return initialSum * Math.pow(1 + percentRate * 0.01 / percentPayingPerYear,
                    countOfYears * percentPayingPerYear);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateEffectivePercentRate(final double percentRate,
                                                       final int percentPayingPerYear) {
        if((percentRate > 0) && (percentPayingPerYear > 0)) {
            return 100 * (Math.pow(1 + (percentRate * 0.01) / percentPayingPerYear, percentPayingPerYear) - 1);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double calculateSumWithEffectivePercentRate(final double initialSum,
                                                            final double percentRate,
                                                            final int percentPayingPerYear,
                                                            final int countOfYears) {
        if((initialSum > 0) && (percentRate > 0) && (countOfYears > 0) && (percentPayingPerYear > 0)) {
            return initialSum * Math.pow(1 + 0.01 * calculateEffectivePercentRate(percentRate, percentPayingPerYear),
                    countOfYears);
        } else {
            throw new IllegalArgumentException();
        }
    }
}