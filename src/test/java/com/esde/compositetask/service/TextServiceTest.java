package com.esde.compositetask.service;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.CompositeTextParser;
import com.esde.compositetask.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    private TextElement parsedText;

    private final String SAMPLE_TEXT = """
    This is the first paragraph. It has two sentences.

    Another paragraph here. One more sentence with the longestwordinhistoryhere.

    Short.
    """;

    @BeforeEach
    void setUp() {
        CompositeTextParser parser = new CompositeTextParser();
        parsedText = parser.parse(SAMPLE_TEXT);
    }

    @Test
    void testSortParagraphsBySentenceCount() {
        TextService<List<TextElement>> paragraphService = new ParagraphService();
        List<TextElement> sorted = paragraphService.execute(parsedText);

        assertEquals(3, sorted.size());

        int first = sorted.get(0).getChild().size();
        int second = sorted.get(1).getChild().size();
        int third = sorted.get(2).getChild().size();

        assertTrue(first <= second && second <= third);
    }

    @Test
    void testFindSentencesWithLongestWord() {
        WordService wordService = new WordService();
        List<String> longest = wordService.findSentencesWithLongestWord(parsedText);

        assertTrue(longest.stream().anyMatch(s -> s.contains("longestwordinhistoryhere")));
    }

    @Test
    void testRemoveShortSentences() {
        TextService<TextElement> sentenceService = new SentenceService(3);
        TextElement result = sentenceService.execute(parsedText);

        String rebuiltText = result.toString();
        assertFalse(rebuiltText.contains("Short."));
    }

    @Test
    void testCountDuplicateWords() {
        WordService wordService = new WordService();
        Map<String, Integer> wordCounts = wordService.execute(parsedText);

        assertTrue(wordCounts.getOrDefault("the", 0) >= 2);
        assertTrue(wordCounts.containsKey("paragraph"));
    }

    @Test
    void testCountVowelsAndConsonants() {
        SentenceService sentenceService = new SentenceService(0);
        TextElement firstSentence = parsedText.getChild().get(0).getChild().get(0);

        Map<String, Integer> counts = sentenceService.countVowelsAndConsonants(firstSentence);

        assertTrue(counts.get("vowels") > 0);
        assertTrue(counts.get("consonants") > 0);
    }
}
