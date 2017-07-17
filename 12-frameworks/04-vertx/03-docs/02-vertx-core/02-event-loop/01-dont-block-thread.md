# Don't block thread!

With very few exceptions (i.e. some file system operations ending in 'Sync'), none of the APIs in Vert.x block the calling thread.

If a result can be provided immediately, it will be returned immediately, otherwise you will usually provide a handler to receive events some time later.

Because none of the Vert.x APIs block threads that means you can use Vert.x to handle a lot of concurrency using just a small number of threads.
