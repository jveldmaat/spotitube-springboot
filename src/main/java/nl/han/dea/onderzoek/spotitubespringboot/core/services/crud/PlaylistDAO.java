package nl.han.dea.onderzoek.spotitubespringboot.core.services.crud;

import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.PlayListDTO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PlaylistDAO implements ICrudResource<Integer, PlayListDTO> {

    private Logger logger = Logger.getLogger(getClass().getName());


    @Autowired
    private DatabaseProperties databaseProperties;

    @Override
    public List<PlayListDTO> getAll() {
        List<PlayListDTO> playlists = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from playlists");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlayListDTO playlist = new PlayListDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setEigenaarNaam(resultSet.getString("owner"));
                playlists.add(playlist);
            }
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlists;
    }

    @Override
    public PlayListDTO get(Integer key) {
        PlayListDTO playlist = new PlayListDTO();
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("SELECT * from playlists where id = ?");
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                playlist.setEigenaarNaam(resultSet.getString("owner"));
            }
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlist;
    }

    @Override
    public void create(PlayListDTO data) {
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO playlists (name, owner) " + "values(?,?)");
            statement.setString(1, data.getName());
            statement.setString(2, data.getEigenaarNaam());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void update(Integer key, PlayListDTO data) {
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("UPDATE playlists set name = ? where id = ?");
            statement.setString(1,data.getName());
            statement.setInt(2,key);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }

    }

    @Override
    public void delete(Integer key) {
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM track_in_playlists where playlistsid = ?");
            statement.setInt(1, key);
            statement.execute();
            statement = connection.prepareStatement("DELETE FROM playlists where id = ?");
            statement.setInt(1,key);
            statement.execute();
            statement.close();
        } catch (SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    public void setAll(List<PlayListDTO> filteredPlaylists) {
    }

    public void add(Integer key, TrackDTO trackDTO){
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO track_in_playlists (playlistsid, trackid, offlineavailable) " + "values(?, ?,?)");
            statement.setInt(1, key);
            statement.setInt(2, trackDTO.getId() );
            statement.setBoolean(3, trackDTO.isOfflineAvailable());
            statement.execute();
            statement.close();
        } catch(SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    public void remove(int playlistId, int trackId) {
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM track_in_playlists where playlistsid = ? AND trackid = ?");
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
            statement.close();
        } catch(SQLException e){
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    public long getLength() {
        long length = 0;
        try(Connection connection = DriverManager.getConnection(databaseProperties.connectionString())) {

            PreparedStatement statement = connection.prepareStatement("select * from playlists p inner join track_in_playlists tip on p.id = tip.playlistsid inner join tracks t on tip.trackid = t.id");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                length += resultSet.getLong("afspeelduur");
            }
            statement.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return length;
    }


}
