package DesignPatterns.BehavioralPatterns;

import java.util.Stack;

interface Expression {
    int interpret();
}

class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    public int interpret() {
        return number;
    }
}

class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

class MultiplyExpression implements Expression {
    private Expression left;
    private Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        return left.interpret() * right.interpret();
    }
}

class DivideExpression implements Expression {
    private Expression left;
    private Expression right;

    public DivideExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret() {
        int rightValue = right.interpret();
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.interpret() / rightValue;
    }
}

class ExpressionParser {
    public Expression parse(String expression) {
        Stack<Expression> stack = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (isOperator(token)) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                Expression operator = getOperatorExpression(token, left, right);
                stack.push(operator);
            } else {
                Expression number = new NumberExpression(Integer.parseInt(token));
                stack.push(number);
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/");
    }

    private Expression getOperatorExpression(String operator,
                                             Expression left,
                                             Expression right) {
        switch (operator) {
            case "+":
                return new AddExpression(left, right);
            case "-":
                return new SubtractExpression(left, right);
            case "*":
                return new MultiplyExpression(left, right);
            case "/":
                return new DivideExpression(left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}

public class InterpreterPattern {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();

        // Postfix notation: "5 3 +" means 5 + 3
        Expression expr1 = parser.parse("5 3 +");
        System.out.println("5 + 3 = " + expr1.interpret()); // 8

        // "10 5 - 2 *" means (10 - 5) * 2
        Expression expr2 = parser.parse("10 5 - 2 *");
        System.out.println("(10 - 5) * 2 = " + expr2.interpret()); // 10

        // "20 4 / 3 +" means (20 / 4) + 3
        Expression expr3 = parser.parse("20 4 / 3 +");
        System.out.println("(20 / 4) + 3 = " + expr3.interpret()); // 8
    }
}
