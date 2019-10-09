package ua.mycompany.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.customer.Address;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.domain.order.impl.*;
import ua.mycompany.service.InsuranceService;
import ua.mycompany.service.UserService;

import java.time.LocalDate;

@Component
public class Menu {
    private UserService userService;
    private InsuranceService insuranceService;

    @Autowired
    public Menu(UserService userService, InsuranceService insuranceService) {
        this.userService = userService;
        this.insuranceService = insuranceService;
    }

    public void run() {

        Customer customer1 = Customer.builder()
                .withName("Name")
                .withSurname("Surname")
                .withBirthday(LocalDate.of(2002, 4, 4))
                .withAddress(new Address("city", "street", 10))
                .withPhoneNumber("380444747474")
                .withEmail("user@gmail.com")
                .withPassword("1111")
                .build();

        Customer customer2 = Customer.builder()
                .withName("Kick")
                .withSurname("Shevchenko")
                .withBirthday(LocalDate.of(1999, 3, 3))
                .withAddress(new Address("Kyiv", "Lviv", 2))
                .withPhoneNumber("380444743334")
                .withEmail("admin@gmail.com")
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

        userService.register(customer1);
        userService.register(customer2);
        userService.register(customer3);

        BusinessInsurance business = new BusinessInsurance(0.1, 1000, 500000);
        CarInsurance car = new CarInsurance(0.2, 1500, 10000);
        HouseInsurance house = new HouseInsurance(0.05, 2000, 1000000);
        LifeInsurance life = new LifeInsurance(0.05, 3000, 100000000);
        MedicalInsurance medical = new MedicalInsurance(0.3, 2000, 10000);

        insuranceService.save(business);
        insuranceService.save(car);
        insuranceService.save(house);
        insuranceService.save(life);
        insuranceService.save(medical);
    }
}
