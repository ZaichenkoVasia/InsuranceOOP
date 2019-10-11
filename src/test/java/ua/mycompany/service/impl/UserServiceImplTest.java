package ua.mycompany.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.mycompany.domain.customer.Address;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.domain.order.impl.HouseInsurance;
import ua.mycompany.domain.order.impl.LifeInsurance;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.util.encoder.PasswordEncoder;
import ua.mycompany.util.validator.UserValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private Customer customer;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private InsuranceServiceImpl insuranceService;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        customer = Customer.builder()
                .withName("V")
                .withSurname("Zaichenk")
                .withBirthday(LocalDate.of(1999, 1, 13))
                .withAddress(new Address("Uman", "South", 13))
                .withPhoneNumber("380911111111")
                .withEmail("1@gmail.com")
                .withPassword("1")
                .withRole(Role.ADMIN)
                .build();
    }

    @After
    public void resetMock() {
        reset(customerRepository);
    }

    @Test
    public void shouldReturnLoginCustomer() {
        Customer studentExpected = (Customer) customer.clone(PasswordEncoder.generateSecurePassword(customer.getPassword()));

        when(customerRepository.findByEmail(any(String.class))).thenReturn(ofNullable(studentExpected));

        Customer studentActual = userService.login(customer.getEmail(), customer.getPassword());
        assertThat(true, is(PasswordEncoder.verifycustomerPassword(customer.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnRegisterCustomer() {

        Customer studentExpected = (Customer) customer.clone(PasswordEncoder.generateSecurePassword(customer.getPassword()));

        when(customerRepository.save(any(Customer.class))).thenReturn(studentExpected);
        when(userValidator.validate(any(Customer.class))).thenReturn(true);

        Customer studentActual = userService.register(customer);
        assertThat(true, is(PasswordEncoder.verifycustomerPassword(customer.getPassword(), studentActual.getPassword())));
    }

    @Test
    public void shouldReturnCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        Customer studentActual = userService.findById(1L);
        assertThat(customer, is(studentActual));
    }

    @Test
    public void shouldReturnAllVegetable() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.3, 50, 1000);
        HouseInsurance houseInsurance = new HouseInsurance(0.3, 50, 1000);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());

        ArrayList<Insurance> expectedInsurance = new ArrayList<>();
        expectedInsurance.add(lifeInsurance);
        expectedInsurance.add(houseInsurance);

        assertThat(expectedInsurance, is(userService.findAllInsurance(customer)));
    }

    @Test
    public void shouldReturnAddingVegetable() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.3, 50, 1000);
        HouseInsurance houseInsurance = new HouseInsurance(0.3, 50, 1000);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());

        ArrayList<Insurance> expectedInsurance = new ArrayList<>();
        expectedInsurance.add(lifeInsurance);
        expectedInsurance.add(houseInsurance);

        assertThat(expectedInsurance, is(userService.findAllInsurance(customer)));
    }

    @Test
    public void shouldReturnDeletingVegetable() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.3, 50, 1000);
        HouseInsurance houseInsurance = new HouseInsurance(0.3, 50, 1000);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());
        userService.deleteInsurance(customer, houseInsurance.getId());

        ArrayList<Insurance> expectedInsurance = new ArrayList<>();
        expectedInsurance.add(lifeInsurance);

        assertThat(expectedInsurance, is(userService.findAllInsurance(customer)));
    }

    @Test
    public void shouldReturnSortingVegetable() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.5, 100.0, 100);
        HouseInsurance houseInsurance = new HouseInsurance(0.7, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());

        ArrayList<Insurance> expectedInsurance = new ArrayList<>();
        expectedInsurance.add(houseInsurance);
        expectedInsurance.add(lifeInsurance);

        assertThat(expectedInsurance, is(userService.sortInsuranceByRisk(customer)));
    }

    @Test
    public void shouldReturnRangingByCaloriesVegetable() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.6, 100.0, 100);
        HouseInsurance houseInsurance = new HouseInsurance(0.1, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());

        ArrayList<Insurance> expectedInsurance = new ArrayList<>();
        expectedInsurance.add(lifeInsurance);

        assertThat(expectedInsurance, is(userService.rangeByRisk(customer, 0.5, 0.8)));
    }

    @Test
    public void shouldReturnSummaryOfCaloriesSalad() {
        LifeInsurance lifeInsurance = new LifeInsurance(0.6, 100.0, 100);
        HouseInsurance houseInsurance = new HouseInsurance(0.1, 100.0, 100);

        when(userValidator.validate(any(Customer.class))).thenReturn(true);
        when(insuranceService.findById(lifeInsurance.getId())).thenReturn(lifeInsurance);
        when(insuranceService.findById(houseInsurance.getId())).thenReturn(houseInsurance);

        userService.addInsurance(customer, lifeInsurance.getId());
        userService.addInsurance(customer, houseInsurance.getId());

        assertThat(200.0, is(userService.summaryOfPriceInsurances(customer)));
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInRegister() {
        userService.register(null);
    }

    @Test(expected = UncorrectedIdRuntimeException.class)
    public void shouldReturnUncorrectedIdRuntimeExceptionInFindById() {
        userService.findById(-1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInUpdate() {
        userService.update(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInFindVegetable() {
        userService.findAllInsurance(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInaddInsurance() {
        userService.addInsurance(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionIndeleteInsurance() {
        userService.deleteInsurance(null, 1L);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSortVegetable() {
        userService.sortInsuranceByRisk(null);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInRangeVegetable() {
        userService.rangeByRisk(null, -0.1, 0.5);
    }

    @Test(expected = CustomerNotExistRuntimeException.class)
    public void shouldReturnCustomerNotExistRuntimeExceptionInSummaryVegetable() {
        userService.summaryOfPriceInsurances(null);
    }
}
