/*
 * Licensed Materials - Property of IBM
 * 5725-G22
 * (C) Copyright IBM Corp. 2012, 2019 All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.i2group.disco.test.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.jetty.client.Origin;
import com.i2group.disco.data.items.rest.transport.internal.OriginIdentifier;
import com.i2group.disco.data.sourcerecord.internal.RecordIdentifierHelper;
import com.i2group.disco.model.internal.ObjectMapperSingleton;
import com.i2group.disco.recordidentifier.internal.LegacyRecordIdentifierGenerator;
import com.i2group.disco.util.json.internal.ObjectMappers;


@SuppressWarnings("CheckStyle")
public class KirillTest {

  public static void main(String[] args) throws IOException {
    //    final RandomStringGenerator generator = new RandomStringGenerator();
    //    final FrequencyCalculator calculator = new FrequencyCalculator();
    //    iterateWork(
    //        () -> {
    //          calculator.count(generator.alphaString(5));
    //        },
    //        10_000,
    //        false);
    //    calculator.build();
    //    calculator.printCounts(5);
    //    System.out.println(concatenate("a", "b", "c", "d"));

  }

  private static String concatenate(String a, String b) {
    return a + b;
  }

  private static String concatenate(String... strings) {
    if (strings.length == 1) return strings[0];
    final String[] na = Arrays.copyOf(strings, strings.length - 1);
    na[na.length - 1] = concatenate(strings[strings.length - 2], strings[strings.length - 1]);
    return concatenate(na);
  }

  @SuppressWarnings("AutoBoxing")
  private static void iterateWork(Runnable work, int iterations, boolean print) {
    final Collection<Long> times = new ArrayList<>();
    for (int i = 0; i < iterations; i++) {
      final long timeTaken = doWorkAndPrintTimings(work, print);
      times.add(timeTaken);
    }

    final long min = times.stream().min(Long::compare).get();
    final long max = times.stream().max(Long::compare).get();
    final long total = times.stream().reduce((x, y) -> x + y).get();
    final double average = ((double) total) / (times.size());
    System.out.println("Completed " + iterations + " iterations. Timing results:");
    System.out.println("Min: " + min + " ms.");
    System.out.println("Max: " + max + " ms.");
    System.out.println("Average: " + average + " ms.");
  }

  private static long doWorkAndPrintTimings(Runnable work, boolean print) {
    final long start = System.currentTimeMillis();
    if (print) {
      System.out.print("Running timed work:");
      System.out.println(work.toString());
    }
    work.run();
    final long stop = System.currentTimeMillis();
    final long timeTaken = stop - start;
    if (print) {
      System.out.println("Work completed. Time taken (milliseconds): " + timeTaken);
    }
    return timeTaken;
  }

  @SuppressWarnings("unused")
  private static void printChars(int min, int max) {
    for (int i = min; i <= max; i++) {
      System.out.print("i = " + i + " ");
      char c = (char) i;
      System.out.print("c = " + c + " ");
      System.out.print("(int) c = " + (int) c);
      System.out.println();
    }
    System.out.println("END");
  }

  static class RandomStringGenerator {
    private final Random mRandom;
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMER = "0123456789";
    private static final char[] ALPHA = (UPPER + LOWER).toCharArray();
    private static final char[] ALPHANUMERIC = (UPPER + LOWER + NUMER).toCharArray();

    public RandomStringGenerator() {
      mRandom = new Random();
    }

    public String asciiString(int length) {
      return anyString(length, (int) Character.MIN_VALUE, 127);
    }

    public String extendedString(int length) {
      return anyString(length, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);
    }

    public String alphaNumericString(int length) {
      return anyString(length, ALPHANUMERIC);
    }

    public String alphaString(int length) {
      return anyString(length, ALPHA);
    }

    public String numericString(int length) {
      return anyString(length, NUMER.toCharArray());
    }

    private String anyString(int length, char[] chars) {

      StringBuilder sb = new StringBuilder(length);
      final int min = 0;
      final int max = chars.length - 1;
      for (int i = 0; i < length; i++) {
        int next = mRandom.nextInt();
        char c = chars[normalise(next, min, max)];
        sb.append(c);
      }
      return sb.toString();
    }

    private String anyString(int length, int min, int max) {
      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        int next = mRandom.nextInt();
        char c = (char) normalise(next, min, max);
        sb.append(c);
      }
      return sb.toString();
    }

    /** Assumes that the range of i is >> (max - min). */
    private int normalise(int i, int min, int max) {
      int mod = (max - min) + 1;
      int j = Math.abs(i);
      return (j % mod) + min;
    }
  }

  @SuppressWarnings("AutoBoxing")
  private static class FrequencyCalculator {
    private Map<Object, Integer> countByValue;
    private boolean built = false;
    private Collection<Map.Entry<Object, Integer>> countsCollection;

    public FrequencyCalculator() {
      countByValue = new HashMap<>();
    }

    public void count(Object o) {
      if (built) {
        throw new IllegalStateException("Cannot call count after calling build().");
      }
      Integer oldCount = countByValue.getOrDefault(o, Integer.valueOf(0));
      Integer newCount = oldCount + 1;
      countByValue.put(o, newCount);
    }

    public void printCounts(int i) {
      if (!built) {
        throw new IllegalStateException("Cannot printCounts() before calling build().");
      }
      countsCollection
          .stream()
          .limit(i)
          .forEach(
              entry -> {
                System.out.println("Value: " + entry.getKey() + "; Count: " + entry.getValue());
              });
    }

    private void build() {
      if (built) {
        return;
      }
      built = true;
      countsCollection =
          countByValue
              .entrySet()
              .stream()
              .sorted((t1, t2) -> t2.getValue().compareTo(t1.getValue()))
              .collect(Collectors.toList());
    }
  }
}
