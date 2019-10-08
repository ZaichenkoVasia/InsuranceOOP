package ua.mycompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.service.UserService;

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
