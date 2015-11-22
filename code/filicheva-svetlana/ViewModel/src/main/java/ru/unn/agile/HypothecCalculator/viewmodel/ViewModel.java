package ru.unn.agile.HypothecCalculator.viewmodel;


public class ViewModel {



    public enum CurrencyType {
        DOLLARS("$"),
        EURO("€"),
        RUBLE("руб.");

        private final String name;

        CurrencyType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum PeriodType {
        MONTH("месяцев"),
        YEAR("лет");

        private final String name;

        PeriodType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum InterestRateType {
        MONTHLY("% ежемесячно"),
        YEARLY("% ежегодно");

        private final String name;

        InterestRateType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum FlatFeeType {
        PERCENT("% от суммы кредита"),
        CONSTANT_SUM("фиксированная сумма");

        private final String name;

        FlatFeeType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum MonthlyFeeType {
        CREDIT_BALANCE_PERCENT("% от остатка долга"),
        CREDIT_SUM_PERCENT("% от суммы кредита"),
        CONSTANT_SUM("фиксированная сумма");

        private final String name;

        MonthlyFeeType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public enum CreditType {
        DIFFERENTIATED("дифференцированный"),
        ANNUITY("аннуитетный");

        private final String name;

        CreditType(final String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }
}
