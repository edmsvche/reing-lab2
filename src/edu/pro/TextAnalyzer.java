package edu.pro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {

	public static void main(String[] args) throws IOException {
		Instant start = Instant.now();

		String filePath = "src/edu/pro/txt/harry.txt";
		String content = readAndClean(filePath);

		Map<String, Integer> wordFrequency = calculateFrequency(content);
		List<Map.Entry<String, Integer>> sortedFrequencies = sortFrequencies(wordFrequency);

		printTopFrequencies(sortedFrequencies, 30);

		Instant finish = Instant.now();
		System.out.println("------");
		System.out.println(Duration.between(start, finish).toMillis() + " ms");
	}

	private static String readAndClean(String filePath) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		return content.replaceAll("[^A-Za-z ]", "").toLowerCase(Locale.ROOT);
	}

	private static Map<String, Integer> calculateFrequency(String content) {
		Map<String, Integer> frequency = new HashMap<>();
		List<String> words = List.of(content.split("\\s+"));

		words.forEach(word -> frequency.put(word, frequency.getOrDefault(word, 0) + 1));

		return frequency;
	}

	private static List<Map.Entry<String, Integer>> sortFrequencies(Map<String, Integer> frequencyMap) {
		return frequencyMap.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toList());
	}

	private static void printTopFrequencies(List<Map.Entry<String, Integer>> frequencies, int topN) {
		frequencies.stream()
			.limit(topN)
			.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
	}
}
