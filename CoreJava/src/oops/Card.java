package oops;

public class Card implements PaymenteMethod {
    protected String cardNo;
    private String userName;

    public Card(String cardNo, String userName) {
        this.cardNo = cardNo;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Override
    public void pay() {

    }
}
