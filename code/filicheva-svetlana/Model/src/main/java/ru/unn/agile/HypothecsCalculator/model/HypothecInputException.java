package ru.unn.agile.HypothecsCalculator.model;

class HypothecInputException extends Exception {
    private String message;

    public HypothecInputException() { }

    public HypothecInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
