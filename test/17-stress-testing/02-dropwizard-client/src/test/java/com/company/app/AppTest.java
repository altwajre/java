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

public class AppTest {

  @Test
  public void long_run_test() {

    int workers = 3;
    Map<String, String> errors = new HashMap<>();
    // need to use CountDownLatch to wait for worker thread to complete.
    CountDownLatch latch = new CountDownLatch(workers);

    Runnable task = () -> {
      Client client = ClientBuilder.newClient();
      Response response = client
          .target("https://origin-api2-gcp-eu.ote-godaddy.com/v1/commerce/offers")
          .request()
          .header("Authorization", "sso-key VVXC1YsJ_BwWZ572Pap8G1WotRE5AaY:BwWb2P6yWtpevzPbwDmxig")
          .get();

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

    ExecutorService executor = Executors.newFixedThreadPool(workers);

    for (int i = 0; i < workers; i++) {
      executor.submit(task);
    }

    try {
      boolean await = latch.await(30, TimeUnit.SECONDS);
      Assert.assertEquals(await, true);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    Assert.assertEquals(errors.isEmpty(), true);

  }

}