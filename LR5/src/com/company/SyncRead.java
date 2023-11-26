package com.company;

public class SyncRead implements Runnable {
    private AnimalSynchronizer sync;

    public SyncRead(AnimalSynchronizer s) {
        sync = s;
    }
    @Override
    public void run() {
        try {
            for (int i=0; i<sync.i.getScoresLen();i++) {
                sync.read();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
