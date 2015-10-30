package ru.unn.agile.NewtonMethod;

import java.util.Stack;

public class InfixExpressionToPolishExpressionConverter {
    private Stack<String> reversPolishStack = new Stack<String>();
    private Stack<String> operationStak     = new Stack<String>();

    int indexChar = 0;

    private int getOperationPriority(char operator)
    {
        int prioritet = -1;
        switch (operator){
            case '*':
            case '/':
                prioritet = 3;
                break;
            case '+':
            case '-':
                prioritet = 2;
                break;
            case '(':
                prioritet = 1;
                break;
            case '=':
                prioritet = 0;
                break;
        }
        return prioritet;
    }

    private boolean isOperator(char character){
        switch (character)
        {
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

    private void collectingNumber(String infixExpression){
        char ch = infixExpression.charAt(indexChar++);
        String digit = new String();
        digit = Character.toString(ch);
        ch = infixExpression.charAt(indexChar++);
        while(Character.isDigit(ch)) {
            digit += ch;
            ch = infixExpression.charAt(indexChar++);
        }
        reversPolishStack.push(digit);
        indexChar--;
    }

    private String createPolishExpression(){
        String polishExpression = new String();
        Stack<String> polishStack = new Stack<String>();

        while(!reversPolishStack.isEmpty())
            polishStack.push(reversPolishStack.pop());

        while (!polishStack.isEmpty())
            polishExpression += polishStack.pop();

        polishExpression += "=";

        return polishExpression;
    }

    private void setOperatorToStack(char ch){
        while(!operationStak.isEmpty()){
            char t = operationStak.pop().charAt(0);
            if(getOperationPriority(ch) <= getOperationPriority(t))
                reversPolishStack.push(Character.toString(t));
            else {
                operationStak.push(Character.toString(t));
                break;
            }
        }
        operationStak.push(Character.toString(ch));
    }

    public String convert(String infixExpression) {
        char ch;

        do{
            ch = infixExpression.charAt(indexChar++);
            if(Character.isAlphabetic(ch))
                reversPolishStack.push(Character.toString(ch));
            else if(Character.isDigit(ch)){
                indexChar--;
                collectingNumber(infixExpression);
            }
            else if(isOperator(ch)){
                setOperatorToStack(ch);
            }
        }while(ch != '=' && indexChar < infixExpression.length());

        return createPolishExpression();
    }
}
