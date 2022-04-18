package com.github.raydeth;

import java.util.concurrent.ThreadLocalRandom;

public class WordGenerator {
    private Byte maxLength = 10;

    public String generate() {
        int randomWordLength = ThreadLocalRandom.current().nextInt(1, maxLength + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < randomWordLength; i++) {
            sb.append((char) ThreadLocalRandom.current().nextInt(65, 91));
        }
        return sb.toString();
    }
}
