package ua.mycompany.Helper.sort;

import org.springframework.stereotype.Component;
import ua.mycompany.domain.Student;

import java.util.ArrayList;
import java.util.Collections;

@Component
final class Utility {
    private Utility() {
    }

    public static void swap(ArrayList<Student> students, int i, int j) {
        Collections.swap(students, i, j);
    }
}
