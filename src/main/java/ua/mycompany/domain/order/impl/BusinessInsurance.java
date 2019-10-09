package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Insurance;

public class BusinessInsurance extends Insurance {

    public BusinessInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "BusinessInsurance{ id = " + id + ", risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
