# try-with-resources

A runnable example of how Java's try-with-resources closes things for you.

## Run it

Java 11 or newer, no build tool needed:

```bash
java TryWithResourcesDemo.java
```

## Output

```
--- Example 1: everything works ---
1. Connection opened
2. Running: SELECT * FROM accounts
3. Connection closed

--- Example 2: an error happens ---
1. Connection opened
2. Running: SELECT * FROM accounts
3. Connection closed
4. Caught: query failed

--- Example 3: two resources ---
open connection
open statement
using both
close statement
close connection

--- Example 4: the code fails AND close() fails ---
open connection
close connection
Caught: query failed
Suppressed: could not close connection
```

## What each example shows

1. **Automatic close.** `close()` is never called in the code, but the connection is closed at the end of the `try` block.
2. **Closed even on error.** The connection is closed before the `catch` block runs (step 3 comes before step 4).
3. **Reverse order.** Resources are closed last-opened-first, because later resources usually depend on earlier ones (a statement depends on its connection).
4. **Suppressed exceptions.** When the code and `close()` both fail, you catch the real error, and the close error is kept inside it, available through `getSuppressed()`.

## Interview answer

The compiler turns try-with-resources into a try/finally that calls `close()` automatically on anything that implements `AutoCloseable`. Resources are closed in reverse order, before the catch block runs. If both the body and `close()` throw, the body's exception is kept and the close exception is added to it as suppressed, so the real error is never lost.
