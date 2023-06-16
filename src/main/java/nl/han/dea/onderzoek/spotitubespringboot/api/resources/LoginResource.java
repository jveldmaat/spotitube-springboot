package nl.han.dea.onderzoek.spotitubespringboot.api.resources;


import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.UserDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.LoginRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginResource {

    private UserDAO user;

    @GetMapping("/")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        if(loginRequestDTO == null){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else{
            var loginResponseDTO = user.login(loginRequestDTO.getUser());

            return new ResponseEntity(HttpStatus.OK);
        }
    }

}
