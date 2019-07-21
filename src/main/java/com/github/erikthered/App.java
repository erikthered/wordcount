/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.erikthered;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.util.Map.Entry;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String... args) {
        if (args.length != 2) {
            throw new RuntimeException("Need exactly 2 arguments");
        }

        var commonWordsFile = Paths.get(args[0]);
        var textFile = Paths.get(args[1]);
        try {
            var commonWords = Files.readAllLines(commonWordsFile);
            var wordCount = Files.lines(textFile)
                    .flatMap(l -> Arrays.stream(l.toLowerCase().replaceAll("[,.!?;:()`'\"*-]", "").split("\\s")))
                    .filter(w -> !commonWords.contains(w) && w.length() > 0)
                    .collect(Collectors.toMap(
                            word -> word,
                            word -> 1,
                            (orig, dup) -> ++orig
                    ));
            wordCount.entrySet().stream()
                    .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEach((entry) -> logger.info("{}: {}", entry.getKey(), entry.getValue()));
        } catch (IOException e) {
            logger.error("Failed to count words: ", e);
            System.exit(1);
        }
    }
}
