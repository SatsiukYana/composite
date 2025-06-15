package com.esde.compositetask.parser;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.impl.*;

public class TextParser {
    private final CustomParser parserChain;

    public TextParser() {
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        SymbolParser symbolParser = new SymbolParser();

        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(symbolParser);

        this.parserChain = paragraphParser;
    }

    public TextElement parse(String text) {
        return parserChain.parse(text);
    }
}
