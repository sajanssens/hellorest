package org.example.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.domain.Student;
import org.example.domain.Students;
import org.example.services.StudentService;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("students")
@Api("students")
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class StudentsResource implements Serializable {

    List<Student> students = new ArrayList<>();

    @Inject
    private StudentService studentService;

    @GET @Path("{id}") // read
    public Student get(@PathParam("id") int id) {
        return studentService.get(id);
    }

    @ApiOperation("Haal studenten op op basis van hun achternaam (volledig).")
    @GET @Path("q") // read
    public List<Student> get(@QueryParam("lastname") String lastname) {
        return filterStudentsBy(lastname);
    }

    @GET // read
    public Students getAll() { return studentService.getAll(); }

    @DELETE // delete
    @Path("{id}")
    public Student delete(@PathParam("id") int id) {
        return studentService.remove(id);
    }

    @POST
    public Student post(Student student) {
        // students.add(student); // doesn't make sense; rest is stateless
        if (studentService.add(student)) {
            return student;
        }
        throw new RuntimeException("Student not added.");
    }

    private List<Student> filterStudentsBy(@QueryParam("lastname") String lastname) {
        return students.stream()
                .filter(s -> lastname.equals(s.getLastname()))
                .collect(toList());
    }
}
