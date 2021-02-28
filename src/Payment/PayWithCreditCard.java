package Payment;

public class PayWithCreditCard implements IPayStrategy {
    @Override
    public String pay(int _amount) {
        return "PAID_CREDIT_CARD";
    }
}
