public class MyClass {


    public synchronized void log1(String msg1, String msg2){
        System.out.println(msg1);
        System.out.println(msg2);
    }


    public void log2(String msg1, String msg2){
        synchronized(this){
            System.out.println(msg1);
            System.out.println(msg2);
        }
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.log1("one","two");
        myClass.log2("three", "four");
    }
