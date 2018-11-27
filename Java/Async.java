import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Async {
    public static int ONE = 1;
    public static int TWO = 2;
    public static int FIVE = 5;

    public static void main(String[] args) throws Exception {
        int numOfThreads = ONE;
        List<Runnable> tasks = new ArrayList<>();

        for (int i=1; i<=numOfThreads; i++) {
            tasks.add(new ParallelisableTask("thread-" + i));
        }

        tasks.add(new ParallelisableTaskMonitor((ParallelisableTask) tasks.get(0)));

        System.out.println("Starting threads...");
        ExecutorService threadExecutor = Executors.newFixedThreadPool(TWO);

        for (int i=0; i<tasks.size(); i++) {
            threadExecutor.execute(tasks.get(i));
        }
        System.out.println("Threads started.");

        threadExecutor.shutdown();

        // Thread.sleep(5000); // sleep main
        // System.out.println("Forcing shutdown of threadExecutor.");
        // threadExecutor.shutdownNow();
        System.out.println("Main ends.");
    }

    static class ParallelisableTask implements Runnable {
        private static final int SLEEP_MAX_TIME = 1000;
        private int sleepTime;
        private String threadName;
        private static Random generator = new Random();
        private boolean isRunning;

        public ParallelisableTask(String name) {
            threadName = name;
            sleepTime = generator.nextInt(SLEEP_MAX_TIME);
            isRunning = false;
        }

        public void run() {
            isRunning = true;
            try {
                System.out.println(String.format("%s going to sleep for %d  millseconds", threadName, sleepTime));
                Thread.sleep(sleepTime); // simulate a long-running operation
                System.out.println(String.format("%s done sleeping", threadName));
            } catch(InterruptedException ex) {
                System.out.println(String.format("%s got interrupted and did not complete", threadName));
            } finally {
                isRunning = false;
            }
        }
    }

    static class ParallelisableTaskMonitor implements Runnable {
        private ParallelisableTask task;

        public ParallelisableTaskMonitor(ParallelisableTask task) {
            this.task = task;
        }

        public void run() {
            // try {
                System.out.println("STARTING MONITOR FOR: " + task.threadName);
                boolean taskIsRunning = false;
                do {
                    System.out.println("TASK " + task.threadName + " IS STILL RUNNING...");
                    // Thread.sleep(50);
                    // mutlti threading safe wait 
                    taskIsRunning = task.isRunning;
                } while(taskIsRunning);
                System.out.println("TASK " + task.threadName + " HAS FINISHED RUNNING.");
            // } catch (InterruptedException ex) {
                // System.out.println("PARALLELISABLETASKMONITOR GOT INTERRUPTED.");
            // }
        }
    }
}