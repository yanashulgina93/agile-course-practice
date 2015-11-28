package ru.unn.agile.TemperatureConverter.viewmodel;

public enum LogMessage {
    INCORRECT_INPUT("Input temperature value has a bad format: "),
    INPUT_EDITED("New input temperature value: "),
    NON_PHYSICAL_INPUT("Input temperature value is non-physical: ");

    private final String name;

    LogMessage(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
