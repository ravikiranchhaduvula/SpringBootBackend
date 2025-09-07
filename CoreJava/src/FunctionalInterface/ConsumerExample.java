package FunctionalInterface;

import java.util.function.Consumer;

// Predefined functional interface
public class ConsumerExample {
    public static void main(String[] args) {
        //Create a Consumer<Integer> whose accept() method is implemented by this lambda body
        /**
         * Consumer<Integer> loggingObject = new Consumer<Integer>() {
         *     @Override
         *     public void accept(Integer val) {
         *         // body
         *     }
         * };
         */
        Consumer<Integer> loggingObject = (Integer val) -> {
          if (val > 10) {
              System.out.println("Logging");
          }
        };
        loggingObject.accept(11);
    }
}
