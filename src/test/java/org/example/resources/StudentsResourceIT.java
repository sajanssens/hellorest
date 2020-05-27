package org.example.resources;

import org.example.App;
import org.example.domain.Students;
import org.example.services.StudentService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.net.URL;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class StudentsResourceIT {

    @ArquillianResource
    private URL deploymentURL;

    private String studentsResource;

    @Before
    public void setup() {
        studentsResource = deploymentURL + "resources/students";
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(App.class) // dont forget!
                .addClass(StudentService.class)
                .addPackage(StudentsResource.class.getPackage())
                .addPackage(Students.class.getPackage());
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void getStudentsReturnsStudents() {
        System.out.println("studentsResource=" + studentsResource);
        String message = ClientBuilder.newClient()
                .target(studentsResource)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println(message);

        assertThat(message, containsString("{\"students\":["));
    }

}