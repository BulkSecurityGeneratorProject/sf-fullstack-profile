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

//    private final Spotify spotify = new Spotify();
//
//    public Spotify getSpotify() {
//        return spotify;
//    }
//
//    public static class Spotify {
//        private String clientId;
//        private String clientSecret;
//        private String url;
//
//        public String getClientId() {
//            return clientId;
//        }
//
//        public void setClientId(String clientId) {
//            this.clientId = clientId;
//        }
//
//        public String getClientSecret() {
//            return clientSecret;
//        }
//
//        public void setClientSecret(String clientSecret) {
//            this.clientSecret = clientSecret;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//    }

}
