package ua.mycompany.domain.order;

import ua.mycompany.exception.InsuranceUncorrectedDataRuntimeException;

import java.util.Objects;

public abstract class Insurance implements Comparable<Insurance>{

    protected final double risk;
    protected final double price;
    protected final double payment;
    protected final Long id;
    protected static Long counter = 0L;

    public Insurance(double risk, double price, double payment) {
        if(risk<0 || risk > 1 || price <0 || payment<0){
            throw new InsuranceUncorrectedDataRuntimeException("Uncorrected value of insurance");
        }
        this.risk = risk;
        this.price = price;
        this.payment = payment;
        this.id = ++counter;
    }

    public double getRisk() {
        return risk;
    }

    public double getPrice() {
        return price;
    }

    public double getPayment() {
        return payment;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Insurance insurance = (Insurance) o;
        return Double.compare(insurance.risk, risk) == 0 &&
                Double.compare(insurance.price, price) == 0 &&
                Double.compare(insurance.payment, payment) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(risk, price, payment);
    }

    public abstract String toString();

    @Override
    public int compareTo(Insurance o) {
        return (int) (-this.risk *100 + o.risk *100);
    }
}
