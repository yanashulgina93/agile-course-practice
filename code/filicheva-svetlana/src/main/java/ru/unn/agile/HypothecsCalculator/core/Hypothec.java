package ru.unn.agile.HypothecsCalculator.core;

public class Hypothec {
    private final double creditSum;
    private final int creditPeriod;

    public double computeMonthlyPayment() {
        return creditSum/creditPeriod;
    }

    public static class Builder {

        private final double creditSum;
        private final int creditPeriod;

        public Builder(final double creditSum, final int creditPeriod) {

            if (creditSum < 0) {
                throw new IllegalArgumentException("Negative credit sum");
            }
            if (creditPeriod <= 0) {
                throw new IllegalArgumentException("Not positive credit period");
            }

            this.creditSum = creditSum;
            this.creditPeriod = creditPeriod;
        }

        public Hypothec build() {
            return new Hypothec(this);
        }

    }
    private Hypothec(Builder builder) {
        this.creditSum = builder.creditSum;
        this.creditPeriod = builder.creditPeriod;
    }
}
