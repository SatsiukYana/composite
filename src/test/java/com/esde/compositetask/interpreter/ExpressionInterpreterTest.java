package com.esde.compositetask.interpreter;

import com.esde.compositetask.interpreter.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionInterpreterTest {

    @Test
    void testAddExpression() {
        ExpressionInterpreter expr = new AddExpression(new NumberExpression(5), new NumberExpression(3));
        assertEquals(8, expr.interpret());
    }

    @Test
    void testSubtractExpression() {
        ExpressionInterpreter expr = new SubtractExpression(new NumberExpression(10), new NumberExpression(4));
        assertEquals(6, expr.interpret());
    }

    @Test
    void testMultiplyExpression() {
        ExpressionInterpreter expr = new MultiplyExpression(new NumberExpression(7), new NumberExpression(6));
        assertEquals(42, expr.interpret());
    }

    @Test
    void testDivideExpression() {
        ExpressionInterpreter expr = new DivideExpression(new NumberExpression(20), new NumberExpression(4));
        assertEquals(5, expr.interpret());
    }

    @Test
    void testDivideByZeroThrows() {
        ExpressionInterpreter expr = new DivideExpression(new NumberExpression(10), new NumberExpression(0));
        assertThrows(ArithmeticException.class, expr::interpret);
    }

    @Test
    void testNumberExpression() {
        ExpressionInterpreter expr = new NumberExpression(42);
        assertEquals(42, expr.interpret());
    }
}


