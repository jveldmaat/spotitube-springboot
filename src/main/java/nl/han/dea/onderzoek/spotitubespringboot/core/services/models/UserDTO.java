package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

public class UserDTO {
    private String user;
    private String password;
    private String token;

    public UserDTO(String user, String password, String token){
        this.user = user;
        this.password = password;
        this.token = token;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
