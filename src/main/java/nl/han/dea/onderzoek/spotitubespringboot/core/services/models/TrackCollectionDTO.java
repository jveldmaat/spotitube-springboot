package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

import java.util.ArrayList;
import java.util.List;

public class TrackCollectionDTO {
    private List<TrackDTO> tracks = new ArrayList<>();
    public TrackCollectionDTO(){
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }


    public void setTracks(List<TrackDTO> tracks) {this.tracks = tracks;
    }
}
