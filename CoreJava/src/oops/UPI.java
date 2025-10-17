package oops;

public class UPI implements PaymenteMethod{
    String upiID;

    public UPI(String upiID) {
        this.upiID = upiID;
    }

    @Override
    public void pay() {
        System.out.println("Making payment using: "+upiID);
    }
}
