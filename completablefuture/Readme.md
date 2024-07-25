# CompletableFuture in Java
### Definition:
CompletableFuture is a class in the `java.util.concurrent` package that represents a future result of an asynchronous computation. \
It provides a way to write non-blocking asynchronous code in Java.

### Key Features:

#### 1. Asynchronous Execution:
- Allows running tasks asynchronously without blocking the main thread.
- Tasks can be executed in parallel, making efficient use of resources.

#### 2. Composability:
- Supports chaining of multiple asynchronous tasks.
- Allows combining multiple CompletableFuture instances using methods like `thenCombine()`, `thenCompose()`, etc.

#### 3. Callback Mechanism:
- Provides callback methods to handle the result of the asynchronous computation, such as `thenApply()`, `thenAccept()`, and `thenRun()`.

#### 4. Exception Handling:
- Supports handling exceptions in asynchronous tasks using methods like `exceptionally()`, `handle()`, and `whenComplete()`.