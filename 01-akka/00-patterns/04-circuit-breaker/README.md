# Circuit Breaker

https://doc.akka.io/docs/akka/current/common/circuitbreaker.html

During normal operation, a circuit breaker is in the Closed state:

- Exceptions or calls exceeding the configured callTimeout increment a failure counter
- Successes reset the failure count to zero
- When the failure counter reaches a maxFailures count, the breaker is tripped into Open state

While in Open state:

- All calls fail-fast with a CircuitBreakerOpenException
- After the configured resetTimeout, the circuit breaker enters a Half-Open state

In Half-Open state:

- The first call attempted is allowed through without failing fast
- All other calls fail-fast with an exception just as in Open state
- If the first call succeeds, the breaker is reset back to Closed state and the resetTimeout is reset
- If the first call fails, the breaker is tripped again into the Open state (as for exponential backoff circuit breaker, the resetTimeout is multiplied by the exponential backoff factor)

State transition listeners:

- Callbacks can be provided for every state entry via onOpen, onClose, and onHalfOpen
- These are executed in the ExecutionContext provided.

Calls result listeners:

- Callbacks can be used eg. to collect statistics about all invocations or to react on specific call results like success, failures or timeouts.
- Supported callbacks are: onCallSuccess, onCallFailure, onCallTimeout, onCallBreakerOpen.
- These are executed in the ExecutionContext provided.

## youtube

- Akka Circuit Breakers

https://www.youtube.com/watch?v=FLw95uX3mkU
