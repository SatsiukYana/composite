package com.esde.compositetask;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.TextParser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String inputText = Files.readString(Paths.get("data/arithmetic.txt"));

            TextParser parser = new TextParser();
            TextElement parsed = parser.parse(inputText);

            System.out.println("Reconstructed Text");
            System.out.println(parsed.toString());

        } catch (Exception e) {
            System.err.println("Ошибка при обработке текста: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
