package com.company;

class Store {
    private int countToys;

    public Store() {
        countToys = 0;
    }

    public synchronized void buyToy() {
        while (countToys <= 0) {
            System.out.println("countToys <= 0");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Игрушек стало: " + countToys);
        System.out.println("Купили 1 игрушку");
        countToys--;
        System.out.println("Игрушек стало: " + countToys);

        notify();
    }

    public synchronized void deliverToy() {
        while (countToys >= 5) {
            System.out.println("countToys >= 5");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        countToys++;

        System.out.println("Доставили 1 игрушку");
        System.out.println("Игрушек стало: " + countToys);

        notify();
    }

}

class Consumer implements Runnable {
    Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                store.buyToy();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

class Deliveryman implements Runnable {
    Store store;

    public Deliveryman(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                store.deliverToy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


public class Main5 {
    public static void main(String[] args) {

        Store store = new Store();
        new Thread(new Consumer(store)).start();
        new Thread(new Deliveryman(store)).start();

    }
}
