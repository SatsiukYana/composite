package com.esde.compositetask;

import com.esde.compositetask.component.TextElement;
import com.esde.compositetask.parser.CompositeTextParser;
import com.esde.compositetask.reader.TextReader;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("data/bit.txt");
        TextReader reader = new TextReader();

        Optional<String> fileContent = reader.readText(filePath);

        if (fileContent.isEmpty()) {
            System.err.println("Failed to read the file or the file is empty.");
            return;
        }

        String sampleText = fileContent.get();

        System.out.println("Original text:");
        System.out.println(sampleText);
        System.out.println("\n" + "=".repeat(50) + "\n");

        CompositeTextParser parser = new CompositeTextParser();
        TextElement parsedText = parser.parse(sampleText);

        System.out.println("Parsed and processed text:");
        System.out.println(parsedText);

        System.out.println("\n" + "=".repeat(50) + "\n");
    }
}
