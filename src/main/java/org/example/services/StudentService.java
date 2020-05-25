package org.example.services;

import org.example.domain.Student;
import org.example.domain.Students;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.example.domain.Values.STUDENTS;

@Stateful
public class StudentService {

    private List<Student> students = new ArrayList<>(STUDENTS); // stateless....

    public Student get(int id) {
        return students.get(id);
    }

    public Students getAll() {
        return Students.of(students);
    }

    public Student remove(int id) {
        return students.remove(id);
    }

    private List<Student> filterStudentsBy(String lastname) {
        return students.stream()
                .filter(s -> lastname.toUpperCase().contains(s.getLastname().toUpperCase()))
                .collect(toList());
    }

    public Students find(String lastname) {
        return Students.of(filterStudentsBy(lastname));
    }

    public Student add(Student student) {
        if (students.add(student)) {
            return student;
        } else {
            throw new RuntimeException("Add failed....");
        }
    }
}
