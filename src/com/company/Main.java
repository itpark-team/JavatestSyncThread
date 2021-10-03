package com.company;

class MyRunnable implements Runnable {

    private boolean isRun;

    public MyRunnable() {
        isRun = true;
    }

    @Override
    public void run() {
        while (isRun == true) {
            System.out.println("hello world");
        }
    }

    public void stopThread() {
        isRun = false;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        while (isInterrupted() == false) {
            System.out.println("hello world");
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*MyRunnable myRunnable = new MyRunnable();
        Thread th1 = new Thread(myRunnable);
        th1.start();*/

        MyThread th1 = new MyThread();
        th1.start();

        Thread.sleep(1000);
        th1.interrupt();
        //myRunnable.stopThread();
    }
}
