package ua.mycompany.domain.order;

public class MedicalInsurance extends Insurance {

    public MedicalInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "MedicalInsurance{ risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
