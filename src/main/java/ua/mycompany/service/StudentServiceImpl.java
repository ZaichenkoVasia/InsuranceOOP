package ua.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mycompany.Helper.Utility.PasswordUtils;
import ua.mycompany.domain.Student;
import ua.mycompany.exception.UncorrectedIdRuntimeException;
import ua.mycompany.exception.UncorrectedLoginRuntimeException;
import ua.mycompany.exception.UserNotExistRuntimeException;
import ua.mycompany.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student register(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User not exist");
        }

        String encodePassword = PasswordUtils.generateSecurePassword(student.getPassword());
        Student encodeStudent = (Student) student.clone(encodePassword);
        return studentRepository.save(encodeStudent);
    }

    @Override
    public Student login(String email, String password) {
        String encodePassword = PasswordUtils.generateSecurePassword(password);

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UncorrectedLoginRuntimeException("Email is uncorrected"));

        String studentPassword = student.getPassword();

        if (studentPassword.equals(encodePassword)) {
            return student;
        }
        throw new UncorrectedLoginRuntimeException("Password is uncorrected");
    }


    @Override
    public Student findById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of user must be > 0");
        }
        Optional<Student> studentFindById = studentRepository.findById(id);
        if (studentFindById.isPresent()) {
            return studentFindById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of user uncorrected");
    }

    @Override
    public void update(Student student) {
        if (student == null) {
            throw new UserNotExistRuntimeException("User not exist");
        }
        studentRepository.update(student);
    }

    @Override
    public Student deleteById(Long id) {
        if (id < 0) {
            throw new UncorrectedIdRuntimeException("Id of user must be > 0");
        }
        Optional<Student> studentDeleteById = studentRepository.deleteById(id);
        if (studentDeleteById.isPresent()) {
            return studentDeleteById.get();
        }
        throw new UncorrectedIdRuntimeException("Id of user uncorrected");
    }

//    @Override
//    public ArrayList<Student> findByDepartment(Long idDepartment) {
//        if (idDepartment < 0) {
//            throw new UncorrectedIdRuntimeException("Id of de must be > 0");
//        }
//        return studentRepository.findByDepartment(idDepartment);
//    }
//
//    @Override
//    public ArrayList<Student> findByYear(int year) {
//        if (year < 1990) {
//            throw new IllegalArgumentException("year must be > 1990");
//        }
//        return studentRepository.findByYear(year);
//    }
//
//    @Override
//    public ArrayList<Student> findByGroup(String group) {
//        if (group == null) {
//            throw new IllegalArgumentException("Group can not be null");
//        }
//        return studentRepository.findByGroup(group);
//    }
//
//    @Override
//    public ArrayList<Student> findByDepartmentAndCourse(Long idDepartment, int course) {
//        if (course < 0 || course > 6 || idDepartment < 0) {
//            throw new IllegalArgumentException("Course must be in range [0;6] or id department must be positive");
//        }
//        return studentRepository.findByDepartmentAndCourse(idDepartment, course);
//    }

    @Override
    public ArrayList<Student> findAll() {
        return studentRepository.findAll();
    }

}
