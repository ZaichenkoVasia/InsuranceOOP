package ua.mycompany.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Customer implements Comparable<Customer>, CustomerPrototype {
    private final Long id;
    private final String name;
    private final String surname;
    private final LocalDate birthday;
    private final Address address;
    private final String phoneNumber;
    private final String email;
    private final String password;
    private static Long counter = 0L;

    private final Comparator<Customer> CUSTOMER_COMPARATOR_BY_AGE =
            Comparator.comparingInt(customer -> LocalDate.now().getYear() - customer.birthday.getYear());

    private final Comparator<Customer> CUSTOMER_COMPARATOR_BY_NAME =
            Comparator.comparing(customer -> customer.name);

    private final Comparator<Customer> CUSTOMER_COMPARATOR_BY_SURNAME =
            Comparator.comparing(customer -> customer.surname);

    private Customer(Builder builder) {
        if (builder.id == null) {
            this.id = ++counter;
        } else {
            this.id = builder.id;
        }
        this.name = builder.name;
        this.surname = builder.surname;
        this.birthday = builder.birthday;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Comparator<Customer> getcustomerComparator() {
        return CUSTOMER_COMPARATOR_BY_NAME.thenComparing(CUSTOMER_COMPARATOR_BY_SURNAME.thenComparing(CUSTOMER_COMPARATOR_BY_AGE));
    }

    @Override
    public int compareTo(Customer o) {
        return this.getcustomerComparator().compare(this, o);
    }

    @Override
    public CustomerPrototype clone(String newPassword) {
        Address address = (Address)Optional.ofNullable(this.address)
                .map(Address::clone)
                .orElse(null);

        return builder()
                .withId(id)
                .withName(name)
                .withSurname(surname)
                .withBirthday(birthday)
                .withAddress(address)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withPassword(newPassword)
                .build();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String surname;
        private LocalDate birthday;
        private Address address;
        private String phoneNumber;
        private String email;
        private String password;

        private Builder() {
        }

        public Customer build() {
            return new Customer(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(surname, customer.surname) &&
                Objects.equals(birthday, customer.birthday) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(CUSTOMER_COMPARATOR_BY_AGE, customer.CUSTOMER_COMPARATOR_BY_AGE) &&
                Objects.equals(CUSTOMER_COMPARATOR_BY_NAME, customer.CUSTOMER_COMPARATOR_BY_NAME) &&
                Objects.equals(CUSTOMER_COMPARATOR_BY_SURNAME, customer.CUSTOMER_COMPARATOR_BY_SURNAME);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthday, address, phoneNumber, email, password, CUSTOMER_COMPARATOR_BY_AGE, CUSTOMER_COMPARATOR_BY_NAME, CUSTOMER_COMPARATOR_BY_SURNAME);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
