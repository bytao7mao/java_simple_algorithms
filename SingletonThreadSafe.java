//we will use in this example Singleton pattern
public class Singleton {
    private final static Object key = new Object();
    private static volatile Singleton instance;
    //constructor private to not be able to create type of it
    private final String nameOfInstance;
    private Singleton(String nameOfInstance){
        this.nameOfInstance = nameOfInstance;
    }
    public static void checkInstance(){
        System.out.println((instance != null) ?
                "(yes) instance created named: " + instance.nameOfInstance :
                "instance null (no)");
    }
    public static Singleton getInstance(String nameOfInstance) {
        //every java object has a lock method (with a key)
        //synchronizing means protecting this method by not
        //letting other threads to run at the same time

        //if instance is not null
        //we read it and return it
        //type: non-sync read
        if (instance != null) {
            return instance;
        }
        //if instance is null
        //we create it by writing it
        //type: sync write
        synchronized (key) {
            //read operation
            if (instance == null) {
                //write operation
                instance = new Singleton(nameOfInstance);
            }
            return instance;
        }//end of sync key
    }//end of getInstance method
}//end of Singleton class
