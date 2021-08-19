package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class JPMTest {
  // Hard-coded input text
  static final String CORPUS =
      "Mary had a little lamb its fleece was white as snow;\n"
          + "And everywhere that Mary went, the lamb was sure to go.\n"
          + "It followed her to school one day, which was against the rule;\n"
          + "It made the children laugh and play, to see a lamb at school.\n"
          + "And so the teacher turned it out, but still it lingered near,\n"
          + "And waited patiently about till Mary did appear.\n"
          + "\"Why does the lamb love Mary so?\" the eager children cry;\"Why, Mary loves the lamb, you know\" the teacher did reply.\"";

  static final List<String> WORDS = splitAndNormalise(CORPUS);

  /** Iterate through each line of input. */
  public static void main(String[] args) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader)) {
      System.out.print(
          "Input format: \"N,word\", where N is a positive integer and word is a string.\nType here: ");
      String line;
      while ((line = in.readLine()) != null && !line.isEmpty()) {
        // assuming the input does not need validating
        final String[] inputTokens = line.split(",");
        try {
          int inputN = Integer.parseInt(inputTokens[0].trim());
          final String inputWord = inputTokens[1].trim().toLowerCase(Locale.ROOT);
          final Map<String, List<String>> ngramsByWord = ngramsByWord(WORDS, inputN);
          final Map<String, Double> probabilities = calculateProbabilities(ngramsByWord, inputWord);
          printProbabilities(probabilities);
        } catch (IllegalArgumentException exception) {
          System.out.println("Illegal arguments: " + line);
        } finally {
          System.out.print("Type here (or press enter to exit): ");
        }
      }
    }
  }

  private static void printProbabilities(Map<String, Double> probabilities) {
    final List<Map.Entry<String, Double>> entries = new ArrayList<>(probabilities.entrySet());
    entries.sort(
        (n1, n2) -> {
          int valueComparator = Double.compare(n2.getValue(), n1.getValue());
          return valueComparator != 0 ? valueComparator : n1.getKey().compareTo(n2.getKey());
        });
    final String printString =
        entries.stream()
            .map(entry -> String.format("%s,%.3f", entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(";"));
    System.out.println(printString);
  }

  /** Given a word returns a list of n-grams and probabilities following it. */
  private static Map<String, Double> calculateProbabilities(
      Map<String, List<String>> ngramsByWord, String word) {
    final List<String> ngrams = ngramsByWord.get(word);
    if (ngrams == null) {
      return Collections.emptyMap();
    }
    final Map<String, Long> countByNgram = counts(ngrams);
    return countByNgram.entrySet().stream()
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, entry -> (double) entry.getValue() / ngrams.size()));
  }

  /** For each word in words returns a list of all succeeding n-grams in words as a map. */
  private static Map<String, List<String>> ngramsByWord(List<String> words, int n) {
    Map<String, List<String>> ngramsByWord = new HashMap<>();
    final int lastIdx = words.size() - n + 1;
    for (int i = 0; i < lastIdx; i++) {
      final String word = words.get(i);
      final List<String> ngrams = ngramsByWord.getOrDefault(word, new ArrayList<>());
      ngrams.add(joinWordsAfterIdx(words, i, n));
      ngramsByWord.put(word, ngrams);
    }
    return ngramsByWord;
  }

  /** Returns n-1 words immediately following the element at idx as a space-separated string. */
  private static String joinWordsAfterIdx(List<String> words, int idx, int n) {
    final List<String> followingWords = words.subList(idx + 1, idx + n);
    return String.join(" ", followingWords);
  }

  /**
   * Splits the text into words using non-alphanumeric characters as separators (spaces, punctuation
   * and newlines). Normalises the result and removes empty strings.
   */
  private static List<String> splitAndNormalise(String text) {
    final String[] tokens = text.split("\\W");
    return Arrays.stream(tokens)
        .filter(s -> !s.isEmpty())
        .map(String::toLowerCase)
        .map(String::trim)
        .collect(Collectors.toList());
  }

  /** Returns counts for each unique token in the list as a map. */
  private static Map<String, Long> counts(List<String> tokens) {
    return tokens.stream().collect(Collectors.groupingBy(token -> token, Collectors.counting()));
  }
}
