package cl.fallabela.spotify.service;

import cl.fallabela.spotify.domain.ReponseTokenSpotify;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpHeaders.USER_AGENT;

@Component
public class Spotify {

    @Value("${application.spotify.clientid}")
    private String clientId;

    @Value("${application.spotify.clientsecret}")
    private String clientSecret;

    @Value("${application.spotify.url}")
    private String url;

    @Value("${application.spotify.urlsearch}")
    private String urlSearch;

    public Spotify(String clientId, String clientSecret, String url, String urlSearch) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.url = url;
        this.urlSearch = urlSearch;
    }

    public Spotify() {
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

            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonResponse = EntityUtils.toString(response.getEntity());

            ReponseTokenSpotify reponseTokenSpotify = objectMapper.readValue(jsonResponse, ReponseTokenSpotify.class);

            return reponseTokenSpotify.getAccessToken();

        } catch (UnsupportedEncodingException e) {
            throw new Exception("FALLO LA OPTENCION DE TOKEN: " + e.getMessage());
        } catch (ClientProtocolException e) {
            throw new Exception("FALLO LA OPTENCION DE TOKEN: " + e.getMessage());
        } catch (IOException e) {
            throw new Exception("FALLO LA OPTENCION DE TOKEN: " + e.getMessage());
        }
    }

    public String getAlbums(String muse, String token) throws Exception {

        try {
            HttpClient client = HttpClientBuilder.create().build();

            URIBuilder uriBuilder = new URIBuilder(urlSearch);
            uriBuilder.addParameter("q", muse);
            uriBuilder.addParameter("type", "album");
            uriBuilder.addParameter("limit", "19");

            System.out.println("URL: " + uriBuilder.build());

            HttpGet get = new HttpGet(uriBuilder.build());

            get.setHeader("User-Agent", USER_AGENT);
            get.setHeader("Content-Type", "application/x-www-form-urlencoded");
            get.setHeader("Authorization", "Bearer " + token);

            HttpResponse response = null;

            response = client.execute(get);

            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new Exception("FALLO LA BUSQUEDA DE ALBUMS: " + e.getMessage());
        } catch (IOException e) {
            throw new Exception("FALLO LA BUSQUEDA DE ALBUMS: " + e.getMessage());
        }
    }
}
