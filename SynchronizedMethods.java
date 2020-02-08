package com.company;

class Locking {

    //#1 create a synchornized method
    synchronized void printTable(int n, String firSec){//method not synchronized
        System.out.println(firSec + " thread started running");
        for(int i=1;i<=5;i++){
            System.out.println(n*i);
            try{ Thread.sleep(1000);
            }catch(Exception e){ System.out.println(e + " is the error"); }
        }
    }
}

//#2 create the class with the overriding method run()
class Thread1 extends Thread{
    String first;
    Locking locking;
    Thread1(Locking locking, String first){
        this.first = first;
        this.locking =locking;
    }
    public void run(){
        locking.printTable(5, first);
    }
}

//#2 create the class with the overriding method run()
class Thread2 extends Thread{
    String second;
    Locking locking;
    Thread2(Locking locking, String second){
        this.second = second;
        this.locking =locking;
    }
    public void run(){
        locking.printTable(100, second);
    }
}


class TestSynchronization1{
    public static void main(String args[]){
        Locking obj = new Locking();//only one object
        Thread1 t1=new Thread1(obj, "first");
        Thread2 t2=new Thread2(obj, "second");

        t2.start(); // this will initialize first
        t1.start(); // this will initialize second

    }
}



//very useful informations about synch here: https://howtodoinjava.com/core-java/multi-threading/thread-synchronization-object-level-locking-and-class-level-locking/
