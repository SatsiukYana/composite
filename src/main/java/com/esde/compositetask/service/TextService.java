package com.esde.compositetask.service;

import com.esde.compositetask.component.TextElement;

public interface TextService<T> {
    T execute(TextElement textElement);
}


