package nl.han.dea.onderzoek.spotitubespringboot.core.services;

import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.PlaylistDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.TracksDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.UserDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistService {

    @Autowired
    private PlaylistDAO playlistDAO;

    @Autowired
    private TracksDAO tracksDAO;

    @Autowired
    private PlayListCollectionDTO playListCollectionDTO;

    @Autowired
    private UserDAO user;

    public PlayListCollectionDTO getPlaylists(String token) throws Exception {
        if(!token.equals(user.getToken(token))){
            throw new Exception();
        }
        UserDTO currentUser = user.findUser(token);
        playListCollectionDTO.setPlaylists(playlistDAO.getAll());
        playListCollectionDTO.setLength(playlistDAO.getLength());
        for(PlayListDTO playlists : playListCollectionDTO.getPlaylists()){
            if(playlists.getEigenaarNaam().equals(currentUser.getUser())){
                playlists.setOwner(true);
            }
        }

        return playListCollectionDTO;
    }

    public TrackCollectionDTO getPlaylist(String token, int id) throws Exception {
        if(!token.equals(user.getToken(token))){
            throw new Exception();
        }
        return tracksDAO.getPlaylist(id);


    }

    public PlayListCollectionDTO deletePlaylist(String token, int id) throws Exception {
        if(!token.equals(user.getToken(token))){
            throw new Exception();
        }
        playlistDAO.delete(id);
        return getPlaylists(token);
    }

    public PlayListCollectionDTO createPlaylist(String token, PlayListDTO playListDTO) throws Exception{
        if(!token.equals(user.getToken(token))){
            throw new Exception();
        }
        playListDTO.setEigenaarNaam(user.findUser(token).getUser());
        playlistDAO.create(playListDTO);
        return getPlaylists(token);
    }

    public PlayListCollectionDTO updatePlaylist(String token, int id, PlayListDTO playListDTO) throws Exception{
        if(!token.equals(user.getToken(token))){
            throw new Exception();
        } else if(id != playListDTO.getId()){
            throw new Exception();
        }
        playlistDAO.update(id,playListDTO);
        return getPlaylists(token);
    }

    public PlayListCollectionDTO addTrackToPlaylist(String token, int id, TrackDTO trackDTO) throws Exception{
        if(!token.equals(user.getToken(token))) {
            throw new Exception();
        }
        playlistDAO.add(id, trackDTO);
        return getPlaylists(token);
    }

    public PlayListCollectionDTO removeTrackFromPlaylist(String token, int playlistId, int trackId) throws Exception {
        if(!token.equals(user.getToken(token))) {
            throw new Exception();
        }
        playlistDAO.remove(playlistId, trackId);
        return getPlaylists(token);
    }
}
