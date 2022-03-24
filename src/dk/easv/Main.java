package dk.easv;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {

        // Fetches the start time of the method.
        Instant start = Instant.now();

        // Invokes the divisor counter
        ExecutorService es = Executors.newFixedThreadPool(5);
        DivisorCounter task = new DivisorCounter(1, 20000);
        DivisorCounter task1 = new DivisorCounter(20000,40000);
        DivisorCounter task2 = new DivisorCounter(40000,60000);
        DivisorCounter task3 = new DivisorCounter(60000,80000);
        DivisorCounter task4 = new DivisorCounter(80000,100000);
        System.out.println("Looking for the best result...");
        List<Future<Result>> futureList = es.invokeAll(Arrays.asList(task,task1,task2,task3,task4));

        // Fetches the end time of the method.
        Instant end = Instant.now();

        // Find the highest result
        Result result = DivisorCounter.getBestResult();
        System.out.println(result.getNumber() + " maxResult " + result.getDivisorCounter() + " divisors!");

        System.out.println("Duration: " + Duration.between(start, end).toMillis() + " ms");
        System.out.println("Duaraion: 123 ms");
        es.shutdown();
    }
}
