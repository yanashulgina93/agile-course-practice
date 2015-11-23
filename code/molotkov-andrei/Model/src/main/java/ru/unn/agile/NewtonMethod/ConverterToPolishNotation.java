package ru.unn.agile.NewtonMethod;

import java.util.Stack;

enum Priority { low, equally, bracket, addDiff, multDiv }

public class ConverterToPolishNotation {
    private final Stack<String> reversPolishStack;
    private final Stack<String> operationStak;
    private final Stack<Character> leftBracketsStack;
    private int indexChar;

    public ConverterToPolishNotation() {
        reversPolishStack = new Stack<>();
        operationStak     = new Stack<>();
        leftBracketsStack = new Stack<>();
        indexChar = 0;
    }

    public String convert(final String infixExpression) {
        char ch = infixExpression.charAt(0);
        if (!isCorrectFirstCharacter(ch)) {
            throw  new ArithmeticException("Incorrect first character");
        }
        char tempCh;
        do {
            ch = infixExpression.charAt(indexChar++);
            if (Character.isAlphabetic(ch)) {
                reversPolishStack.push(Character.toString(ch));
                tempCh = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForVariable(tempCh)) {
                    throw new ArithmeticException("Incorrect character after variable");
                }
            } else if (Character.isDigit(ch)) {
                tempCh = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForDigit(tempCh)) {
                    throw new ArithmeticException("Incorrect character after digit");
                }
                indexChar--;
                collectingNumber(infixExpression);
            } else if (ch == '(') {
                operationStak.push(Character.toString(ch));
                leftBracketsStack.push(ch);
                tempCh = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForLeftBracket(tempCh)) {
                    throw new ArithmeticException("Incorrect character after left bracket");
                }
            } else if (ch == ')') {
                if (leftBracketsStack.isEmpty()) {
                    throw new ArithmeticException("Missing left bracket");
                }
                leftBracketsStack.pop();
                tempCh = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForRightBracket(tempCh)) {
                    throw new ArithmeticException("Incorrect character after right bracket");
                }
                calculateInBrackets();
            } else if (isOperator(ch)) {
                setOperatorToStack(ch);
                if (ch == '=') {
                    break;
                }
                tempCh = infixExpression.charAt(indexChar);
                if (!isAllowableCaseForOperator(tempCh)) {
                    throw new ArithmeticException("Incorrect character after operator");
                }
            }
        } while (ch != '=' && indexChar < infixExpression.length());
        return createPolishExpression();
    }

    private Priority getOperationPriority(final char operator) {
        Priority priority = Priority.low;
        switch (operator) {
            case '*':
            case '/':
                priority = Priority.multDiv;
                break;
            case '+':
            case '-':
                priority = Priority.addDiff;
                break;
            case '(':
                priority = Priority.bracket;
                break;
            case '=':
                priority = Priority.equally;
                break;
            default:
        }
        return priority;
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

    private boolean isAllowableCaseForVariable(final char character) {
        return isOperator(character) || isRightBracket(character);
    }

    private boolean isAllowableCaseForDigit(final char character) {
        return isAllowableCaseForVariable(character) || Character.isDigit(character);
    }

    private boolean isAllowableCaseForOperator(final char character) {
        return Character.isAlphabetic(character) || Character.isDigit(character)
                || isLeftBracket(character);
    }

    private boolean isCorrectFirstCharacter(final char character) {
        return isAllowableCaseForOperator(character);
    }

    private boolean isAllowableCaseForLeftBracket(final char character) {
        return Character.isAlphabetic(character) || Character.isDigit(character)
                || isRightBracket(character);
    }

    private boolean isAllowableCaseForRightBracket(final char character) {
        return isOperator(character) || isRightBracket(character);
    }

    private boolean isRightBracket(final char character) {
        return character == ')';
    }

    private boolean isLeftBracket(final char character) {
        return character == '(';
    }
}
