package com.orecic.recommentationtrackapi.infrastructure.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyAccessTokenResponse;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyArtistResponse;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyTrackResponse;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpotifyClientImpl implements SpotifyClient {

    @Value("${spotify.api.token.uri}")
    private String tokenUri;

    @Value("${spotify.api.client.id}")
    private String clientId;

    @Value("${spotify.api.client.secret}")
    private String clientSecret;

    @Value("${spotify.api.grant.type}")
    private String grantType;

    @Value("${spotify.api.search.uri}")
    private String searchUri;

    @Value("${spotify.api.top.tracks}")
    private String topTracksUri;

    @Override
    public SpotifyAccessTokenResponse getAccessToken() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tokenUri);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", grantType));
        params.add(new BasicNameValuePair("client_id", clientId));
        params.add(new BasicNameValuePair("client_secret", clientSecret));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        SpotifyAccessTokenResponse spotifyToken;

        try ( CloseableHttpResponse response = client.execute(httpPost)) {

            if (response.getCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                spotifyToken = new ObjectMapper().readValue(responseBody, SpotifyAccessTokenResponse.class);
                return spotifyToken;
            }

        } catch (Exception e) {
            throw new RuntimeException("Does not possible integrated with Spotify API");
        }

        return null;
    }

    @Override
    public SpotifyArtistResponse getArtistByGenreMusic(String genre) {
        System.out.println("genre: " + genre);
        try {
            CloseableHttpClient client =  HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(searchUri);
            URI uri = new URIBuilder(httpGet.getUri())
                    .addParameter("q", "genre:" + genre)
                    .addParameter("type", "artist")
                    .addParameter("limit", "1")
                    .build();
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken().getAccessToken());
            httpGet.setUri(uri);
            CloseableHttpResponse response = client.execute(httpGet);

            if (response.getCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                return mapper.readValue(responseBody, SpotifyArtistResponse.class);

            }

            throw new RuntimeException("Does not possible obtain http 200 from Spotify API");

        } catch (Exception e) {
           throw new RuntimeException(e.getCause());
        }

    }

    @Override
    public SpotifyTrackResponse getTopTracksByArtist(String artistId) {
        try {
            CloseableHttpClient client =  HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(topTracksUri.replace(":id", artistId));

            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken().getAccessToken());
            CloseableHttpResponse response = client.execute(httpGet);

            if (response.getCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                return mapper.readValue(responseBody, SpotifyTrackResponse.class);
            }

            throw new RuntimeException("Does not possible obtain http 200 from Spotify API");

        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
