package cl.fallabela.spotify.service;

import cl.fallabela.spotify.ApispotifyApp;
import cl.fallabela.spotify.domain.ResponseAlbum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = ApispotifyApp.class)
public class SpotifyTest {

    @Autowired
    private Spotify spotify;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getToken() throws Exception {

        String token = spotify.getToken();
        System.out.println("token: " + token);
        Assert.assertThat(token, is(notNullValue()));

//        assertThat(token).isEqualTo("Hello John Doe!");

    }

    @Test
    public void getAlbums() throws Exception {
        String token = spotify.getToken();
        System.out.println("token: " + token);
        ResponseAlbum albums = spotify.getAlbums("clasica", token);
        Assert.assertThat(albums, is(notNullValue()));

    }

}
