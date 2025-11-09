package com.demo;

class Car extends Thread {
    public String createCar(int i) {
        return "Car is running : " + i;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(createCar(i));
        }
    }
}

class Bus implements Runnable {

    public String createBus(int i) {
        return "Bus is running : " + i;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(createBus(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Restore interrupt status and exit loop (cooperative interruption)
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

public class Demo {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(" --- " + Thread.currentThread().getName() + " --- ");

        Car c = new Car();
        c.setName("Thread-1");
        c.start();

        System.out.println("Thread : " + c.getName() + ", is alive : " + c.isAlive());

        // yield is a static hint to the scheduler
        Thread.yield();
        System.out.println("Thread : " + c.getName() + ", is alive : " + c.isAlive());

        Thread t1 = new Thread(new Bus());
        t1.setName("Thread-2");
        t1.start();

        System.out.println("Thread : " + t1.getName() + ", is alive : " + t1.isAlive());
        Thread.yield();
        System.out.println("Thread : " + t1.getName() + ", is alive : " + t1.isAlive());

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Lorry is running : " + i);
            }
        }, "Thread-3");

        t3.start();
        System.out.println("Thread : " + t3.getName() + ", is alive : " + t3.isAlive());
        Thread.yield();
        System.out.println("Thread : " + t3.getName() + ", is alive : " + t3.isAlive());

        // Wait for all threads to finish
        c.join();
        t1.join();
        t3.join();

        System.out.println(" --- " + Thread.currentThread().getName() + " --- ");
    }
}
