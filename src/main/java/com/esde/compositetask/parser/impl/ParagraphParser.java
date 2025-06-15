package com.esde.compositetask.parser.impl;

import com.esde.compositetask.component.ElementType;
import com.esde.compositetask.component.TextComposite;
import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.AbstractParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    // Абзац — это блок текста, разделённый одной или более пустыми строками
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile(
            "(?m)(?=\\S).*?(?:\\n\\s*\\n|\\z)", Pattern.DOTALL
    );


    @Override
    public TextElement parse(String text) {
        logger.info("Начало парсинга текста на абзацы");

        TextComposite composite = new TextComposite(ElementType.TEXT);
        Matcher matcher = PARAGRAPH_PATTERN.matcher(text.trim());

        while (matcher.find()) {
            String paragraph = matcher.group().trim();
            if (!paragraph.isEmpty()) {
                TextElement paragraphElement = next.parse(paragraph);
                composite.add(paragraphElement);
            }
        }

        logger.info("Абзацев распарсено: {}", composite.getChild().size());
        return composite;
    }
}