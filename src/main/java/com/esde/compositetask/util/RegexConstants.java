package com.esde.compositetask.util;

public final class RegexConstants {
    public static final String PARAGRAPH_REGEX = "(?m)(?=\\S).*?(?:\\n\\s*\\n|\\z)";
    public static final String SENTENCE_REGEX = "(?<=[.!?…])\\s+";
    public static final String LEXEME_REGEX = "\\S+";

    private RegexConstants() {}
}