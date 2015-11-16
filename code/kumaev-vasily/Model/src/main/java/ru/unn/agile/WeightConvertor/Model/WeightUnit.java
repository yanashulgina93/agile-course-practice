package ru.unn.agile.WeightConvertor.Model;

public enum WeightUnit {
    OUNCE(35.27),
    POUND(2.20),
    STONE(0.15),
    GRAM(1000.0),
    KILOGRAM(1.0),
    CENTNER(0.01),
    TON(0.001);

    private final double multiplierKGram;

    WeightUnit(final double multiplierKGram) {
        this.multiplierKGram = multiplierKGram;
    }

    public double getMultiplier() {
        return multiplierKGram;
    }

    public String convert(final String value,
                          final WeightUnit unitInput, final WeightUnit unitOutput) {
        Weight weightInput = new Weight(Double.parseDouble(value), unitInput);
        WeightConvertor weightconv = new WeightConvertor();
        Weight weightOutput = weightconv.convert(weightInput, unitOutput);
        double val = weightOutput.getValue();
        return Double.toString(val);
    }
}
