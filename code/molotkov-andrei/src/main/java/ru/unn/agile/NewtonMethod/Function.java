package ru.unn.agile.NewtonMethod;

import java.util.Stack;

enum TypeComponent {add, diff, mult, div, digit, alpha, equally}

class FunctionComponent{
    TypeComponent type;
    int value;
}

public class Function {
    private Stack<Double> calculateStack;
    private FunctionComponent component[];
    private InfixExpressionToPolishExpressionConverter converter;

    private void setComponents(String polishNotation){
        char ch;
        int indexChar = 0;
        int indexComponent = 0;
        do{
            ch = polishNotation.charAt(indexChar++);
            switch(ch){
                case '+':
                    component[indexComponent++].type = TypeComponent.add;
                    break;
                case '-':
                    component[indexComponent++].type = TypeComponent.diff;
                    break;
                case '*':
                    component[indexComponent++].type = TypeComponent.mult;
                    break;
                case '/':
                    component[indexComponent++].type = TypeComponent.div;
                    break;
                case '=':
                    component[indexComponent++].type = TypeComponent.equally;
                    break;
                default:
                    if(Character.isAlphabetic(ch))
                        component[indexComponent++].type = TypeComponent.alpha;
                    else if(Character.isDigit(ch)){
                        String digit = new String();
                        digit += ch;
                        ch = polishNotation.charAt(indexChar);
                        while(Character.isDigit(ch)){
                            digit += ch;
                            ch = polishNotation.charAt(++indexChar);
                        }
                        //indexChar--;
                        ch = 'q';
                        component[indexComponent].type = TypeComponent.digit;
                        component[indexComponent++].value = Integer.valueOf(digit);
                    }
            }
        }while(ch != '=' && indexChar < polishNotation.length());
    }
    public Function(){}

    public Function(String formula){
        converter = new InfixExpressionToPolishExpressionConverter();
        String polishNotation = converter.convert(formula);
        component = new FunctionComponent[polishNotation.length()];
        for(int i = 0; i < polishNotation.length(); i++)
            component[i] = new FunctionComponent();
        calculateStack = new Stack<Double>();
        setComponents(polishNotation);
    }

    private void runOperation(char ch){
        double delta = 0.0000000001;
        boolean resultGetOperands = false;
        Double operand1 = new Double(0);
        Double operand2 = new Double(0);
        if(calculateStack.isEmpty())
            resultGetOperands = false;
        else
            operand1 = calculateStack.pop();
        if(calculateStack.isEmpty())
            resultGetOperands = false;
        else {
            operand2 = calculateStack.pop();
            resultGetOperands = true;
        }
        if(resultGetOperands){
            switch (ch){
                case '+':
                    calculateStack.push(operand2 + operand1);
                    break;
                case '-':
                    calculateStack.push(operand2 - operand1);
                    break;
                case '*':
                    calculateStack.push(operand2 * operand1);
                    break;
                case '/':
                    if(operand1 < delta)
                        calculateStack.clear();
                    else
                        calculateStack.push(operand2 / operand1);
                    break;
            }
        }
        else
            calculateStack.clear();
    }

    public double calculate(double x) {
        char ch;
        int currentComponent = 0;
        double result = 0;
        while(component[currentComponent].type != TypeComponent.equally){
            switch (component[currentComponent].type){
                case digit:
                    calculateStack.push(Double.valueOf((double)component[currentComponent].value));
                    break;
                case alpha:
                    calculateStack.push(Double.valueOf(x));
                    break;
                case add:
                    runOperation('+');
                    break;
                case diff:
                    runOperation('-');
                    break;
                case mult:
                    runOperation('*');
                    break;
                case div:
                    runOperation('/');
                    break;
            }
            currentComponent++;
        }
        if(!calculateStack.isEmpty())
            return calculateStack.pop();
        else
            return result;
    }
}
