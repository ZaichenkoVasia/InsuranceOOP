package ua.mycompany.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    private ResourceBundle lang;
    private Scanner in = new Scanner(System.in);
    private Customer currentCustomer;

    @Autowired
    public CustomerViewInfo(UserController userController, AdminController adminController) {
        this.userController = userController;
        this.adminController = adminController;
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
        System.out.println("9 - " + lang.getString("chooseLanguage"));
        System.out.println("0 - " + lang.getString("exit"));

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
            case 9:
                chooseMenuLang();
                break;
            case 0:
                System.exit(0);
        }
        menuAdmin();
    }

    private void menuUser() {
        System.out.println(lang.getString("menu"));
        System.out.println("1 - " + lang.getString("viewInfoUser"));
        System.out.println("8 - " + lang.getString("chooseLanguage"));
        System.out.println("0 - " + lang.getString("exit"));

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
            case 8:
                chooseMenuLang();
                break;
            case 0:
                System.exit(0);
        }
        menuUser();
    }
}
