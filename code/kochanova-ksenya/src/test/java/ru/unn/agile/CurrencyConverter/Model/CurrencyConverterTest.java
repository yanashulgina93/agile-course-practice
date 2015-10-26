package ru.unn.agile.CurrencyConverter.Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ksenyako on 26.10.2015.
 */
public class CurrencyConverterTest {
    private CurrencyConverter converter = new CurrencyConverter();

    @Test
    public void canConvertNullToNull() {
        Currency expectedCurrency = new Currency(0, Unit.Dollar);
        Currency currencyBeforeConvert = new Currency(0, Unit.Dollar);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Dollar);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void cannotConvertNegativeValue() {
        boolean thrown = false;
        Currency currencyBeforeConvert = new Currency(-100, Unit.Dollar);
        try {
            Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                    Unit.Dollar);
        } catch (IllegalArgumentException e) {
            System.out.println("Please, use positive value.");
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void canConvertRubleToDollar() {
        Currency expectedCurrency = new Currency(1.6, Unit.Dollar);
        Currency currencyBeforeConvert = new Currency(100, Unit.Ruble);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Dollar);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertRubleToEuro() {
        Currency expectedCurrency = new Currency(1.38, Unit.Euro);
        Currency currencyBeforeConvert = new Currency(100, Unit.Ruble);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Euro);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertRubleToPound() {
        Currency expectedCurrency = new Currency(1.01, Unit.Pound);
        Currency currencyBeforeConvert = new Currency(100, Unit.Ruble);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Pound);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertDollarToEuro() {
        Currency expectedCurrency = new Currency(86.21, Unit.Euro);
        Currency currencyBeforeConvert = new Currency(100, Unit.Dollar);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Euro);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertDollarToPound() {
        Currency expectedCurrency = new Currency(62.89, Unit.Pound);
        Currency currencyBeforeConvert = new Currency(100, Unit.Dollar);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Pound);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertDollarToRuble() {
        Currency expectedCurrency = new Currency(6250, Unit.Ruble);
        Currency currencyBeforeConvert = new Currency(100, Unit.Dollar);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Ruble);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertEuroToRuble() {
        Currency expectedCurrency = new Currency(7250, Unit.Ruble);
        Currency currencyBeforeConvert = new Currency(100, Unit.Euro);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Ruble);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertEuroToDollar() {
        Currency expectedCurrency = new Currency(116, Unit.Dollar);
        Currency currencyBeforeConvert = new Currency(100, Unit.Euro);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Dollar);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertEuroToPound() {
        Currency expectedCurrency = new Currency(72.96, Unit.Pound);
        Currency currencyBeforeConvert = new Currency(100, Unit.Euro);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Pound);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertPoundToDollar() {
        Currency expectedCurrency = new Currency(159, Unit.Dollar);
        Currency currencyBeforeConvert = new Currency(100, Unit.Pound);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Dollar);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertPoundToEuro() {
        Currency expectedCurrency = new Currency(137.07, Unit.Euro);
        Currency currencyBeforeConvert = new Currency(100, Unit.Pound);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Euro);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }
    @Test
    public void canConvertPoundToRuble() {
        Currency expectedCurrency = new Currency(9937.5, Unit.Ruble);
        Currency currencyBeforeConvert = new Currency(100, Unit.Pound);
        Currency currencyAfterConvert = converter.convertCurrency(currencyBeforeConvert,
                Unit.Ruble);
        assertEquals(expectedCurrency, currencyAfterConvert);
    }


}
