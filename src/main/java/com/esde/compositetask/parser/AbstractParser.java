package com.esde.compositetask.parser;

public abstract class AbstractParser implements CustomParser {
    protected CustomParser next;

    public void setNext(CustomParser next) {
        this.next = next;
    }
}


