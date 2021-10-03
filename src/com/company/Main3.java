package com.company;

import java.util.concurrent.Exchanger;

class Thread1 implements Runnable{
    private Exchanger<String> exchanger;

    public Thread1(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String output = exchanger.exchange("hui");
                System.out.println("Thread1 output = " + output);
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Thread2 implements Runnable{
    private Exchanger<String> exchanger;

    public Thread2(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String output = exchanger.exchange("pizda");
                System.out.println("Thread2 output = " + output);
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class Main3 {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Thread1(exchanger)).start();
        new Thread(new Thread2(exchanger)).start();

        while (true){
            try{
                String output = exchanger.exchange("Sdfsdf");
                System.out.println("Main output = " + output);
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
