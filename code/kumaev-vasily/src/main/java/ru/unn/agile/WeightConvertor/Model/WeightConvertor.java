package ru.unn.agile.WeightConvertor.Model;

public class WeightConvertor {

    public Weight convert(final Weight weight, final WeightUnit unitOutput) {

        Weight outputWeight = new Weight(0.0, WeightUnit.KILOGRAM);
        double valueInputWeight = weight.getValue();

        if (valueInputWeight >= 0
                && valueInputWeight <= Double.MAX_VALUE) {

            double valueOutputWeight = unitOutput.getMultiplier()
                    / weight.getUnit().getMultiplier() * valueInputWeight;
            outputWeight.setValue(valueOutputWeight);
            outputWeight.setUnit(unitOutput);
        } else {
            throw new IllegalArgumentException();
        }
        return outputWeight;
    }
}
