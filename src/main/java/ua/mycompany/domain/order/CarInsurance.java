package ua.mycompany.domain.order;

public class CarInsurance extends Insurance {

    public CarInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "CarInsurance { id = " + id +", risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
