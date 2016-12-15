package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;

public class VertxUnitTest {
    public static void main(String... args){
        new VertxUnitTest().run();
    }

    Vertx vertx;

    public void run(){
        TestOptions options = new TestOptions().addReporter(new ReportOptions().setTo("console"));
        TestSuite suite = TestSuite.create("com.company.app.VertxUnitTest");

        suite.before(context -> {
            vertx = Vertx.vertx();
            vertx.createHttpServer().requestHandler(req -> req.response().end("foo")).listen(8080, context.asyncAssertSuccess());
        });

        suite.after(context -> {
            vertx.close(context.asyncAssertSuccess());
        });

        suite.test("Some_test1", context -> {
            HttpClient client = vertx.createHttpClient();
            Async async = context.async();
            client.getNow(8080, "localhost", "/", resp -> {
                resp.bodyHandler(body -> context.assertEquals("foo", body.toString("UTF-8")));
                client.close();
                async.complete();
            });
        });
        suite.run(options);
    }
}
/*
https://github.com/vert-x3/vertx-examples/blob/master/unit-examples/src/test/java/io/vertx/example/unit/test/VertxUnitTest.java

1, run VertxUnitTest.main()
 */