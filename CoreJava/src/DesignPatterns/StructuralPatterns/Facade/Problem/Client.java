package DesignPatterns.StructuralPatterns.Facade.Problem;

public class Client {
    public static void main(String[] args) {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();

        //Task (Complexity of the client & Tight coupling (Exposing API directly to client)
        System.out.println(userService.getUserDetails("123"));
        System.out.println(orderService.getOrderDetails("456"));
        System.out.println(paymentService.getPaymentDetails("789"));
    }
}
