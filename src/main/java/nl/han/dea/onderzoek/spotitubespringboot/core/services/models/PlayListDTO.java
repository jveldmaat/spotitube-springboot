package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

import org.springframework.stereotype.Component;

@Component
public class PlayListDTO {
    private int id;
    private String naam;
    private boolean eigenaar;

    private String eigenaarNaam;

    private TrackCollectionDTO tracks = new TrackCollectionDTO();

    public PlayListDTO(){
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return naam;
    }
    public boolean getOwner(){
        return eigenaar;
    }

    public void setName(String name){
        this.naam = name;
    }

    public void setOwner(boolean eigenaar){
        this.eigenaar = eigenaar;
    }

    public void setId(int id){this.id = id;}

    public TrackCollectionDTO getTracks() {
        return tracks;
    }

    public void setTracks(TrackCollectionDTO tracks) {
        this.tracks = tracks;
    }

    public String getEigenaarNaam() {
        return eigenaarNaam;
    }

    public void setEigenaarNaam(String eigenaarNaam) {
        this.eigenaarNaam = eigenaarNaam;
    }
}
