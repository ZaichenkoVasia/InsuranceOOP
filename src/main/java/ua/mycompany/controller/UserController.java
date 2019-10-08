package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.service.UserService;

import java.util.ArrayList;

@Component
@Primary
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Customer register(Customer customer) {
        return userService.register(customer);
    }

    public Customer login(String email, String password) {
        return userService.login(email, password);
    }

    public Customer findById(Long id) {
        return userService.findById(id);
    }

    public void update(Customer customer) {
        userService.update(customer);
    }

    public void addInsurance(Customer customer, Long id) {
        userService.addInsurance(customer, id);
    }

    public void deleteInsurance(Customer customer, Long id) {
        userService.deleteInsurance(customer, id);
    }

    public ArrayList<Insurance> sortInsuranceByRisk(Customer customer) {
        return userService.sortInsuranceByRisk(customer);
    }

    public ArrayList<Insurance> rangeByRisk(Customer customer, double startRange, double endRange) {
        return userService.rangeByRisk(customer, startRange, endRange);
    }

    public ArrayList<Insurance> rangeByPrice(Customer customer, double startRange, double endRange){
        return userService.rangeByPrice(customer, startRange, endRange);
    }

    public ArrayList<Insurance> rangeByPayment(Customer customer, double startRange, double endRange){
        return userService.rangeByPayment(customer, startRange, endRange);
    }

    public double summaryOfPriceInsurances(Customer customer){
        return userService.summaryOfPriceInsurances(customer);
    }

    public ArrayList<Insurance> findAllInsurance(Customer customer) {
        return userService.findAllInsurance(customer);
    }
}
