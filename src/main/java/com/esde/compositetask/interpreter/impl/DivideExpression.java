package com.esde.compositetask.interpreter.impl;

import com.esde.compositetask.interpreter.ExpressionInterpreter;

public class DivideExpression implements ExpressionInterpreter {
    private final ExpressionInterpreter left;
    private final ExpressionInterpreter right;

    public DivideExpression(ExpressionInterpreter left, ExpressionInterpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double interpret() {
        double rightValue = right.interpret();
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return left.interpret() / rightValue;
    }
}