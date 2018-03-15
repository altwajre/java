package com.company.app;

import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {

    @Test
    public void long_run_test() {

        int numberOfThreads = 3;
        Map<String, String> errors = new HashMap<>();
        // need to use CountDownLatch to wait for worker thread to complete.
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Runnable task = () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            Client client = ClientBuilder.newClient();
            Response response = client
                    .target("http://localhost:8080/long-run")
                    .request()
                    .get();

//            Assert.assertEquals(response.getStatus(), 200); // DO NOT user assert in worker thread

            // Collecting errors and assert them in main thread
            if (response.getStatus() != 200) {
                String threadName = Thread.currentThread().getName();
                errors.put(threadName, String.format("StatusCode=%s", response.getStatus()));
            }

            response.bufferEntity();
            String responseBody = response.readEntity(String.class);
            System.out.println(responseBody);

            latch.countDown();
        };


        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(task);
        }

        try {
            boolean await = latch.await(5, TimeUnit.SECONDS); // timeout after 5 seconds. will fail after change it to 1 second
            Assert.assertEquals(await, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(errors.isEmpty(), true);

    }
}
