package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Insurance;

public class MedicalInsurance extends Insurance {

    public MedicalInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "MedicalInsurance{ id = " + id + ", risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
