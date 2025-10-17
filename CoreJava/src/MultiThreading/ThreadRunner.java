package MultiThreading;

public class ThreadRunner {
    public static void main(String[] args) {
        System.out.println("Go Inside Main thread: " + Thread.currentThread().getName());
        MultiThreadingLearning runnableObject = new MultiThreadingLearning();
        Thread t1 = new Thread(runnableObject);
        t1.start();
        System.out.println("Finish Main thread: " + Thread.currentThread().getName());
    }
}
