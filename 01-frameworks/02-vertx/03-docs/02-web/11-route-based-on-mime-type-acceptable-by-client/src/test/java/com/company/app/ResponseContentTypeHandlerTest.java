package com.company.app;

import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.WebTestBase;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import org.junit.Test;

import static io.vertx.core.http.HttpHeaders.CONTENT_LENGTH;
import static io.vertx.core.http.HttpHeaders.CONTENT_TYPE;

public class ResponseContentTypeHandlerTest extends WebTestBase {
  private Route testRoute;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    router.route().handler(ResponseContentTypeHandler.create());
    // Added to make sure ResponseContentTypeHandler works well with others
    router.route().handler(ResponseTimeHandler.create());
    testRoute = router.route("/test");
  }

  @Test
  public void testExistingHeader() {
    testRoute.produces("application/json").handler(rc -> rc.response().putHeader(CONTENT_TYPE, "text/plain").end());
    client.get(testRoute.getPath(), resp -> {
      assertEquals("text/plain", contentType(resp));
      testComplete();
    }).putHeader(HttpHeaders.ACCEPT, "application/json").end();
    await();
  }

  @Test
  public void testNoContent() {
    testRoute.produces("application/json").handler(rc -> {
      rc.response().end();
    });
    client.get(testRoute.getPath(), resp -> {
      assertNull(contentType(resp));
      assertEquals(Integer.valueOf(0), contentLength(resp));
      testComplete();
    }).putHeader(HttpHeaders.ACCEPT, "application/json").end();
    await();
  }

  private String contentType(HttpClientResponse resp) {
    return resp.getHeader(CONTENT_TYPE);
  }

  private Integer contentLength(HttpClientResponse resp) {
    String header = resp.getHeader(CONTENT_LENGTH);
    return header == null ? null : Integer.parseInt(header);
  }
}
