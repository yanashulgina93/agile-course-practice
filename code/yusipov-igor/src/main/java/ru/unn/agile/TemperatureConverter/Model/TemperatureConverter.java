package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

        private double base;
        private double scale;

        public TemperatureConverter(double base, double scale) {
            this.base = base;
            this.scale = scale;
        }

        public double convert(double temperatureInCelsius){

            double temperatureInOtherScale = scale * temperatureInCelsius + base;
            if (temperatureInOtherScale >= Double.MAX_VALUE) {
                throw new IllegalArgumentException();
            }

            return temperatureInOtherScale;
        }
}
