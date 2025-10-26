package DesignPatterns.StructuralPatterns.Facade.Solution;

public class APIGateway {
    UserService userService;
    OrderService orderService;
    PaymentService paymentService;

    public APIGateway() {
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }

    public String getFullOrderDetails(String userId, String orderId, String paymentId) {
        String userDetails = userService.getUserDetails(userId);
        String orderDetails = orderService.getOrderDetails(orderId);
        String processPayment = paymentService.processPayment(paymentId);

        return userDetails + "\n" + orderDetails + "\n" + processPayment;
    }
}
