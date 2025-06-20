package com.esde.compositetask.interpreter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

    @Test
    void testParseAndEvaluateSimpleAddition() {
        String input = "3 + 7";
        String result = ExpressionParser.parseAndEvaluate(input);
        assertEquals("10", result);
    }

    @Test
    void testParseAndEvaluateMixedExpression() {
        String input = "2 + 3 * 4"; // Only simple expressions with two operands will be parsed independently
        String result = ExpressionParser.parseAndEvaluate(input);
        // It will replace "2 + 3" first, leaving "* 4"
        // So result will be "5 * 4"
        assertEquals("5 * 4", result);
    }

    @Test
    void testParseAndEvaluateMultipleExpressions() {
        String input = "1 + 2 and 10 / 2";
        String result = ExpressionParser.parseAndEvaluate(input);
        assertEquals("3 and 5", result);
    }

    @Test
    void testParseAndEvaluateDivisionByZero() {
        String input = "10 / 0";
        // Since division by zero throws, expression remains as-is
        String result = ExpressionParser.parseAndEvaluate(input);
        assertEquals("10 / 0", result);
    }

    @Test
    void testParseAndEvaluateDecimalNumbers() {
        String input = "3.5 + 2.5";
        String result = ExpressionParser.parseAndEvaluate(input);
        assertEquals("6.0", result);
    }

    @Test
    void testParseAndEvaluateNoExpression() {
        String input = "Hello world!";
        String result = ExpressionParser.parseAndEvaluate(input);
        assertEquals("Hello world!", result);
    }
}
