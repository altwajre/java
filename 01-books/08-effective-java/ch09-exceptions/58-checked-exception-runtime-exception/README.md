# Use checked exceptions for recoverable conditions and runtime exceptions for programming errors

http://beginnersbook.com/2013/04/java-checked-unchecked-exceptions-with-examples/

## Checked exceptions

```
Use checked exceptions for conditions from which the caller can reasonably be expected to recover
By throwing a checked exception, you force the caller to handle the exception in a catch clause or to propagate it
out ward.
```

## Runtime exceptions (unchecked exceptions)

```
Use runtime exceptions to indicate programming errors
The great majority of runtime exceptions indicate precondition violations. A precondition violation is simply a failure
by the client of the API to adhere to the contract established by the API specification.
For example, the contract for array access specifies that the array index must be between zero and the array length 
minus one. ArrayIndexOutOfBoundsException indicates that this precondition was violated.
All of the unchecked throw-ables you implement should subclass RuntimeException. 
```