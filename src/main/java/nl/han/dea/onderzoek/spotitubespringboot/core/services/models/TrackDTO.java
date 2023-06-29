package nl.han.dea.onderzoek.spotitubespringboot.core.services.models;

import java.sql.Date;

public class TrackDTO {
    private int id;
    private String performer;
    private String titel;
    private String url;
    private long afspeelduur;
    private boolean offlineAvailable;
    private String album;

    private Date publicatiedatum;
    private String beschrijving;

    public TrackDTO() {
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return titel;
    }

    public void setTitle(String titel) {
        this.titel = titel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getDuration() {
        return afspeelduur;
    }

    public void setDuration(long afspeelduur) {
        this.afspeelduur = afspeelduur;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Date getPublicatiedatum() {
        return publicatiedatum;
    }

    public void setPublicatiedatum(Date publicatiedatum) {
        this.publicatiedatum = publicatiedatum;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setofflineAvailable(boolean offlineavailable) {
        this.offlineAvailable = offlineavailable;
    }

    public int getId() {
        return id;
    }
}
