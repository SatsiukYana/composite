package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(SymbolParser.class);

    @Override
    public TextElement parse(String text) {
        logger.info("Parsing lexeme into symbols: '{}'", text);

        TextComposite composite = new TextComposite(ElementType.LEXEME);

        for (char ch : text.toCharArray()) {
            String s = String.valueOf(ch);
            ElementType type = s.matches("[.,!?…]") ? ElementType.PUNCTUATION : ElementType.LETTER;
            composite.add(new TextSymbol(s, type));
        }

        logger.info("Symbols parsed: {}", composite.getChild().size());
        return composite;
    }
}