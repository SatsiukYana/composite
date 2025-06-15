package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends AbstractParser {

    private static final String SENTENCE_REGEX = "(?<=[.!?…])\\s+";
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);

    @Override
    public TextElement parse(String text) {
        logger.info("Начало парсинга абзаца на предложения");

        TextComposite composite = new TextComposite(ElementType.PARAGRAPH);
        String[] sentences = text.split(SENTENCE_REGEX);

        for (String sentence : sentences) {
            if (!sentence.isBlank()) {
                TextElement sentenceElement = next.parse(sentence.trim());
                composite.add(sentenceElement);
            }
        }

        logger.info("Предложений распарсено: {}", composite.getChild().size());
        return composite;
    }
}