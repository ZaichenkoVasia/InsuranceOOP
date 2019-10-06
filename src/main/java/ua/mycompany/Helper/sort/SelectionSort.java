package ua.mycompany.Helper.sort;

import org.springframework.stereotype.Component;
import ua.mycompany.domain.Customer;

import java.util.ArrayList;

@Component
public final class SelectionSort {

    private SelectionSort() {

    }

    public static ArrayList<Customer> sort(ArrayList<Customer> customers) {

        for (int left = 0; left < customers.size(); left++) {
            int minInd = left;
            for (int i = left; i < customers.size(); i++) {
                if (customers.get(i).compareTo(customers.get(minInd)) < 0) {
                    minInd = i;
                }
            }
            Utility.swap(customers, left, minInd);
        }
        return customers;
    }
}
