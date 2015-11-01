package ru.unn.agile.HexBinOctCalculator.Model;

public final class HexBinOctCalculator {
    private HexBinOctCalculator() {

    }

    private static String convertResult(final Integer res, final NumeralSystem system) {
        switch (system) {
            case HEX:
                return Integer.toHexString(res);
            case BIN:
                return Integer.toBinaryString(res);
            case OCT:
                return Integer.toOctalString(res);
            default:
                return Integer.toString(res);
        }
    }

    private static int parseString(final String number, final NumeralSystem system) {
        return (int) Long.parseLong(number, system.getValue());
    }

    public static String add(final String firstNumber, final String secondNumber,
                             final NumeralSystem system) {
        int left = parseString(firstNumber, system);
        int right = parseString(secondNumber, system);
        int sum = left + right;
        return convertResult(sum, system);
    }

    public static String subtract(final String firstNumber, final String secondNumber,
                                  final NumeralSystem system) {
        int left = parseString(firstNumber, system);
        int right = parseString(secondNumber, system);
        int diff = left - right;
        return convertResult(diff, system);
    }

    public static String multiply(final String firstNumber, final String secondNumber,
                                  final NumeralSystem system) {
        int left = parseString(firstNumber, system);
        int right = parseString(secondNumber, system);
        int prod = left * right;
        return convertResult(prod, system);
    }

    public static String divide(final String firstNumber, final String secondNumber,
                                final NumeralSystem system) {
        int left = parseString(firstNumber, system);
        int right = parseString(secondNumber, system);
        if (right == 0) {
            throw new IllegalArgumentException("Division by zero!");
        } else {
            int quotient = left / right;
            return convertResult(quotient, system);
        }
    }
}
