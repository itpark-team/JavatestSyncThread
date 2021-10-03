package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

class SaveNumberToFile {
    private int number;
    private String filename;

    public SaveNumberToFile(int number, String filename) {
        this.number = number;
        this.filename = filename;
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Thread.sleep(500);
            bufferedWriter.write(Integer.toString(number));

            bufferedWriter.close();

            System.out.println("done "+number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumber() {
        return number;
    }
}

class ThreadSaveNumberToFile implements Runnable{
    private SaveNumberToFile s;
    //private Object synchronObject;
    private ReentrantLock locker;

    /*public ThreadSaveNumberToFile(SaveNumberToFile s, Object synchronObject) {
        this.s = s;
        this.synchronObject = synchronObject;
    }*/

    public ThreadSaveNumberToFile(SaveNumberToFile s, ReentrantLock locker) {
        this.s = s;
        this.locker = locker;
    }

    @Override
    public void run() {
        //s.save();

        /*System.out.println("NO SYNHRON "+s.getNumber());

        synchronized (synchronObject) {
            s.save();
        }*/

        System.out.println("NO SYNHRON "+s.getNumber());

        locker.lock();
        s.save();
        locker.unlock();
    }
}

public class Main2 {
    public static void main(String[] args) {
        //Object synchronObject = new Object();

        ReentrantLock locker = new ReentrantLock();

        SaveNumberToFile s1 = new SaveNumberToFile(1, "output.txt");
        SaveNumberToFile s2 = new SaveNumberToFile(2, "output.txt");
        SaveNumberToFile s3 = new SaveNumberToFile(3, "output.txt");

        /*ThreadSaveNumberToFile ts1 = new ThreadSaveNumberToFile(s1, synchronObject);
        ThreadSaveNumberToFile ts2 = new ThreadSaveNumberToFile(s2, synchronObject);
        ThreadSaveNumberToFile ts3 = new ThreadSaveNumberToFile(s3, synchronObject);*/

        ThreadSaveNumberToFile ts1 = new ThreadSaveNumberToFile(s1, locker);
        ThreadSaveNumberToFile ts2 = new ThreadSaveNumberToFile(s2, locker);
        ThreadSaveNumberToFile ts3 = new ThreadSaveNumberToFile(s3, locker);


        Thread th1 = new Thread(ts1);
        Thread th2 = new Thread(ts2);
        Thread th3 = new Thread(ts3);

        th1.start();
        th2.start();
        th3.start();

        System.out.println("done all");
    }
}
