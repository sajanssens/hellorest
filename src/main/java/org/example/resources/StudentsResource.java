package org.example.resources;

import org.example.domain.Student;
import org.example.domain.Students;
import org.example.services.StudentService;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("students")
@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class StudentsResource {

    @Inject
    private StudentService studentService;

    @GET @Path("{id}") // read
    public Student get(@PathParam("id") int id) {
        return studentService.get(id);
    }

    @GET @Path("q") // read
    public Students get(@QueryParam("lastname") String lastname) {
        return studentService.find(lastname);
    }

    @GET // read
    public Students getAll() { return studentService.getAll(); }

    @DELETE // delete
    @Path("{id}")
    public Student delete(@PathParam("id") int id) {
        return studentService.remove(id);
    }

    @POST // create
    @Produces(MediaType.APPLICATION_JSON)
    public Student post(Student student) {
        return studentService.add(student);
    }
}
