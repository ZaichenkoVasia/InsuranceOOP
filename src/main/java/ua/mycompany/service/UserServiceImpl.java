package ua.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mycompany.Helper.Utility.PasswordUtils;
import ua.mycompany.domain.Customer;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.repository.CustomerRepository;

import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer register(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }

        String encodePassword = PasswordUtils.generateSecurePassword(customer.getPassword());
        Customer encodeCustomer = (Customer) customer.clone(encodePassword);
        return customerRepository.save(encodeCustomer);
    }

    @Override
    public Customer login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UncorrectedLoginRuntimeException("Email is uncorrected"));

        String CustomerPassword = customer.getPassword();

        if (CustomerPassword.equals(encodePassword)) {
            return customer;
        }
        throw new UncorrectedLoginRuntimeException("Password is uncorrected");
    }


    @Override
    public Customer findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of customer must be > 0");
        }
        Optional<Customer> CustomerFindById = customerRepository.findById(id);
        if (CustomerFindById.isPresent()) {
            return CustomerFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of customer uncorrected");
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        customerRepository.update(customer);
    }

//    @Override
//    public ArrayList<Customer> findByDepartment(Long idDepartment) {
//        if (idDepartment < 0) {
//            throw new UncorrectedIdRuntimeException("Id of de must be > 0");
//        }
//        return customerRepository.findByDepartment(idDepartment);
//    }
//
//    @Override
//    public ArrayList<Customer> findByYear(int year) {
//        if (year < 1990) {
//            throw new IllegalArgumentException("year must be > 1990");
//        }
//        return customerRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Customer> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group can not be null");
//        }
//        return customerRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Customer> findByDepartmentAndCourse(Long idDepartment, int course) {
//        if (course < 0 || course > 6 || idDepartment < 0) {
//            throw new IllegalArgumentException("Course must be in range [0;6] or id department must be positive");
//        }
//        return customerRepository.findByDepartmentAndCourse(idDepartment, course);
//    }

}
