package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Insurance;

public class LifeInsurance extends Insurance {

    public LifeInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "LifeInsurance{ id = " + id + ", risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
