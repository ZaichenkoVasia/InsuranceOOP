package ua.mycompany.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.controller.InsuranceController;
import ua.mycompany.domain.order.Insurance;
import ua.mycompany.helper.utility.UTF8Control;
import ua.mycompany.helper.validator.ValidatorFactory;
import ua.mycompany.helper.sort.BubbleSort;
import ua.mycompany.controller.AdminController;
import ua.mycompany.controller.UserController;
import ua.mycompany.domain.customer.Customer;
import ua.mycompany.domain.customer.Role;

//import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class CustomerViewInfo {

    private UserController userController;
    private AdminController adminController;
    private InsuranceController insuranceController;
    private ResourceBundle lang;
    private Scanner in = new Scanner(System.in);
    private Customer currentCustomer;

    @Autowired
    public CustomerViewInfo(UserController userController, AdminController adminController, InsuranceController insuranceController) {
        this.userController = userController;
        this.adminController = adminController;
        this.insuranceController = insuranceController;
    }

    public void run() {
        chooseMenuLang();
    }

    protected void chooseMenuLang() {

        System.out.println("Choose language/Оберіть мову");
        System.out.println("English (1)");
        System.out.println("Українська (2)");
        int chooseLanguage = in.nextInt();

        chooseLang(chooseLanguage);
    }

    private void chooseLang(int chooseLanguage) {

        try {
            if (chooseLanguage == 1) {
                lang = ResourceBundle.getBundle("resources", new Locale("en"), new UTF8Control());
            } else if (chooseLanguage == 2) {
                lang = ResourceBundle.getBundle("resources", new Locale("ua"), new UTF8Control());
            } else
                chooseMenuLang();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        loginOrRegister();
    }

    private void loginOrRegister() {
        System.out.println("1 - " + lang.getString("registration"));
        System.out.println("2 - " + lang.getString("login"));
        int loginOrRegister = in.nextInt();
        if(loginOrRegister == 1){
            register();
        }else if (loginOrRegister == 2){
            loginCustomer();
        }else {
            loginOrRegister();
        }
    }

    private void loginCustomer(){
        String email = writeFieldValidator("email");

        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();
        currentCustomer = userController.login(email,password);
        menu();
    }

    private void register() {
        String name = writeFieldValidator("name");
        String surname = writeFieldValidator("surname");
        String email = writeFieldValidator("email");
        String phoneNumber = writeFieldValidator("phoneNumber");
        String birthday = writeFieldValidator("date");
        System.out.println(lang.getString("passwordCustomer"));
        String password = in.nextLine();

        Customer customer = Customer.builder()
                .withName(name)
                .withSurname(surname)
                .withBirthday(splitBirthday(birthday))
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withPassword(password)
                .build();
        userController.register(customer);
        System.out.println(lang.getString("customerCreated") + "\n");
        currentCustomer = customer;
        menu();
    }

    private void menu() {
        if(currentCustomer.getRole() == Role.ADMIN){
            menuAdmin();
        }else {
            menuUser();
        }

    }

    private LocalDate splitBirthday(String birthday) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(birthday, formatter);
    }

    private String writeFieldValidator(String nameField) {

        String key = nameField + "Customer";
        System.out.println(lang.getString(key));
        String fieldInput = in.nextLine();
        if (!ValidatorFactory.getValidator(nameField).validate(fieldInput)) {
            System.out.println(lang.getString("uncorrectedValue"));
            fieldInput = writeFieldValidator(nameField);
        }
        return fieldInput;
    }

    private void printAllCustomers(ArrayList<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println(lang.getString("noCustomerYet"));
        } else {
            System.out.println("\n" + lang.getString("listCustomer"));
            for (Customer customer : customers
            ) {
                System.out.println(customer);
            }
            System.out.println();
        }
    }

    private void printAllInsurance(ArrayList<Insurance> insurances) {
        if (insurances.isEmpty()) {
            System.out.println(lang.getString("noInsuranceYet"));
        } else {
            System.out.println("\n" + lang.getString("listInsurance"));
            for (Insurance insurance : insurances
            ) {
                System.out.println(insurance);
            }
            System.out.println();
        }
    }

    private void sortCustomer() {
        System.out.println(lang.getString("customersAreSorted") + "\n");
        printAllCustomers(BubbleSort.sort(adminController.findAll()));
    }

    private Customer findById(){
        System.out.println(lang.getString("inputId"));
        return adminController.findById(in.nextLong());
    }

    private void menuAdmin() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewCustomer"));
        System.out.println("2 - " + lang.getString("sortCustomer"));
        System.out.println("3 - " + lang.getString("inputId"));
        System.out.println("4 - " + lang.getString("viewAllInsurance"));
        System.out.println("5 - " + lang.getString("deleteInsurance"));
        System.out.println("6 - " + lang.getString("viewOwnInsurance"));
        System.out.println("7 - " + lang.getString("addOwnInsurance"));
        System.out.println("8 - " + lang.getString("deleteOwnInsurance"));
        System.out.println("9 - " + lang.getString("sortOwnInsurance"));
        System.out.println("10 - " + lang.getString("rangeRiskOwnInsurance"));
        System.out.println("11 - " + lang.getString("rangePriceOwnInsurance"));
        System.out.println("12 - " + lang.getString("rangePaymentOwnInsurance"));
        System.out.println("13 - " + lang.getString("sumOwnInsurance"));
        System.out.println("14 - " + lang.getString("chooseLanguage"));
        System.out.println("15 - " + lang.getString("exit"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                printAllCustomers(adminController.findAll());
                break;
            case 2:
                sortCustomer();
                break;
            case 3:
                System.out.println(findById());
                break;
            case 4:
                printAllInsurance(insuranceController.findAll());
                break;
            case 5:
                deleteInsurance();
                break;
            case 6:
                printAllInsurance(adminController.findAllInsurance(currentCustomer));
                break;
            case 7:
                addOwnInsurance();
                break;
            case 8:
                deleteOwnInsurance();
                break;
            case 9:
                printAllInsurance(adminController.sortInsuranceByRisk(currentCustomer));
                break;
            case 10:
                printAllInsurance(adminController.rangeByRisk(currentCustomer, 0.05, 0.15));
                break;
            case 11:
                printAllInsurance(adminController.rangeByPrice(currentCustomer, 3000, 20000));
                break;
            case 12:
                printAllInsurance(adminController.rangeByPayment(currentCustomer, 1000, 100000));
                break;
            case 13:
                System.out.println(adminController.summaryOfPriceInsurances(currentCustomer));
            break;
            case 14:
                chooseMenuLang();
                break;
            case 15:
                System.exit(0);
        }
        menuAdmin();
    }

    private void deleteOwnInsurance() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        adminController.deleteInsurance(currentCustomer,id);
    }

    private void addOwnInsurance() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        adminController.addInsurance(currentCustomer,id);
    }

    private void deleteInsurance() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        insuranceController.deleteById(id);
    }

    private void menuUser() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("currentId"));
        System.out.println("2 - " + lang.getString("viewAllInsurance"));
        System.out.println("3 - " + lang.getString("viewOwnInsurance"));
        System.out.println("4 - " + lang.getString("addOwnInsurance"));
        System.out.println("5 - " + lang.getString("deleteOwnInsurance"));
        System.out.println("6 - " + lang.getString("sortOwnInsurance"));
        System.out.println("7 - " + lang.getString("rangeRiskOwnInsurance"));
        System.out.println("8 - " + lang.getString("rangePriceOwnInsurance"));
        System.out.println("9 - " + lang.getString("rangePaymentOwnInsurance"));
        System.out.println("10 - " + lang.getString("sumOwnInsurance"));
        System.out.println("11 - " + lang.getString("chooseLanguage"));
        System.out.println("12 - " + lang.getString("exit"));

        int choice;
        try {
            choice = in.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(lang.getString("uncorrectedArgument"));
        }

        switch (choice) {
            case 1:
                System.out.println(userController.findById(currentCustomer.getId()));
                break;
            case 2:
                printAllInsurance(insuranceController.findAll());
                break;
            case 3:
                printAllInsurance(userController.findAllInsurance(currentCustomer));
                break;
            case 4:
                addOwnInsuranceUser();
                break;
            case 5:
                deleteOwnInsuranceUser();
                break;
            case 6:
                printAllInsurance(userController.sortInsuranceByRisk(currentCustomer));
                break;
            case 7:
                printAllInsurance(userController.rangeByRisk(currentCustomer, 0.05, 0.15));
                break;
            case 8:
                printAllInsurance(userController.rangeByPrice(currentCustomer, 3000, 20000));
                break;
            case 9:
                printAllInsurance(userController.rangeByPayment(currentCustomer, 1000, 100000));
                break;
            case 10:
                System.out.println(userController.summaryOfPriceInsurances(currentCustomer));
                break;
            case 11:
                chooseMenuLang();
                break;
            case 12:
                System.exit(0);
        }
        menuUser();
    }

    private void deleteOwnInsuranceUser() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        userController.deleteInsurance(currentCustomer,id);
    }

    private void addOwnInsuranceUser() {
        System.out.println(lang.getString("inputId"));
        Long id = in.nextLong();
        userController.addInsurance(currentCustomer,id);
    }
}
