package ua.mycompany.Helper.sort;

import org.springframework.stereotype.Component;
import ua.mycompany.domain.Student;

import java.util.ArrayList;

@Component
public final class BubbleSort {

    private BubbleSort() {
    }

    public static ArrayList<Student> sort(ArrayList<Student> students) {

        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - i - 1; j++) {
                if (students.get(j).compareTo(students.get(j + 1)) > 0) {
                    Utility.swap(students, j, j + 1);
                }
            }
        }
        return students;
    }
}
