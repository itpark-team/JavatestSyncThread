package com.company;

import java.util.concurrent.Exchanger;

class DataBus {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

class Thread11 implements Runnable{

    private DataBus dataBus;

    public Thread11(DataBus dataBus) {
        this.dataBus = dataBus;
    }

    @Override
    public void run() {
        while (true) {
            try {
                dataBus.setObject("hui");
                System.out.println("Thread1 write hui to databus");

                String output = (String) dataBus.getObject();
                System.out.println("Thread1 read from databus = " + output);

                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class Thread22 implements Runnable{
    private DataBus dataBus;

    public Thread22(DataBus dataBus) {
        this.dataBus = dataBus;
    }

    @Override
    public void run() {
        while (true) {
            try {
                dataBus.setObject("pizda");
                System.out.println("Thread2 write pizda to databus");

                String output = (String) dataBus.getObject();
                System.out.println("Thread2 read from databus = " + output);

                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class Main4 {
    public static void main(String[] args) {
        DataBus dataBus = new DataBus();

        new Thread(new Thread11(dataBus)).start();
        new Thread(new Thread22(dataBus)).start();
    }
}
