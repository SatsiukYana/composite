package com.esde.compositetask.service.impl;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.service.TextService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ParagraphService implements TextService<List<TextElement>> {

    @Override
    public List<TextElement> execute(TextElement textElement) {
        List<TextElement> paragraphs = new ArrayList<>(textElement.getChild());
        paragraphs.sort(Comparator.comparingInt(p -> p.getChild().size()));
        return paragraphs;
    }
}