package ua.mycompany.repository;

import ua.mycompany.domain.Customer;

import java.util.ArrayList;
import java.util.Optional;

public interface CustomerRepository {

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(Long id);

    void update(Customer customer);

    Optional<Customer> deleteById(Long id);

//    ArrayList<Customer> findByDepartment(Long idDepartment);
//
//    ArrayList<Customer> findByYear(int year);
//
//    ArrayList<Customer> findByGroup(String group);
//
//    ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course);

    ArrayList<Customer> findAll();
}
