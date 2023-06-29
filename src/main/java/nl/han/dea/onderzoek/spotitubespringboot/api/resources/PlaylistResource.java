package nl.han.dea.onderzoek.spotitubespringboot.api.resources;

import nl.han.dea.onderzoek.spotitubespringboot.core.services.PlaylistService;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.crud.TracksDAO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.PlayListDTO;
import nl.han.dea.onderzoek.spotitubespringboot.core.services.models.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistResource {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private TracksDAO tracksDAO;

    @GetMapping(produces = "application/json")
    public ResponseEntity getPlaylists(@RequestParam("token") String token){
        try {
            return ResponseEntity.ok(playlistService.getPlaylists(token));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(path = "/{id}/tracks", produces = "application/json" )
    public ResponseEntity getPlaylist(@PathVariable("id")int id){
        return ResponseEntity.ok(tracksDAO.getPlaylist(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletePlaylist(@RequestParam("token") String token,@PathVariable("id") int id){
        try{
            return ResponseEntity.ok(playlistService.deletePlaylist(token,id));
        } catch(Exception e){
            return ResponseEntity.ok(401);
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity addPlaylist(@RequestParam("token") String token, @RequestBody PlayListDTO playListDTO) {
        try{
            return ResponseEntity.ok(playlistService.createPlaylist(token,playListDTO));
        } catch(Exception e){
            return ResponseEntity.ok(401);
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity editPlaylist(@RequestParam("token") String token, @PathVariable("id") int id, @RequestBody PlayListDTO playlistDTO){
        try{
            return ResponseEntity.ok(playlistService.updatePlaylist(token,id,playlistDTO));
        } catch(Exception e){
            return ResponseEntity.ok(401);
        }
    }

    @PostMapping(path = "/{id}/tracks", produces = "application/json")
    public ResponseEntity addTrackToPlaylist(@RequestParam("token") String token, @PathVariable("id") int id, @RequestBody TrackDTO trackDTO){
        try{
            return ResponseEntity.ok(playlistService.addTrackToPlaylist(token, id, trackDTO));
        } catch(Exception e){
            return ResponseEntity.ok(403);
        }
    }

    @DeleteMapping(path ="/{id1}/tracks/{id2}", produces = "application/json")
    public ResponseEntity deleteTrackFromPlaylist(@RequestParam("token") String token, @PathVariable("id1") int playlistId, @PathVariable("id2") int trackId) {
        try{
            return ResponseEntity.ok(playlistService.removeTrackFromPlaylist(token,playlistId, trackId));
        } catch(Exception e){
            return ResponseEntity.ok(403);
        }
    }

}
