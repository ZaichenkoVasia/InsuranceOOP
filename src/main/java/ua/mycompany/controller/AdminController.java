package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
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

//    public ArrayList<Customer> findByDepartment(Long idDepartment) {
//        return userService.findByDepartment(idDepartment);
//    }
//
//    public ArrayList<Customer> findByYear(int year) {
//        return userService.findByYear(year);
//    }
//
//    public ArrayList<Customer> findByGroup(String group) {
//        return userService.findByGroup(group);
//    }
//
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course) {
//        return userService.findByDepartmentAndCourse(idDepartment, course);
//    }


}
