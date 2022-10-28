package org.example;

import java.io.IOException;

/**
 * Processor of HTTP request.
 */

public class PoolThreadRunnable
        extends Thread
{
    private ThreadPool queue;

    public void StartProcessor(Input input) throws IOException {
        Processor proc = new Processor(input.socket, input.request);
        proc.process();
    }

    private int threadNumber;
    public PoolThreadRunnable(int threadNumber) {
        this.threadNumber = threadNumber;

    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i ++ ) {
            System.out.println(i + " from thread " + threadNumber);
        }
        try {
            while (true) {
                // Wait for new element.
                Input elem = queue.pop();

                // Stop consuming if null is received.
                if (elem == null) {
                    return;
                }

                // Process element.
                StartProcessor(elem);
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}