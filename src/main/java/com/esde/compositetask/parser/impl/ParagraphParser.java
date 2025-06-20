package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.esde.compositetask.util.RegexConstants.PARAGRAPH_REGEX;

public class ParagraphParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile(PARAGRAPH_REGEX, Pattern.DOTALL);

    @Override
    public TextElement parse(String text) {
        logger.info("Parsing text into paragraphs");

        TextComposite composite = new TextComposite(ElementType.TEXT);
        Matcher matcher = PARAGRAPH_PATTERN.matcher(text.trim());

        while (matcher.find()) {
            String paragraph = matcher.group().trim();
            if (!paragraph.isEmpty() && next != null) {
                TextElement paragraphElement = next.parse(paragraph);
                composite.add(paragraphElement);
            }
        }

        logger.info("Paragraphs parsed: {}", composite.getChild().size());
        return composite;
    }
}