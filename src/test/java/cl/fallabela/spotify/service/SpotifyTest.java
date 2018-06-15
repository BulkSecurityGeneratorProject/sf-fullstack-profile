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

    @Autowired
    private Spotify spotify;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getToken() throws Exception {

        String token = spotify.getToken();

        System.out.println("token: " +token);

        String albums = spotify.getAlbums("clasica", token);
        System.out.println("albums : " +albums);
        Assert.assertThat(albums, is(notNullValue()));

//        assertThat(token).isEqualTo("Hello John Doe!");


    }
}
