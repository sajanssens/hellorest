package org.example;

import com.github.phillipkruger.apiee.ApieeService;
import org.example.resources.HelloWorldResource;
import org.example.resources.StudentsResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("resources")
public class App extends Application {

    public Set<Class<?>> getClasses() {
        return new HashSet<>(Arrays.asList(
                StudentsResource.class,
                HelloWorldResource.class,
                // add more resources here...
                ApieeService.class
        ));
    }
}
