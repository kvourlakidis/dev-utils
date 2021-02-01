package misc;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Runner {
  private static final String PATH = "PATH";
  private static final String SEPARATOR = ":";

  public static void main(String[] args) {
    System.out.println("Hello from misc.Runner.");
    // List<String> command = tutorials.collections.Arrays.asList("moomin");
    // List<String> command =
    // tutorials.collections.Arrays.asList("/Users/kyrilosvourlakidis/tmp/moomin");
    List<String> command = Arrays.asList("moomin");
    // List<String> command = tutorials.collections.Arrays.asList("env");
    File runFrom = new File("/Users/kyrilosvourlakidis");
    runCommand(command, null, runFrom);
  }

  private static int runCommand(
      List<String> commandWithArgs,
      List<String> environmentAssignments,
      File directoryToRunFrom,
      boolean print) {
    final ProcessBuilder pb = new ProcessBuilder(commandWithArgs);
    Map<String, String> environment = pb.environment();
    // print(environment);
    // String path = environment.get(PATH);
    // System.out.println("PATH=" + path);
    // path = "/Users/kyrilosvourlakidis/tmp" + SEPARATOR + path;
    // environment.put(PATH, path);
    // System.out.println("PATH=" + environment.get(PATH));
    if (environment != null) environment.clear();
    environment.put("FOO", "BAR");
    environment.put("PATH", "/Users/kyrilosvourlakidis/tmp");
    if (directoryToRunFrom != null) {
      pb.directory(directoryToRunFrom);
    }
    pb.redirectErrorStream(true);
    pb.redirectOutput(ProcessBuilder.Redirect.PIPE);
    if (print) {
      System.out.println("command " + commandWithArgs);
    }
    try {
      Process process = pb.start(); // throws IOException
      if (print) {
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        while ((line = br.readLine()) != null) // throws IOException
        {
          System.out.println(line);
        }
        br.close(); // throws IOException
      }
      process.waitFor(); // throws InterruptedException
      int exitValue = process.exitValue();
      if (print) {
        System.out.println("exit value " + exitValue);
      }
      return exitValue;
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    return -1;
  }

  private static int runCommand(
      List<String> commandWithArgs, List<String> environmentAssignments, File directoryToRunFrom) {
    return runCommand(commandWithArgs, environmentAssignments, directoryToRunFrom, true);
  }

  private static void print(Map<String, String> map) {
    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println(entry.getKey());
      System.out.println(entry.getValue());
    }
  }

  // List<String> getEnv() {
  //     def env = []
  //     System.env.each { k,v ->
  //         if (k != "PATH") {
  //             env.add("$k=$v")
  //         }
  //     }
  //     def prependedPath = nodeBinsDir + System.getProperty("path.separator") + System.env.PATH
  //     env.add("PATH=${prependedPath}")
  //     return env
  // }
}
