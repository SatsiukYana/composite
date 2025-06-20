package com.esde.compositetask.parser;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private static final String SAMPLE_TEXT = """
        This is a sentence. Another one follows.

        This paragraph has one sentence.
        """;

    private ParagraphParser paragraphParser;
    private SentenceParser sentenceParser;
    private LexemeParser lexemeParser;
    private SymbolParser symbolParser;

    @BeforeEach
    void setUp() {
        paragraphParser = new ParagraphParser();
        sentenceParser = new SentenceParser();
        lexemeParser = new LexemeParser();
        symbolParser = new SymbolParser();

        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);
    }

    @Test
    void testParagraphParser() {
        TextElement text = paragraphParser.parse(SAMPLE_TEXT);
        assertEquals(2, text.getChild().size(), "Should be 2 paragraphs");
    }

    @Test
    void testSentenceParser() {
        TextElement paragraph = sentenceParser.parse("This is a sentence. Another one follows.");
        assertEquals(2, paragraph.getChild().size(), "Should be 2 sentences");
    }

    @Test
    void testLexemeParser() {
        TextElement sentence = lexemeParser.parse("This is a test");
        assertEquals(4, sentence.getChild().size(), "Should be 4 lexemes");
    }

    @Test
    void testSymbolParser() {
        TextElement lexeme = symbolParser.parse("Word.");
        assertEquals(5, lexeme.getChild().size(), "Should be 5 symbols including punctuation");
        assertEquals("Word.", lexeme.toString(), "Symbols should reconstruct the original lexeme");
    }

    @Test
    void testFullChain() {
        CompositeTextParser fullParser = new CompositeTextParser();
        TextElement result = fullParser.parse(SAMPLE_TEXT);
        assertNotNull(result);
        assertEquals(2, result.getChild().size(), "Full chain should parse 2 paragraphs");
    }
}
