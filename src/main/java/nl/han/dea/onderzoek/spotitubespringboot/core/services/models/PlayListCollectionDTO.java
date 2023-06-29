package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

import nl.han.dea.onderzoek.spotitubespringboot.core.exceptions.PlayListNotAvailableException;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.PlaylistDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayListCollectionDTO {
    private PlaylistDAO playlistDAO = new PlaylistDAO();
    private List<PlayListDTO> playlists = new ArrayList<>();
    private long length = 0;
    public PlayListCollectionDTO(){
    }

    public PlayListDTO getPlayList(int id){
        Optional<PlayListDTO> requestedPlaylist = playlistDAO.getAll().stream().filter(item -> item.getId() == id).findFirst();

        if (requestedPlaylist.isPresent()) {
            return requestedPlaylist.get();
        } else {
            throw new PlayListNotAvailableException();
        }
    }

    public void removePlaylist(int id){
        Optional<PlayListDTO> playlistForName = playlistDAO.getAll().stream().filter(item -> item.getId() == id).findFirst();

        List<PlayListDTO> filteredPlaylists = playlistDAO.getAll().stream().filter(item -> item.getId() != id).collect(Collectors.toList());

        if (filteredPlaylists.size() == playlistDAO.getAll().size()) {
            throw new PlayListNotAvailableException();
        }

        playlistDAO.setAll(filteredPlaylists);
    }


    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public List<PlayListDTO> getPlaylists(){
        return playlists;
    }

    public void setPlaylists(List<PlayListDTO> playlists) {
        this.playlists = playlists;
    }
}
