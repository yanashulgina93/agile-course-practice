package ru.unn.agile.NewtonMethod;

import java.util.Stack;

enum Prioritet { low, equally, bracket, addDiff, multDiv }

public class ConverterToPolishNotation {
    private final Stack<String> reversPolishStack;
    private final Stack<String> operationStak;
    private int indexChar;

    public ConverterToPolishNotation() {
        reversPolishStack = new Stack<>();
        operationStak     = new Stack<>();
        indexChar = 0;
    }

    public String convert(final String infixExpression) {
        char ch;
        do {
            ch = infixExpression.charAt(indexChar++);
            if (Character.isAlphabetic(ch)) {
                reversPolishStack.push(Character.toString(ch));
            } else if (Character.isDigit(ch)) {
                indexChar--;
                collectingNumber(infixExpression);
            } else if (ch == '(') {
                operationStak.push(Character.toString(ch));
            } else if (ch == ')') {
                calculateInBrackets();
            } else if (isOperator(ch)) {
                setOperatorToStack(ch);
            }
        } while (ch != '=' && indexChar < infixExpression.length());

        return createPolishExpression();
    }

    private Prioritet getOperationPriority(final char operator) {
        Prioritet prioritet = Prioritet.low;
        switch (operator) {
            case '*':
            case '/':
                prioritet = Prioritet.multDiv;
                break;
            case '+':
            case '-':
                prioritet = Prioritet.addDiff;
                break;
            case '(':
                prioritet = Prioritet.bracket;
                break;
            case '=':
                prioritet = Prioritet.equally;
                break;
            default:
        }
        return prioritet;
    }

    private boolean isOperator(final char character) {
        switch (character) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '=':
                return true;
            default:
                return false;
        }
    }

    private void collectingNumber(final String infixExpression) {
        char ch = infixExpression.charAt(indexChar++);
        String digit = new String();
        digit = Character.toString(ch);
        ch = infixExpression.charAt(indexChar++);
        while (Character.isDigit(ch)) {
            digit += ch;
            ch = infixExpression.charAt(indexChar++);
        }
        reversPolishStack.push(digit);
        indexChar--;
    }

    private String createPolishExpression() {
        String polishExpression = new String();
        Stack<String> polishStack = new Stack<String>();

        while (!reversPolishStack.isEmpty()) {
            polishStack.push(reversPolishStack.pop());
        }

        while (!polishStack.isEmpty()) {
            polishExpression += polishStack.pop();
        }

        polishExpression += "=";

        return polishExpression;
    }

    private void setOperatorToStack(final char ch) {
        while (!operationStak.isEmpty()) {
            char t = operationStak.pop().charAt(0);
            if (getOperationPriority(ch).ordinal() <= getOperationPriority(t).ordinal()) {
                reversPolishStack.push(Character.toString(t));
            } else {
                operationStak.push(Character.toString(t));
                break;
            }
        }
        operationStak.push(Character.toString(ch));
    }

    private void calculateInBrackets() {
        while (true) {
            char tempChar = operationStak.pop().charAt(0);
            if (tempChar == '(') {
                break;
            }
            reversPolishStack.push(Character.toString(tempChar));
        }
    }
}
