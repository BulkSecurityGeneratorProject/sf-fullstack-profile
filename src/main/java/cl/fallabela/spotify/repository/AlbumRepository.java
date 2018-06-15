package cl.fallabela.spotify.repository;

import cl.fallabela.spotify.domain.Album;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Album entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {

}
