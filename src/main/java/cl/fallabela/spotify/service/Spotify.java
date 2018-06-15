package cl.fallabela.spotify.service;

import cl.fallabela.spotify.domain.*;
import cl.fallabela.spotify.repository.AlbumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.http.HttpHeaders.USER_AGENT;

@Service
public class Spotify {

    private final Logger log = LoggerFactory.getLogger(Spotify.class);

    @Value("${application.spotify.clientid}")
    private String clientId;

    @Value("${application.spotify.clientsecret}")
    private String clientSecret;

    @Value("${application.spotify.url}")
    private String url;

    @Value("${application.spotify.urlsearch}")
    private String urlSearch;

    ObjectMapper objectMapper = new ObjectMapper();
    private final AlbumRepository albumRepository;

    public Spotify(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public String getToken() throws Exception {

        try {
            String idSecret = clientId + ":" + clientSecret;
            String idSecretEncoded = new String(Base64.encodeBase64(idSecret.getBytes()));

            HttpClient client = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);

            post.setHeader("User-Agent", USER_AGENT);
            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            post.setHeader("Authorization", "Basic " + idSecretEncoded);

            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            HttpResponse response = null;
            response = client.execute(post);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ReponseTokenSpotify reponseTokenSpotify = objectMapper.readValue(jsonResponse, ReponseTokenSpotify.class);

            return reponseTokenSpotify.getAccessToken();

        } catch (IOException e) {
            throw new Exception("FALLO LA OPTENCION DE TOKEN: " + e.getMessage());
        }
    }

    public ResponseAlbum getAlbums(String muse, String token) throws Exception {
        try {
            HttpClient client = HttpClientBuilder.create().build();

            URIBuilder uriBuilder = new URIBuilder(urlSearch);
            uriBuilder.addParameter("q", muse);
            uriBuilder.addParameter("type", "album");

            HttpGet get = new HttpGet(uriBuilder.build());

            get.setHeader("User-Agent", USER_AGENT);
            get.setHeader("Content-Type", "application/x-www-form-urlencoded");
            get.setHeader("Authorization", "Bearer " + token);

            HttpResponse response = null;
            response = client.execute(get);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ResponseAlbum responseAlbum = objectMapper.readValue(jsonResponse, ResponseAlbum.class);

            return responseAlbum;
        } catch (IOException e) {
            throw new Exception("FALLO LA BUSQUEDA DE ALBUMS: " + e.getMessage());
        }
    }

    public Optional<ResponseAlbum> readAlbums(String muse) {

        Optional<ResponseAlbum> responseAlbum = null;
        String token = null;
        try {
            token = getToken();
            responseAlbum = Optional.ofNullable(getAlbums(muse, token));

            //save in mongoDB
            if (responseAlbum.isPresent()) {
                for (Item item : responseAlbum.get().getAlbums().getItems()) {
                    Album album = new Album();
                    album.setAlbumType(item.getAlbumType());
                    album.setId(item.getId());
                    album.setName(item.getName());
                    album.setReleaseDate(item.getReleaseDate());

                    album.setArtistList(readArtist(item.getArtists()));

                    Album result = albumRepository.save(album);
                }
            }
            return responseAlbum;
        } catch (Exception e) {
            log.debug(e.getMessage());
            return responseAlbum;
        }
    }

    private String readArtist(List<ArtistSpotify> artists) {
        StringBuilder artistSpotify = new StringBuilder();
        for (ArtistSpotify artist : artists) {
            artistSpotify.append(artist.getId() + ";");
        }
        return artistSpotify.toString();
    }
}
