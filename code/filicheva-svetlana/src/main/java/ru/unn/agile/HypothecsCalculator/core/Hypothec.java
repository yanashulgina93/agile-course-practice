package ru.unn.agile.HypothecsCalculator.core;

public class Hypothec {
    private final double creditSum;
    private final int creditPeriod;

    public double computeMonthlyPayment() {
        return creditSum/creditPeriod;
    }

    public static class Builder {

        private final double houseCost;
        private final int creditPeriod;

        private double downPayment = 0.0;

        public Builder(final double houseCost, final int creditPeriod) {

            if (houseCost < 0) {
                throw new IllegalArgumentException("Negative house cost");
            }
            if (creditPeriod <= 0) {
                throw new IllegalArgumentException("Not positive credit period");
            }

            this.houseCost = houseCost;
            this.creditPeriod = creditPeriod;
        }

        public Builder setDownPayment(double downPayment) {
            if (downPayment < 0) {
                throw new IllegalArgumentException("Not positive down payment");
            }
            if (downPayment > houseCost) {
                throw new IllegalArgumentException("Down payment is more then house cost");
            }
            this.downPayment = downPayment;
            return this;
        }

        public Hypothec build() {
            return new Hypothec(this);
        }

    }
    private Hypothec(Builder builder) {
        this.creditSum = builder.houseCost - builder.downPayment;
        this.creditPeriod = builder.creditPeriod;
    }
}
