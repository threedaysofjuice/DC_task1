package org.example;
import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private final Queue<Input> queue;

    public ThreadPool() {
        this.queue = new LinkedList<>();
    }

    // Put element in the queue.
    public synchronized void add(Input elem) {
        queue.add(elem);
        notify();
    }

    // Wait for new element in the queue and return it.
    public synchronized Input pop() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return this.queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }
}