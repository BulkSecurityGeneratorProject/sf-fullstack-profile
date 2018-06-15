package cl.fallabela.spotify.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Apispotify.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private Spotify spotify = new Spotify();

    public Spotify getSpotify() {
        return spotify;
    }

    public static class Spotify {
        private String clientid;
        private String clientsecret;
        private String url;
        private String urlsearch;

        public String getClientid() {
            return clientid;
        }

        public void setClientid(String clientid) {
            this.clientid = clientid;
        }

        public String getClientsecret() {
            return clientsecret;
        }

        public void setClientsecret(String clientsecret) {
            this.clientsecret = clientsecret;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlsearch() {
            return urlsearch;
        }

        public void setUrlsearch(String urlsearch) {
            this.urlsearch = urlsearch;
        }
    }

}
