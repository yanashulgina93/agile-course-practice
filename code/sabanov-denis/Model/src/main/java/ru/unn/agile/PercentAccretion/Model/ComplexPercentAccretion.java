package ru.unn.agile.PercentAccretion.Model;

public class ComplexPercentAccretion extends PercentAccretion{

    @Override
    public double calculate(PercentData data) {
        if (data.getInitialSum() >= 0 && data.getPercentRate() >= 0
                && data.getCountOfYears() >= 0) {
            return data.getInitialSum() * Math.pow(1 + data.getPercentRate()
                    * PercentData.FROM_PERCENT, data.getCountOfYears());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
