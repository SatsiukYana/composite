package com.esde.compositetask.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {

    private TextReader reader;

    @BeforeEach
    void setUp() {
        reader = new TextReader();
    }

    @Test
    void testReadTextSuccess() throws IOException {
        Path tempFile = Files.createTempFile("test-", ".txt");
        String expected = "Sample text for testing.";
        Files.writeString(tempFile, expected);

        Optional<String> result = reader.readText(tempFile);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadTextFileNotFound() {
        Path nonExistentPath = Path.of("nonexistent-file-" + System.nanoTime() + ".txt");

        Optional<String> result = reader.readText(nonExistentPath);

        assertTrue(result.isEmpty());
    }

    @Test
    void testReadTextThrowsIOException() throws IOException {
        Path tempFile = Files.createTempFile("test-", ".txt");
        Files.deleteIfExists(tempFile);

        Optional<String> result = reader.readText(tempFile);

        assertTrue(result.isEmpty());
    }
}
