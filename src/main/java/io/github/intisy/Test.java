package io.github.intisy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        String format = "yyyy-MM-dd-HH-mm";
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern(format));
        System.out.println(formattedDateTime);
    }
}
