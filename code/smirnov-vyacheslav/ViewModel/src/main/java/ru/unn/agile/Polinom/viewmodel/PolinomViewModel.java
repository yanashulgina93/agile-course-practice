package ru.unn.agile.Polinom.viewmodel;

import javafx.beans.property.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.unn.agile.Polinom.Model.Polinom;
import ru.unn.agile.Polinom.Model.Polinom.Operation;

public class PolinomViewModel {
    private final StringProperty firstOperand = new SimpleStringProperty();
    private final StringProperty secondOperand = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public PolinomViewModel() {
        firstOperand.set("");
        secondOperand.set("");
        result.set("");
    }

    public void operation(final String operation) {
        if (firstOperand.get().isEmpty() || secondOperand.get().isEmpty()) {
            result.set(Errors.EMPTY_FIELD.getMessage());
            return;
        }

        Polinom first = convertIntoPolinom(firstOperand.get());
        Polinom second = convertIntoPolinom(secondOperand.get());

        if (first == null || second == null) {
            result.set(Errors.BAD_FORMAT.getMessage());
            return;
        }

        try {
            setResult(Operation.valueOf(operation).apply(first, second));
        } catch (IllegalArgumentException iae) {
            result.set(iae.getMessage());
        }
    }

    public StringProperty firstOperandProperty() {
        return firstOperand;
    }

    public StringProperty secondOperandProperty() {
        return secondOperand;
    }
    public StringProperty resultProperty() {
        return result;
    }
    public final String getResult() {
        return result.get();
    }

    private Polinom convertIntoPolinom(final String input) {
        Pattern reExpression = Pattern.compile("((^| *[+-] *)\\d+(\\.\\d+)?(x\\^\\d+)?)+");
        Matcher matcher = reExpression.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        Polinom operand = new Polinom();
        reExpression = Pattern.compile("([+-] *)?\\d+(\\.\\d+)?(x\\^\\d+)?");
        matcher = reExpression.matcher(input);
        while (matcher.find()) {
            int monomDegree = 0;
            String[] monom = matcher.group().split("x\\^");
            if (monom.length > 1) {
                monomDegree = Integer.parseInt(monom[1]);
            }
            double[] monomCoefficients = new double[monomDegree + 1];
            monomCoefficients[monomDegree] = Double.parseDouble(monom[0].replace(" ", ""));
            operand.add(new Polinom(monomCoefficients));
        }
        return operand;
    }

    private void setResult(final Polinom result) {
        double[] coefficients = result.getCoefficients();
        String resultPolinom = "";
        for (int i = coefficients.length - 1; i >= 0; i--) {
            resultPolinom += coefficients[i] + "x^" + String.valueOf(i) + " ";
        }

        this.result.set(resultPolinom.trim().replace(" -", "-").replace(" ", "+").
            replaceAll("\\+0\\.0x\\^\\d+", "").replace("x^0", ""));
    }

    private enum Errors {
        BAD_FORMAT("Incorrect Input"),
        EMPTY_FIELD("Set Polinoms");

        private String message;

        Errors(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
