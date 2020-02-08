package com.company;

public class Counter{
    long count;
    synchronized void add(){
        count++;
    }
}
 class CounterThread extends Thread{
    Counter counter = null;
    CounterThread(Counter counter){
        this.counter = counter;
    }

    public synchronized void run() {
        for(int i=0; i<10; i++){
            counter.add();
            System.out.println(i);
        }
    }

     public static void main(String[] args) {
         Counter counterA = new Counter();
         Counter counterB = new Counter();

         Thread threadA = new CounterThread(counterA);
         Thread threadB = new CounterThread(counterB);

         threadA.start();
         threadB.start();
     }
}
