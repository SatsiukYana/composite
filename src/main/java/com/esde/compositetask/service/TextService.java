package com.esde.compositetask.service;

import com.esde.compositetask.component.*;

import java.util.*;

public class TextService {

    public List<TextElement> sortParagraphsBySentenceCount(TextElement text) {
        List<TextElement> paragraphs = new ArrayList<>(text.getChild());
        paragraphs.sort(Comparator.comparingInt(p -> p.getChild().size()));
        return paragraphs;
    }

    public List<String> findSentencesWithLongestWord(TextElement text) {
        int maxLength = 0;
        List<String> result = new ArrayList<>();

        for (TextElement paragraph : text.getChild()) {
            for (TextElement sentence : paragraph.getChild()) {
                String sentenceStr = sentence.toString();
                String[] words = sentenceStr.split("\\s+");
                for (String word : words) {
                    String cleanWord = word.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "");
                    if (cleanWord.length() > maxLength) {
                        maxLength = cleanWord.length();
                        result.clear();
                        result.add(sentenceStr);
                    } else if (cleanWord.length() == maxLength) {
                        result.add(sentenceStr);
                    }
                }
            }
        }
        return result;
    }

    public TextElement removeShortSentences(TextElement text, int minWords) {
        TextComposite resultText = new TextComposite(text.getElementType());

        for (TextElement paragraph : text.getChild()) {
            TextComposite resultParagraph = new TextComposite(paragraph.getElementType());

            for (TextElement sentence : paragraph.getChild()) {
                int wordCount = (int) sentence.getChild().stream()
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

    public Map<String, Integer> countDuplicateWords(TextElement text) {
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (TextElement paragraph : text.getChild()) {
            for (TextElement sentence : paragraph.getChild()) {
                for (TextElement lexeme : sentence.getChild()) {
                    String word = lexeme.toString()
                            .replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "")
                            .toLowerCase();

                    if (!word.isBlank()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        return wordFrequency;
    }

    public Map<String, Integer> countVowelsAndConsonants(TextElement sentence) {
        int vowels = 0;
        int consonants = 0;
        String vowelRegex = "[aeiouаеёиоуыэюяAEIOUАЕЁИОУЫЭЮЯ]";

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
