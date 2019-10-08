package ua.mycompany.service;

import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Insurance;

import java.util.ArrayList;

public interface UserService {

    Customer register(Customer customer);

    Customer login(String email, String password);

    Customer findById(Long id);

    void update(Customer customer);

    void addInsurance(Customer customer, Insurance insurance);

    void deleteInsurance(Customer customer, Insurance insurance);

    ArrayList<Insurance> sortInsuranceByRisk(Customer customer);

    ArrayList<Insurance> rangeByRisk(Customer customer, double startRange, double endRange);

    ArrayList<Insurance> rangeByPrice(Customer customer, double startRange, double endRange);

    ArrayList<Insurance> rangeByPayment(Customer customer, double startRange, double endRange);

    double summaryOfPriceInsurances (Customer customer);
}
