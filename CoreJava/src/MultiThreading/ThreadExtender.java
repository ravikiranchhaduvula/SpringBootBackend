package MultiThreading;

public class ThreadExtender extends Thread {
    @Override
    public void run() {
        System.out.println("Code Executed by thread: " + Thread.currentThread().getName());
    }
}
