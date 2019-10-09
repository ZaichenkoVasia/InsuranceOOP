package ua.mycompany.domain.order.impl;

import ua.mycompany.domain.order.Insurance;

public class HouseInsurance extends Insurance {

    public HouseInsurance(double risk, double price, double payment) {
        super(risk, price, payment);
    }

    @Override
    public String toString() {
        return "HouseInsurance{id = " + id + ", risk=" + risk + ", price=" + price + ", payment=" + payment + '}';
    }
}
