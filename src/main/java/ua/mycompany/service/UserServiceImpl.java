package ua.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.exception.CustomerNotExistRuntimeException;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.helper.utility.PasswordUtils;
import ua.mycompany.repository.CustomerRepository;
import ua.mycompany.repository.InsuranceRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl implements UserService {

    protected CustomerRepository customerRepository;
    private InsuranceService insuranceService;

    @Autowired
    public UserServiceImpl(CustomerRepository customerRepository, InsuranceService insuranceService) {
        this.customerRepository = customerRepository;
        this.insuranceService = insuranceService;
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
        Optional<Customer> customerFindById = customerRepository.findById(id);
        if (customerFindById.isPresent()) {
            return customerFindById.get();
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

    @Override
    public void addInsurance(Customer customer, Long id) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        Insurance insurance = insuranceService.findById(id);
        customer.getDerivative().add(insurance);
        update(customer);
    }

    @Override
    public void deleteInsurance(Customer customer, Long id) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        Insurance insurance = insuranceService.findById(id);
        customer.getDerivative().remove(insurance);
        update(customer);
    }

    @Override
    public ArrayList<Insurance> findAllInsurance(Customer customer){
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().getInsurances();
    }

    @Override
    public ArrayList<Insurance> sortInsuranceByRisk(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().sortByRisk();
    }

    @Override
    public ArrayList<Insurance> rangeByRisk(Customer customer, double startRange, double endRange) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().searchElementRisk(startRange,endRange);
    }

    @Override
    public ArrayList<Insurance> rangeByPrice(Customer customer, double startRange, double endRange) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().searchElementPrice(startRange,endRange);
    }


    @Override
    public ArrayList<Insurance> rangeByPayment(Customer customer, double startRange, double endRange) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().searchElementPayment(startRange,endRange);
    }

    @Override
    public double summaryOfPriceInsurances(Customer customer) {
        if (customer == null) {
            throw new CustomerNotExistRuntimeException("customer not exist");
        }
        return customer.getDerivative().sumOfInsurance();
    }
}
