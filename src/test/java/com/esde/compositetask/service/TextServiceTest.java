package com.esde.compositetask.service;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.TextParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {

    private TextService service;
    private TextElement parsedText;

    private final String SAMPLE_TEXT = """
        This is the first paragraph. It has two sentences.

        Another paragraph here. One more sentence with the longestwordinhistoryhere.
        Short.
        """;

    @BeforeEach
    void setUp() {
        service = new TextService();
        TextParser parser = new TextParser();
        parsedText = parser.parse(SAMPLE_TEXT);
    }

    @Test
    void testSortParagraphsBySentenceCount() {
        List<TextElement> sorted = service.sortParagraphsBySentenceCount(parsedText);

        assertEquals(3, sorted.size());
        int first = sorted.get(0).getChild().size();
        int second = sorted.get(1).getChild().size();
        int third = sorted.get(2).getChild().size();

        assertTrue(first <= second && second <= third);
    }

    @Test
    void testFindSentencesWithLongestWord() {
        List<String> longest = service.findSentencesWithLongestWord(parsedText);
        assertTrue(longest.stream().anyMatch(s -> s.contains("longestwordinhistoryhere")));
    }

    @Test
    void testRemoveShortSentences() {
        TextElement result = service.removeShortSentences(parsedText, 3);
        String rebuiltText = result.toString();
        assertFalse(rebuiltText.contains("Short."));
    }

    @Test
    void testCountDuplicateWords() {
        Map<String, Integer> wordCounts = service.countDuplicateWords(parsedText);

        assertTrue(wordCounts.getOrDefault("the", 0) >= 2);
        assertTrue(wordCounts.containsKey("paragraph"));
    }

    @Test
    void testCountVowelsAndConsonants() {
        TextElement firstSentence = parsedText.getChild().get(0).getChild().get(0); // first paragraph → first sentence
        Map<String, Integer> counts = service.countVowelsAndConsonants(firstSentence);

        assertTrue(counts.get("vowels") > 0);
        assertTrue(counts.get("consonants") > 0);
    }
}
