package com.example.mipt_praktika2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TekstoSkaiciavimoLogika {

    public int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Pattern pattern = Pattern.compile("[^.]+\\.");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public int countNumbers(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }
    public int countWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ' || c == ',' || c == '.') {
                inWord = false;
            } else {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            }
        }

        return count;
    }

    public int countPunctuation(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ' || c == ',' || c == '.') {
                count++;
            }
        }

        return count;
    }
}

