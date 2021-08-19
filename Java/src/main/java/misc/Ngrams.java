package misc;

import java.util.*;

public class Ngrams {
  static final String START = "^";
  static final String END = "$";
  static final String SPACE = " ";

  public static void main(String[] args) {
    String[] text = {
      "peter piper picked a peck of pickled peppers",
      "a peck of pickled peppers peter piper picked",
      "if peter piper picked a peck of pickled peppers",
      "where s the peck of pickled peppers peter piper picked"
    };
    final Map<String, Integer> counts = countNgrams(2, text);

    final Map<Integer, Set<String>> countOfCounts = getCountOfCounts(counts);
    System.out.println(countOfCounts);
  }

  static Map<Integer, Set<String>> getCountOfCounts(Map<String, Integer> counts) {
    final Map<Integer, Set<String>> countOfCounts = new HashMap<>();
    for (String ngram : counts.keySet()) {
      final Integer count = counts.get(ngram);
      final Set<String> ngramsForCount = countOfCounts.getOrDefault(count, new HashSet<>());
      ngramsForCount.add(ngram);
      countOfCounts.put(count, ngramsForCount);
    }
    return countOfCounts;
  }

  static Map<String, Integer> countNgrams(int n, String... lines) {
    Map<String, Integer> ngramFrequency = new HashMap<>();
    for (String line : lines) {
      final String[] tokens = addTerminatorsAndSplit(line);
      final String[] ngrams = getNgrams(n, tokens);
      addCounts(ngramFrequency, ngrams);
    }
    return ngramFrequency;
  }

  static void addCounts(Map<String, Integer> counts, String[] ngrams) {
    for (String ngram : ngrams) {
      final Integer newCount = counts.getOrDefault(ngram, 0) + 1;
      counts.put(ngram, newCount);
    }
  }

  static String[] getNgrams(int n, String[] tokens) {
    final int count = tokens.length - n + 1;
    final String[] ngrams = new String[count];
    for (int i = 0; i < count; i++) {
      final String[] words = getElements(i, n, tokens);
      ngrams[i] = String.join(SPACE, words);
    }
    return ngrams;
  }

  static String[] getElements(int start, int numElements, String[] src) {
    final String[] elements = new String[numElements];
    System.arraycopy(src, start, elements, 0, numElements);
    return elements;
  }

  static String[] addTerminatorsAndSplit(String line) {
    final String[] words = line.toLowerCase(Locale.ROOT).split(SPACE);
    final String[] withTerminators = new String[words.length + 2];
    withTerminators[0] = START;
    System.arraycopy(words, 0, withTerminators, 1, words.length);
    withTerminators[withTerminators.length - 1] = END;
    return withTerminators;
  }
}
