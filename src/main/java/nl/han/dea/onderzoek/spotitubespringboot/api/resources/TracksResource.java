package nl.han.dea.onderzoek.spotitubespringboot.api.resources;


import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.TracksDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.TrackCollectionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracks")
public class TracksResource {

    @Autowired
    TracksDAO tracksDAO;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAvailableTracks(@RequestParam("forPlaylist") int id, @RequestParam("token") String token){
        TrackCollectionDTO trackCollectionDTO = new TrackCollectionDTO();
        trackCollectionDTO.setTracks(tracksDAO.getAvailableTracks(id));
        return ResponseEntity.ok(trackCollectionDTO);
    }
}
