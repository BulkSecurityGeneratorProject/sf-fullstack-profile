package cl.fallabela.spotify.service;

import cl.fallabela.spotify.ApispotifyApp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ApispotifyApp.class)
public class SpotifyTest {

//    @Value("${application.spotify.clientid}")
    private String clientId = "5de5cc1dea9a49248447e9c1fc8c883e";

//    @Value("${application.spotify.clientSecret}")
    private String clientSecret = "f96497e6b670460a8b68279f9d9a1375";

//    @Value("${application.spotify.url}")
    private String url = "https://accounts.spotify.com/api/token";

    //    @Value("${application.urlsearch}")
    private String urlSearch = "https://api.spotify.com/v1/search";

//    @Autowired
    private Spotify spotify;

    @Before
    public void setUp() throws Exception {
        spotify = new Spotify(clientId, clientSecret, url, urlSearch);
    }

    @Test
    public void getToken() throws Exception {

        String token = spotify.getToken();

        System.out.println("token: " +token);

        String albums = spotify.getAlbums("school", token);
        System.out.println("albums : " +albums);
        Assert.assertThat(albums, is(notNullValue()));


//        assertThat(token).isEqualTo("Hello John Doe!");


    }
}
