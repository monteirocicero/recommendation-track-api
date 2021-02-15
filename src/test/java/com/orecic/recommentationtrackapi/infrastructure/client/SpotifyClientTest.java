package com.orecic.recommentationtrackapi.infrastructure.client;


import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyArtistResponse;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyTrackResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpotifyClientTest {

    @Autowired
    SpotifyClient spotifyClient;

    @Test
    public void shouldGetAccessToken() {
        String accessToken = spotifyClient.getAccessToken().getAccessToken();
        Assertions.assertNotNull(accessToken);
    }

    @Test
    public void shouldGetArtistsByGenreMusic() {
        SpotifyArtistResponse artist = spotifyClient.getArtistByGenreMusic("hip hop");
        Assertions.assertNotNull(artist.getArtists());
    }

    @Test
    public void shouldGetTopTracksByArtist() {
        String artistId = "0vLuOi2k62sHujIfplInlK";
        SpotifyTrackResponse tracks = spotifyClient.getTopTracksByArtist(artistId);
        Assertions.assertNotNull(tracks.getTracks());
    }

}
