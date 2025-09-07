package Loops;

import java.util.concurrent.Executors;

//Run looped tasks concurrently.
public class VirtualThreads {
    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            String[] urls = {"One", "Two"};
            for (String url : urls) {
                executor.submit(() -> fetch(url));  // many tasks, cheap threads
            }
        }
    }

    private static Object fetch(String url) {
        System.out.println("Hello");
        return null;
    }
}
