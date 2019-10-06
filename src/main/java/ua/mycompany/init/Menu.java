package ua.mycompany.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.mycompany.domain.Address;
import ua.mycompany.domain.Department;
import ua.mycompany.domain.Student;
import ua.mycompany.service.StudentService;

import java.time.LocalDate;

@Component
public class Menu {
    private StudentService studentService;

    @Autowired
    public Menu(StudentService studentService) {
        this.studentService = studentService;
    }

    public void run() {
        Department department = new Department(1L, "Dep1");

        Student student1 = Student.builder()
                .withName("Name")
                .withSurname("Surname")
                .withBirthday(LocalDate.of(2002, 4, 4))
                .withAddress(new Address("city", "street", 10))
                .withDepartment(department)
                .withPhoneNumber("380444747474")
                .withGroup("AA-11")
                .withCourse(1)
                .withEmail("dsadas@gmail.com")
                .withPassword("1111")
                .build();

        Student student2 = Student.builder()
                .withName("Kick")
                .withSurname("Shevchenko")
                .withBirthday(LocalDate.of(1999, 3, 3))
                .withAddress(new Address("Kyiv", "Lviv", 2))
                .withDepartment(department)
                .withPhoneNumber("380444743334")
                .withGroup("AA-22")
                .withCourse(4)
                .withEmail("email@gmail.com")
                .withPassword("2222")
                .build();

        Student student3 = Student.builder()
                .withName("Tom")
                .withSurname("King")
                .withBirthday(LocalDate.of(1999, 3, 23))
                .withAddress(new Address("Kyiv", "street", 12))
                .withDepartment(department)
                .withPhoneNumber("380444744444")
                .withGroup("AA-22")
                .withCourse(4)
                .withEmail("tomking@gmail.com")
                .withPassword("3333")
                .build();

        studentService.register(student1);
        studentService.register(student2);
        studentService.register(student3);
    }
}
