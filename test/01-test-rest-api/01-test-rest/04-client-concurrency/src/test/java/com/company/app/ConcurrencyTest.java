package com.company.app;

import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTest {

    // Need to use CountDownLatch to wait for worker thread to complete.
    @Test
    public void long_run_test() {

        int numberOfThreads = 3;

        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Runnable task = () -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            Client client = ClientBuilder.newClient();
            Response response = client
                    .target("http://localhost:8080/long-run")
                    .request()
                    .get();
            Assert.assertEquals(response.getStatus(), 200);
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
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
