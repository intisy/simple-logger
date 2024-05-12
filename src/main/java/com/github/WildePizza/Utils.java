package com.github.WildePizza;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class Utils {
    protected static String removeDuplicateLines(String text) {
        String[] lines = text.split("\\r?\\n");

        LinkedHashSet<String> uniqueLines = new LinkedHashSet<>(Arrays.asList(lines));

        StringBuilder result = new StringBuilder();

        for (String uniqueLine : uniqueLines) {
            result.append(uniqueLine);
            result.append("\n");
        }

        // Remove the last newline character if it exists
        if (result.length() > 0 && result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
