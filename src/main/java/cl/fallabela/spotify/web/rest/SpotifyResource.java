package cl.fallabela.spotify.web.rest;

import cl.fallabela.spotify.domain.Albums;
import cl.fallabela.spotify.domain.ResponseAlbum;
import cl.fallabela.spotify.service.Spotify;
import cl.fallabela.spotify.web.rest.errors.InternalServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SpotifyResource {

    private final Logger log = LoggerFactory.getLogger(SpotifyResource.class);

    private final Spotify spotify;

    public SpotifyResource(Spotify spotify) {
        this.spotify = spotify;
    }

    /**
     * GET /spotify/albums/{muse} : get all muse.
     *
     * @param muse
     * @return the ResponseEntity with status 200 (OK) and with body all albums
     */
    @GetMapping("/spotify/albums/{muse}")
    public ResponseEntity<Albums> getUser(@PathVariable String muse) {
        log.debug("REST request to get albums : {}", muse);
        Optional<ResponseAlbum> resp = spotify.readAlbums(muse);

        if (!resp.isPresent()) {
            throw new InternalServerErrorException("Error when trying to take the albums");
        }
        return new ResponseEntity<>(resp.get().getAlbums(), HttpStatus.OK);
    }
}
