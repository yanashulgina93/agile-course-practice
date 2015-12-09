package ru.unn.agile.AreaConverter.viewmodel;

public enum LogMessage {
    SCALE_FROM_CHANGED("Scale 'from' changed to: "),
    SCALE_TO_CHANGED("Scale 'to' changed to: "),
    INPUT_AREA_CHANGED("Input area changed to: ");

    private final String name;

    LogMessage(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
