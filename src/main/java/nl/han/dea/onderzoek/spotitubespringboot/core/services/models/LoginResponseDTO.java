package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

public class LoginResponseDTO {

    private String user;

    private String token;

    public LoginResponseDTO(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token){this.token = token;}

    public String getUser() {
        return user;

    }
    public void setUser(String user){
        this.user = user;
    }
}
