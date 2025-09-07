package Reflection;

import FunctionalInterface.ConcreteImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //1. With class.forName - Fully qualified
        Class<?> concreteClass = Class.forName("FunctionalInterface.ConcreteImpl");
        // 1. Public methods (including inherited ones, like from Object)
        System.out.println("Public methods:");
        for (var method : concreteClass.getMethods()) {
            System.out.println(method.getName());
        }

        // 2. Declared methods (all methods declared in this class only,
        //    regardless of modifier, but not inherited)
        System.out.println("\nDeclared methods:");
        for (var method : concreteClass.getDeclaredMethods()) {
            System.out.println(method.getName());
            System.out.println(method.getReturnType());
            System.out.println(method.getDeclaringClass());
        }

        for (var field : concreteClass.getDeclaredFields()) {
            System.out.println(field.getName());
            field.setAccessible(true);//Private field modification
            if (field.getName().equals("breed")) {
                field.set(field, "Ravi");   // set String value
            }
            if (field.getName().equals("canSwim")) {
                field.set(field, true);     // set boolean value
            }
            System.out.println(field.getType());
            System.out.println(Modifier.toString(field.getModifiers()));
        }

        // Second way - Using .class
        Class<?> concreteClass2 = ConcreteImpl.class;
        System.out.println("Second way..."+concreteClass2.getName());

        // Second way - Using getClass
        ConcreteImpl impl = new ConcreteImpl();
        Class<?> concreteClass3 =impl.getClass();

// Create instance (uses no-arg constructor)
        Object eagleObj = concreteClass3.getDeclaredConstructor().newInstance();

// Now invoke a method (e.g., canFly(String))
        Method canFly = concreteClass3.getDeclaredMethod("canFly", String.class);
        canFly.invoke(eagleObj, "Eagle");
    }
}
