package ru.unn.agile.TemperatureConverter.Model;

public class TemperatureConverter {

        public TemperatureConverter(final double base, final double scale) {
            this.base = base;
            this.scale = scale;
        }

        public double convert(final double temperatureInCelsius) {
            double temperatureInOtherScale = scale * temperatureInCelsius + base;

            if (temperatureInOtherScale >= Double.MAX_VALUE) {
                throw new IllegalArgumentException();
            }

            return temperatureInOtherScale;
        }

        private final double base;
        private final double scale;
}
