package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/*
http://vertx.io/docs/vertx-core/java/#_buffers

Buffers
Most data is shuffled around inside Vert.x using buffers.

A buffer is a sequence of zero or more bytes that can read from or written to and which expands automatically as necessary
to accommodate any bytes written to it. You can perhaps think of a buffer as smart byte array.
 */
public class App {
  public static void main(String[] args) {

    createEmptyBuffer();

    createBufferFromString();

    createBufferFromStringEncoded();

    createBufferFromByte();

    createBufferWithSize();

    writeToBuffer();

    randomAccessBufferWrites();

    readFromBuffer();
  }

  private static void readFromBuffer() {

    System.out.println("#readFromBuffer");
    Buffer buffer = Buffer.buffer();
    buffer
        .appendInt(1)
        .appendInt(2)
        .appendInt(3);

    System.out.println("buffer=" + buffer);
    System.out.println("buffer.length()=" + buffer.length());
    for (int i = 0; i < buffer.length(); i++) {
      System.out.println(buffer.getInt(i));
    }
  }

  private static void randomAccessBufferWrites() {

    System.out.println("#randomAccessBufferWrites");
    Buffer buffer = Buffer.buffer();
    buffer.setInt(28, 123);
    buffer.setString(8, "hello");
    System.out.println(buffer);
  }

  private static void writeToBuffer() {

    System.out.println("#writeToBuffer");
    Vertx vertx = Vertx.vertx();
    NetServer netServer = vertx.createNetServer();
    netServer.connectHandler(socket -> {

      Buffer buffer = Buffer.buffer();
      buffer
          .appendInt(123)
          .appendString("hello\n");
      System.out.println(buffer);

      socket.write(buffer);

    });

//        netServer.listen(ar -> {
//            System.out.println(ar);
//        });
  }

  private static void createBufferWithSize() {

    System.out.println("#createBufferWithSize");
    Buffer buffer = Buffer.buffer(10000);
    System.out.println(buffer);
  }

  private static void createBufferFromByte() {

    System.out.println("#createBufferFromByte");
    byte[] bytes = new byte[]{1, 3, 5};
    Buffer buffer = Buffer.buffer(bytes);
    System.out.println(buffer);
  }

  private static void createBufferFromStringEncoded() {

    System.out.println("#createBufferFromStringEncoded");
    Buffer buffer = Buffer.buffer("some string", "UTF-16");
    System.out.println(buffer);
  }

  private static void createBufferFromString() {

    System.out.println("#createBufferFromString");
    Buffer buffer = Buffer.buffer("some string");
    System.out.println(buffer);
  }

  private static void createEmptyBuffer() {

    System.out.println("#createEmptyBuffer");
    Buffer buffer = Buffer.buffer();
    System.out.println(buffer);
  }
}
/*
output:
#createEmptyBuffer

#createBufferFromString
some string
#createBufferFromStringEncoded
�� s o m e   s t r i n g
#createBufferFromByte

#createBufferWithSize

#writeToBuffer
#randomAccessBufferWrites
        hello                  {
#readFromBuffer
buffer=         
buffer.length()=12
1
256
65536
16777216
2
512
131072
33554432
3
768
196608
50331648
 */
