package ua.mycompany.repository.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CustomerRepositoryImplTest {
    private static CustomerRepository customerRepository;
    private static Customer customer;

    @BeforeClass
    public static void setUp(){
        customerRepository = new CustomerRepositoryImpl();
        customer = Customer.builder()
                .withRole(Role.USER)
                .withEmail("cust1@gmail.com")
                .withPassword("1111")
                .build();
        customerRepository.save(customer);
    }
    @Test
    public void shouldCorrectSaveCustomerFromRepo() {
        assertThat(customer, is(customerRepository.findById(1L).get()));
    }

    @Test
    public void shouldReturnCorrectCustomerFromRepoById() {
        assertThat(customer, is(customerRepository.findById(1L).get()));
    }

    @Test
    public void shouldReturnAllCustomersFromRepo() {
        Customer secondCustomer = Customer.builder()
                .withRole(Role.USER)
                .withEmail("cust2@gmail.com")
                .withPassword("2222")
                .build();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(secondCustomer);
        customerRepository.save(secondCustomer);
        assertThat(customers, is(customerRepository.findAll()));
        customerRepository.deleteById(2L);
    }

    @Test
    public void shouldReturnCustomerFromRepoByEmail() {
        assertThat(customer, is(customerRepository.findByEmail("cust1@gmail.com").get()));
    }

    @Test
    public void shouldReturnUpdatedCustomerFromRepoById() {
        Customer updateCustomer = Customer.builder()
                .withId(1L)
                .withRole(Role.USER)
                .withEmail("cust2@gmail.com")
                .withPassword("3333")
                .build();

        customerRepository.update(updateCustomer);
        assertThat(updateCustomer, is(customerRepository.findById(1L).get()));
        customerRepository.update(customer);
    }

    @Test
    public void shouldReturnDeleteCustomerFromRepoById() {
        assertThat(customer, is(customerRepository.findById(1L).get()));
        customerRepository.deleteById(1L);
        assertThat(customerRepository.findAll(), is(emptyCollectionOf(Customer.class)));
    }
}
