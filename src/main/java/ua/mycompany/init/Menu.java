package ua.mycompany.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.Address;
import ua.mycompany.domain.Customer;
import ua.mycompany.domain.Role;
import ua.mycompany.service.CustomerService;

import java.time.LocalDate;

@Component
public class Menu {
    private CustomerService customerService;

    @Autowired
    public Menu(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void run() {

        Customer customer1 = Customer.builder()
                .withName("Name")
                .withSurname("Surname")
                .withBirthday(LocalDate.of(2002, 4, 4))
                .withAddress(new Address("city", "street", 10))
                .withPhoneNumber("380444747474")
                .withEmail("dsadas@gmail.com")
                .withPassword("1111")
                .build();

        Customer customer2 = Customer.builder()
                .withName("Kick")
                .withSurname("Shevchenko")
                .withBirthday(LocalDate.of(1999, 3, 3))
                .withAddress(new Address("Kyiv", "Lviv", 2))
                .withPhoneNumber("380444743334")
                .withEmail("email@gmail.com")
                .withPassword("2222")
                .withRole(Role.ADMIN)
                .build();

        Customer customer3 = Customer.builder()
                .withName("Tom")
                .withSurname("King")
                .withBirthday(LocalDate.of(1999, 3, 23))
                .withAddress(new Address("Kyiv", "street", 12))
                .withPhoneNumber("380444744444")
                .withEmail("tomking@gmail.com")
                .withPassword("3333")
                .build();

        customerService.register(customer1);
        customerService.register(customer2);
        customerService.register(customer3);
    }
}
