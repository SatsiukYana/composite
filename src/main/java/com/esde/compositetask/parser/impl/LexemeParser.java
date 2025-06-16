package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.interpreter.ExpressionParser;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final Pattern LEXEME_PATTERN = Pattern.compile("\\S+");

    @Override
    public TextElement parse(String text) {
        logger.info("Парсинг лексем. Входной текст: '{}'", text);
        text = ExpressionParser.parseAndEvaluate(text);
        logger.info("После интерпретации арифметики: '{}'", text);

        TextComposite composite = new TextComposite(ElementType.LEXEME);
        Matcher matcher = LEXEME_PATTERN.matcher(text);

        while (matcher.find()) {
            String lexeme = matcher.group();
            TextElement lexemeElement;

            if (next != null) {
                lexemeElement = next.parse(lexeme);
            } else {
                lexemeElement = new TextSymbol(lexeme, ElementType.LEXEME);
            }

            composite.add(lexemeElement);
        }

        logger.info("Лексем распарсено: {}", composite.getChild().size());
        return composite;
    }
}
