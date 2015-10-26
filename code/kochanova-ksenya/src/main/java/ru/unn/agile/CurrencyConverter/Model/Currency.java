package ru.unn.agile.CurrencyConverter.Model;

/**
 * Created by ksenyako on 25.10.2015.
 */
enum Unit { Dollar, Euro, Ruble, Pound }
public class Currency {
    private double value;
    private Unit unit;

    public Currency(final double value, final Unit unit) {
        this.value = value;
        this.unit = unit;
    }
    public int hashCode() {
        final int shift = 32;

        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> shift));
    }
    public boolean equals(final Object object) {
        Currency cur = (Currency) object;
        return cur.value == value
                && cur.unit == unit;
    }
    public void setValue(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }

}
