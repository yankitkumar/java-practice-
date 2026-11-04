# try-with-resources

A simple example of how Java closes things for you.

## Run it

Java 11 or newer, no build tool needed:

```bash
java TryWithResourcesDemo.java
```

## Output

```
--- Example 1 ---
Open 1
Open 2
Using 1 and 2
Close 2
Close 1

--- Example 2: with an error ---
Open 1
Open 2
Using 1 and 2
Close 2
Close 1
Caught: Something went wrong
```

## What it shows

1. **You never call `close()`.** Java calls it for you at the end of the `try` block.
2. **Reverse order.** Opened 1 then 2, closed 2 then 1: the last one opened is closed first.
3. **Closed even on error.** When something goes wrong, both are still closed, and before the `catch` block runs.

## Interview answer

Anything that implements `AutoCloseable` can go in `try ( ... )`, and Java calls its `close()` automatically when the block ends, even if an exception is thrown. Resources are closed in reverse order, and before the catch block runs.
