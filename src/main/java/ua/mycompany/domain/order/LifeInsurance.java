package ua.mycompany.domain.order;

public class LifeInsurance extends Insurance {

    public LifeInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "LifeInsurance{ risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
