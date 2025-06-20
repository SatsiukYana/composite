package com.esde.compositetask.service.impl;

import com.esde.compositetask.component.*;
import com.esde.compositetask.service.TextService;
import java.util.HashMap;
import java.util.Map;

public class SentenceService implements TextService<TextElement> {
    private final int minWords;

    public SentenceService(int minWords) {
        this.minWords = minWords;
    }

    @Override
    public TextElement execute(TextElement text) {
        TextComposite resultText = new TextComposite(text.getElementType());

        for (TextElement paragraph : text.getChild()) {
            TextComposite resultParagraph = new TextComposite(paragraph.getElementType());

            for (TextElement sentence : paragraph.getChild()) {
                long wordCount = sentence.getChild().stream()
                        .filter(lexeme -> lexeme.getElementType() == ElementType.LEXEME)
                        .count();

                if (wordCount >= minWords) {
                    resultParagraph.add(sentence);
                }
            }

            if (!resultParagraph.getChild().isEmpty()) {
                resultText.add(resultParagraph);
            }
        }

        return resultText;
    }
    public Map<String, Integer> countVowelsAndConsonants(TextElement sentence) {
        int vowels = 0;
        int consonants = 0;
        String vowelRegex = "[aeiouAEIOUаеёиоуыэюяАЕЁИОУЫЭЮЯ]";

        for (TextElement lexeme : sentence.getChild()) {
            for (TextElement symbol : lexeme.getChild()) {
                String ch = symbol.toString();
                if (ch.matches("[a-zA-Zа-яА-ЯёЁ]")) {
                    if (ch.matches(vowelRegex)) {
                        vowels++;
                    } else {
                        consonants++;
                    }
                }
            }
        }

        Map<String, Integer> counts = new HashMap<>();
        counts.put("vowels", vowels);
        counts.put("consonants", consonants);
        return counts;
    }
}

