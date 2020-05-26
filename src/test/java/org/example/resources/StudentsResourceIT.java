package org.example.resources;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class StudentsResourceIT {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(StudentsResource.class.getPackage());
    }

    @Test
    public void getGreeterReturnsCorrectMessage() {
        String message = ClientBuilder.newClient()
                .target("http://localhost:9080/hellorest_war_exploded/resources/students/")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        System.out.println(message);

        assertThat(message, containsString("{\"students\":["));

    }

}