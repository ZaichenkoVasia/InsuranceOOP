package ua.mycompany.service;

import ua.mycompany.domain.customer.Customer;

public interface UserService {

    Customer register(Customer customer);

    Customer login(String email, String password);

    Customer findById(Long id);

    void update(Customer customer);
}
