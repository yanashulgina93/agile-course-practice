package ru.unn.agile.TemperatureConverter.viewmodel;

public enum Status {
    WAITING("Enter temperature to convert"),
    READY("Press 'Convert'"),
    BAD_FORMAT("Bad input format"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
