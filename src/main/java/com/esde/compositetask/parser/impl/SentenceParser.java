package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.esde.compositetask.util.RegexConstants.SENTENCE_REGEX;

public class SentenceParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);

    @Override
    public TextElement parse(String text) {
        logger.info("Parsing paragraph into sentences");

        TextComposite composite = new TextComposite(ElementType.PARAGRAPH);
        String[] sentences = text.split(SENTENCE_REGEX);

        for (String sentence : sentences) {
            if (!sentence.isBlank() && next != null) {
                TextElement sentenceElement = next.parse(sentence.trim());
                composite.add(sentenceElement);
            }
        }

        logger.info("Sentences parsed: {}", composite.getChild().size());
        return composite;
    }
}