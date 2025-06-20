package com.esde.compositetask.service.impl;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.service.TextService;

import java.util.*;

public class WordService implements TextService<Map<String, Integer>> {

    @Override
    public Map<String, Integer> execute(TextElement textElement) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for (TextElement paragraph : textElement.getChild()) {
            for (TextElement sentence : paragraph.getChild()) {
                for (TextElement lexeme : sentence.getChild()) {
                    String word = lexeme.toString()
                            .replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "")
                            .toLowerCase();

                    if (!word.isBlank()) {
                        wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        return wordCounts;
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
}
