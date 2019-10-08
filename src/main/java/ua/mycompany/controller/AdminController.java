package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.service.AdminService;

import java.util.ArrayList;

@Component
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public Customer register(Customer customer) {
        return adminService.register(customer);
    }

    public Customer login(String email, String password) {
        return adminService.login(email, password);
    }

    public Customer findById(Long id) {
        return adminService.findById(id);
    }

    public void update(Customer customer) {
        adminService.update(customer);
    }

    public Customer deleteById(Long id) {
        return adminService.deleteById(id);
    }

    public ArrayList<Customer> findAll() {
        return adminService.findAll();
    }

    public void addInsurance(Customer customer, Insurance insurance) {
        adminService.addInsurance(customer, insurance);
    }

    void deleteInsurance(Customer customer, Insurance insurance) {
        adminService.deleteInsurance(customer, insurance);
    }

    ArrayList<Insurance> sortInsuranceByRisk(Customer customer) {
        return adminService.sortInsuranceByRisk(customer);
    }

    ArrayList<Insurance> rangeByRisk(Customer customer, double startRange, double endRange) {
        return adminService.rangeByRisk(customer, startRange, endRange);
    }

    ArrayList<Insurance> rangeByPrice(Customer customer, double startRange, double endRange){
        return adminService.rangeByPrice(customer, startRange, endRange);
    }

    ArrayList<Insurance> rangeByPayment(Customer customer, double startRange, double endRange){
        return adminService.rangeByPayment(customer, startRange, endRange);
    }

    double summaryOfPriceInsurances(Customer customer){
        return adminService.summaryOfPriceInsurances(customer);
    }
}
