package cl.fallabela.spotify.repository;

import cl.fallabela.spotify.domain.Artist;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Artist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {

}
