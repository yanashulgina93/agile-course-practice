package ru.unn.agile.CurrencyConverter.Model;

/**
 * Created by ksenyako on 26.10.2015.
 */
public class CurrencyConverter {
        static final double DOLLAR = 1;
        static final double EURO = 1.16;
        static final double RUBLE = 0.016;
        static final double POUND = 1.59;

        public Currency convertCurrency(final Currency inputCurrency, final Unit unitOutputCurrency)
        {
            double[] someCurrencyToDollar = {DOLLAR, EURO, RUBLE, POUND};
            Currency outputCurrency = new Currency(0, Unit.Dollar);
            AccuracyCorrector valueAfterRound = new AccuracyCorrector();

            int indexOfInputUnit = inputCurrency.getUnit().ordinal();
            int indexOfOutputUnit = unitOutputCurrency.ordinal();

            if (inputCurrency.getValue() < 0) {
                throw new IllegalArgumentException("Please, use positive value.");
            }

            double outputCurrencyBeforeRound = someCurrencyToDollar[indexOfInputUnit]
                    * inputCurrency.getValue() / someCurrencyToDollar[indexOfOutputUnit];
            double valueOutputCurrencyAfterRound = valueAfterRound.rounding(
                    outputCurrencyBeforeRound);

            outputCurrency.setValue(valueOutputCurrencyAfterRound);
            outputCurrency.setUnit(unitOutputCurrency);
            return outputCurrency;

        }

    }
