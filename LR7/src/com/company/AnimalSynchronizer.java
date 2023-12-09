package com.company;

import java.util.Random;

public class AnimalSynchronizer {
    public Animal  i;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public AnimalSynchronizer(Animal i) {
        this.i = i;
    }

    public int read() throws InterruptedException {
        int val;
        synchronized(lock) {
            if (!canRead()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = i.getOneScore(current);
            System.out.println("Read: " + val  + " to position " + current);
            set = false;
            current++;
            lock.notifyAll();
        }
        return val;
    }

    public void write() throws InterruptedException {
        synchronized(lock) {
            if (!canWrite()) throw new InterruptedException();
            while (set)
                lock.wait();
            Random rnd = new Random();
            i.setOneScore(current, rnd.nextInt(100));
            System.out.println("Write: " + i.getOneScore(current) + " to position " + current);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canRead() {
        return current < i.getScoresLen();
    }

    public boolean canWrite() {
        return (!set && current < i.getScoresLen()) || (set && current < i.getScoresLen() - 1);
    }
}
