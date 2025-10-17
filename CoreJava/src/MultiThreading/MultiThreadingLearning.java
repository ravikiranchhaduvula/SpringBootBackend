package MultiThreading;

public class MultiThreadingLearning implements Runnable {

    @Override
    public void run() {
        System.out.println("Code Executed by thread: " + Thread.currentThread().getName());
    }
}
