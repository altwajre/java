# protocol buffers

https://developers.google.com/protocol-buffers/

## Installation

> `$ brew install protobuf`

## Java proto Tutorial

https://developers.google.com/protocol-buffers/docs/javatutorial

### Compiling Protocol Buffers

Usage

> `$ protoc -I=$SRC_DIR --java_out=$DST_DIR $SRC_DIR/addressbook.proto`

Example

> `$ protoc -I=. --java_out=. ./addressbook.proto`

> `$ ls ./out/example/tutorial/AddressBookProtos.java`
