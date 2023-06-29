package nl.han.dea.onderzoek.spotitubespringboot.core.services.crud;

import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.LoginResponseDTO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class UserDAO{
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private ICrudResource.DatabaseProperties databaseProperties;


    public List<UserDTO> findAll(Connection connection) {
        List<UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from users");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDTO user = new UserDTO(resultSet.getString("user"), resultSet.getString("password"), resultSet.getString("token"));
                users.add(user);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return users;
    }

    public LoginResponseDTO login(String username){
        LoginResponseDTO login = null;
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                login = new LoginResponseDTO(
                        resultSet.getString("username"),
                        resultSet.getString("token")
                );
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return login;
    }

    public String getToken(String token) {
        String getToken = null;
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                getToken = resultSet.getString("token");
            }
            statement.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return getToken;
    }

    public UserDTO findUser(String token){
        UserDTO user = null;
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from users where token = ?");
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                user = new UserDTO(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("token"));
            }
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return user;
    }
}
