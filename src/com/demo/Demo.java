package com.demo;

class Mythread extends Thread{

    int count;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" : is running");
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+" : is running inside Sync this");
            for (int i = 0; i < 100; i++) {
                count++;
            }
            this.notify();
            System.out.println(Thread.currentThread().getName()+" : is exit from sync this");
        }
        System.out.println(Thread.currentThread().getName()+" : is exit");
    }
}

public class Demo{
    static void main(String[] args) throws InterruptedException {
        Mythread mt = new Mythread();
        mt.start();

        System.out.println(Thread.currentThread().getName()+" : is running in main method");
        synchronized (mt){
            System.out.println(Thread.currentThread().getName()+" : is running inside Sync mt");
            mt.wait();
            System.out.println("COUNT IS: "+mt.count);
            System.out.println(Thread.currentThread().getName()+" : is exit from Sync mt");
        }
        System.out.println(Thread.currentThread().getName()+" : is exit from main method");


    }
}