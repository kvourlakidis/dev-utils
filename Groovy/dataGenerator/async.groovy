import java.text.NumberFormat
import java.util.concurrent.Executors
import submit_test

final int numOfThreads = 100
def tasks = []
numOfThreads.times { tasks.add(new ParallelisableTask("thread ${it + 1}")) }
println "Starting ${numOfThreads} threads."
def threadExecutor = Executors.newFixedThreadPool(numOfThreads)
tasks.each { threadExecutor.execute(it) }
println "Threads started."
threadExecutor.shutdown()


class ParallelisableTask implements Runnable {
    private int sleepTime
    private String name
    private static Random generator = new Random()

    public ParallelisableTask(String name) {
        this.name = name
        sleepTime = generator.nextInt(1000)
    }

    public void run() {
        try {
            System.out.println(String.format("%s going to sleep for %d  millseconds", name, sleepTime));
            Thread.sleep(sleepTime); // simulate a long-running operation
            System.out.println(String.format("%s done sleeping", name));
            def startTime = System.currentTimeMillis()
            def numRequests = 10
            def numPerRequest = 1
            new submit_test().submitPeople(numRequests, numPerRequest)
            println "${name} execution time: ${ NumberFormat.getNumberInstance(Locale.US).format(System.currentTimeMillis() - startTime) } ms"
        } catch (InterruptedException ex) {
            println "${name} got interupted"
        }
    }
}