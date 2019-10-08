package ua.mycompany.domain.order;

public class HouseInsurance extends Insurance {

    public HouseInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "HouseInsurance{ risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
