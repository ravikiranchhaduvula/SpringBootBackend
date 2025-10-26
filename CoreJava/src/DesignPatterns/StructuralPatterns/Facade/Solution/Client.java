package DesignPatterns.StructuralPatterns.Facade.Solution;

public class Client {
    // Client should only know about api gateway
    public static void main(String[] args) {
        //Client code is simplified
        //Client is decoupled from the internal structure
        //Centralised control (Security, Logging, Rate Limiting)
        //Consistency (Internal API can evolve over time, But API call won't change for client to run)
        APIGateway apiGateway = new APIGateway();
        System.out.println(apiGateway.getFullOrderDetails("123", "456", "789"));
    }
}
