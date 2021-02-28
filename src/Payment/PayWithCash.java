package Payment;

public class PayWithCash implements IPayStrategy {
    @Override
    public String pay(int _amount) {
        return "PAID_CASH";
    }
}
