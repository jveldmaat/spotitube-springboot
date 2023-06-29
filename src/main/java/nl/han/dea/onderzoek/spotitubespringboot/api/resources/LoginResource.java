package nl.han.dea.onderzoek.spotitubespringboot.api.resources;


import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.UserDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginResource {

    private final UserDAO user;

    public LoginResource(UserDAO user) {
        this.user = user;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        if(loginRequestDTO == null){
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        } else{
            var loginResponseDTO = user.login(loginRequestDTO.getUser());

            return ResponseEntity.ok(loginResponseDTO);
        }
    }

}
