package oops;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    // Storing, Making payments
    Map<String, PaymenteMethod> paymentMethods;

    public PaymentService() {
        paymentMethods = new HashMap<>();
    }

    public void addPaymentMethod(String name, PaymenteMethod paymenteMethod) {
        paymentMethods.put(name, paymenteMethod);
    }

    public void makePayment(String name) {
        PaymenteMethod pm = paymentMethods.get(name);
        pm.pay();
    }
}
