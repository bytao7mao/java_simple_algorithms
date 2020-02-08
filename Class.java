public class Class {

// Java provides a class with name Class in java.lang package.
// Instances of the class Class represent classes and interfaces in a running Java application.
// The primitive Java types (boolean, byte, char, short, int, long, float, and double),
// and the keyword void are also represented as Class objects. It has no public constructor.
// Class objects are constructed automatically by the Java Virtual Machine(JVM).
// It is a final class, so we cannot extend it.



    public static void main(String[] args) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException {

        Class cl_class = Class.forName("Chicken");
        Class cl_int = int.class;
        Class cl_string = Class.forName("java.lang.String");
        Class cl_void = void.class;
        ClassLoader loader = cl_class.getClassLoader();
        Class chickenLoader = Class.forName("Chicken", true, loader);

        System.out.println(chickenLoader.toString());
        System.out.println("What is this class ? "
                + cl_class.getSimpleName());

        Class cl_main = Class.forName("Main");
        Class cl_obj = Class.forName("java.lang.Object");
        Class chickClass = Class.forName("Chicken");
        Object newChicken = chickClass.newInstance();
        System.out.println("Type of newChiken: " + newChicken.getClass());

        String s = "Kaizen";
        int ten = 10_000;
        System.out.println("kaizen is string ? " +
                cl_string.isInstance(s) + "\n" +
        "ten is String ? " + cl_string.isInstance(ten));
        System.out.println("Object is assignable from Main ? "
                + cl_obj.isAssignableFrom(cl_main));

        Class fish = Fish.class;
        System.out.println("is fish primitive ? " +
                fish.isPrimitive());
        System.out.println(fish.getSimpleName());

    }
}
// inspiration from:
//https://www.geeksforgeeks.org/java-lang-class-class-java-set-1/
