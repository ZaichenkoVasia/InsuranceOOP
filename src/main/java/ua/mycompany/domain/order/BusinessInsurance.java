package ua.mycompany.domain.order;

public class BusinessInsurance extends Insurance {

    public BusinessInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "BusinessInsurance{ risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
