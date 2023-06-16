package nl.han.dea.onderzoek.spotitubespringboot.api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @GetMapping("/")
    String get(){
        return ("Hallo Wereld!");
    }
}
