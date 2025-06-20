package com.esde.compositetask.parser;

import com.esde.compositetask.component.*;
import com.esde.compositetask.parser.CompositeTextParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextParserTest {

    @Test
    void testTextSymbolToString() {
        TextSymbol symbol = new TextSymbol("a", ElementType.LETTER);
        assertEquals("a", symbol.toString());
    }

    @Test
    void testTextCompositeToString() {
        TextComposite sentence = new TextComposite(ElementType.SENTENCE);
        sentence.add(new TextSymbol("Hello", ElementType.LEXEME));
        sentence.add(new TextSymbol("world", ElementType.LEXEME));

        assertEquals("Hello world", sentence.toString());
    }

    @Test
    void testTextSymbolUnsupportedOperations() {
        TextSymbol symbol = new TextSymbol("a", ElementType.LETTER);

        assertThrows(UnsupportedOperationException.class, () -> symbol.add(new TextSymbol("b", ElementType.LETTER)));
        assertThrows(UnsupportedOperationException.class, () -> symbol.remove(new TextSymbol("b", ElementType.LETTER)));
        assertThrows(UnsupportedOperationException.class, symbol::getChild);
    }

    @Test
    void testCompositeTextParserArithmeticReplacement() {
        CompositeTextParser parser = new CompositeTextParser();
        String input = "2 + 3 is five.";
        TextElement parsed = parser.parse(input);
        String result = parsed.toString();

        assertTrue(result.contains("5"));
        assertFalse(result.contains("2 + 3"));
    }


    @Test
    void testCompositeTextParserStructure() {
        CompositeTextParser parser = new CompositeTextParser();
        String input = "First sentence. Second sentence!";
        TextElement parsed = parser.parse(input);

        assertEquals(ElementType.TEXT, parsed.getElementType());
        assertFalse(parsed.getChild().isEmpty());

        TextElement paragraph = parsed.getChild().get(0);
        assertEquals(ElementType.PARAGRAPH, paragraph.getElementType());
        assertEquals(2, paragraph.getChild().size()); // 2 sentences
    }

    @Test
    void testEmptyInput() {
        CompositeTextParser parser = new CompositeTextParser();
        TextElement parsed = parser.parse("   ");
        assertEquals("", parsed.toString());
    }
}

