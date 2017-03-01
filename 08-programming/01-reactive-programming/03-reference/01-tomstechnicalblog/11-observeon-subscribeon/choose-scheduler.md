# Choosing a Scheduler

Schedulers.io() is optional for IO-related tasks (and it caches and re-uses threads to increase efficiency). 

Schedulers.newThread() which simply creates a new thread for each subscription.

You have to be careful with both of these because in theory they could create an unlimited number of threads (this can cause bad performance). 
For computational tasks, you should use Schedulers.computation() so the number of threads are limited based on the number of cores your machine has.
