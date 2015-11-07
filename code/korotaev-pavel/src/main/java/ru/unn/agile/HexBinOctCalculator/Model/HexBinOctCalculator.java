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

    public static String add(final Number firstNumber, final Number secondNumber,
                             final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        int sum = left + right;
        return convertResult(sum, resultSystem);
    }

    public static String subtract(final Number firstNumber, final Number secondNumber,
                                  final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        int diff = left - right;
        return convertResult(diff, resultSystem);
    }

    public static String multiply(final Number firstNumber, final Number secondNumber,
                                  final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        int prod = left * right;
        return convertResult(prod, resultSystem);
    }

    public static String divide(final Number firstNumber, final Number secondNumber,
                                final NumeralSystem resultSystem) {
        int left = parseString(firstNumber.getValue(), firstNumber.getSystem());
        int right = parseString(secondNumber.getValue(), secondNumber.getSystem());
        if (right == 0) {
            throw new IllegalArgumentException("Division by zero!");
        } else {
            int quotient = left / right;
            return convertResult(quotient, resultSystem);
        }
    }
}
