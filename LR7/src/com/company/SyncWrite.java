package com.company;

public class SyncWrite implements Runnable {
    private AnimalSynchronizer sync;

    public SyncWrite(AnimalSynchronizer s) {
        sync = s;
    }

    @Override
    public void run() {
        try {
            for (int i=0; i<sync.i.getScoresLen();i++) {
                sync.write();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
