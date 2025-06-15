package com.esde.compositetask.interpreter.impl;

import com.esde.compositetask.interpreter.ExpressionInterpreter;

public class NumberExpression implements ExpressionInterpreter {
    private final double number;

    public NumberExpression(double number) {
        this.number = number;
    }

    @Override
    public double interpret() {
        return number;
    }
}