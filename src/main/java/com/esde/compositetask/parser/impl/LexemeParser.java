package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.interpreter.ExpressionParser;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.esde.compositetask.util.RegexConstants.LEXEME_REGEX;

public class LexemeParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final Pattern LEXEME_PATTERN = Pattern.compile(LEXEME_REGEX);

    @Override
    public TextElement parse(String text) {
        logger.info("Parsing sentence into lexemes. Input: '{}'", text);
        text = ExpressionParser.parseAndEvaluate(text);
        logger.info("After evaluating arithmetic expressions: '{}'", text);

        TextComposite composite = new TextComposite(ElementType.SENTENCE);
        Matcher matcher = LEXEME_PATTERN.matcher(text);

        while (matcher.find()) {
            String lexeme = matcher.group();
            TextElement lexemeElement = (next != null) ? next.parse(lexeme) : new TextSymbol(lexeme, ElementType.LEXEME);
            composite.add(lexemeElement);
        }

        logger.info("Lexemes parsed: {}", composite.getChild().size());
        return composite;
    }
}