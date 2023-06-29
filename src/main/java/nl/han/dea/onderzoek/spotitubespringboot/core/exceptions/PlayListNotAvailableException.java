package nl.han.dea.onderzoek.spotitubespringboot.core.exceptions;

public class PlayListNotAvailableException extends RuntimeException{
    public PlayListNotAvailableException(){
        super("Playlist not available");
    }
}
