package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {

    public static String cleanText(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        content = content.replaceAll("[^A-Za-z ]"," ").toLowerCase(Locale.ROOT);
        return content;
    }

    public static void main(String[] args) throws IOException {

        LocalDateTime start = LocalDateTime.now();
       // Path path = Paths.get()
        String content = new String(Files.readAllBytes(Paths.get("src/edu/pro/txt/harry.txt")));

        content = content.replaceAll("[^A-Za-z ]"," ").toLowerCase(Locale.ROOT);

        String[] words = content.split(" +"); // 400 000

        Arrays.sort(words);

        String distinctString = " ";

        for (int i = 0; i < words.length ; i++) {
            if (!distinctString.contains(words[i])){
                distinctString+= words[i] + " ";
            }
        }

        String[] distincts = distinctString.split(" ");
        int[] freq = new int[distincts.length];

        for (int i = 0; i < distincts.length ; i++) {
            int count = 0;
            for (int j = 0; j < words.length ; j++) {
                if (distincts[i].equals(words[j])) {
                    count++;
                }
            }
            freq[i] = count;
        }

        for (int i = 0; i < distincts.length ; i++) { // 5 000
            distincts[i] += " " + freq[i];
        }

        Arrays.sort(distincts, Comparator.comparing(str
                -> Integer.valueOf(str.replaceAll("[^0-9]", ""))));

        for (int i = 0; i < 30; i++) {
            System.out.println(distincts[distincts.length - 1 - i]);
        }
        LocalDateTime finish = LocalDateTime.now();

        System.out.println("------");
        System.out.println(ChronoUnit.MILLIS.between(start, finish));

    }
}
