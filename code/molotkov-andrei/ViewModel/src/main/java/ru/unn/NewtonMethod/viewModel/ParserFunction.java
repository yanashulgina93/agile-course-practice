package ru.unn.NewtonMethod.viewModel;

import java.util.Stack;

public class ParserFunction {
    private int indexChar = 0;
    private final Stack<Character> leftBracketsStack = new Stack<>();

    public boolean isCorrectFunction(final String function) {
        char ch = function.charAt(0);
        if (!isCorrectFirstCharacter(ch)) {
            return false;
        }
        do {
            ch = function.charAt(indexChar++);
            if (Character.isAlphabetic(ch)) {
                ch = function.charAt(indexChar);
                if (!isAllowableCaseForVariable(ch)) {
                    return false;
                }
            } else if (Character.isDigit(ch)) {
                ch = function.charAt(indexChar);
                if (!isAllowableCaseForDigit(ch)) {
                    return false;
                }
            } else if (isOperator(ch)) {
                ch = function.charAt(indexChar);
                if (!isAllowableCaseForOperator(ch)) {
                    return false;
                }
            } else if (isLeftBracket(ch)) {
                leftBracketsStack.push(ch);
                ch = function.charAt(indexChar);
                if (!isAllowableCaseForLeftBracket(ch)) {
                    return false;
                }
            } else if (isRightBracket(ch)) {
                if (leftBracketsStack.isEmpty()) {
                    return false;
                }
                leftBracketsStack.pop();
                ch = function.charAt(indexChar);
                if (!isAllowableCaseForRightBracket(ch)) {
                    return false;
                }
            }
        } while (ch != '=' && indexChar < function.length());
        if (!leftBracketsStack.isEmpty()) {
            return false;
        }
        return true;
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

    private boolean isRightBracket(final char character) {
        return character == ')';
    }

    private boolean isLeftBracket(final char character) {
        return character == '(';
    }
}
