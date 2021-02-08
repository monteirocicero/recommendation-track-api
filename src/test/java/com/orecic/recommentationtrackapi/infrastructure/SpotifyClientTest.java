package com.orecic.recommentationtrackapi.infrastructure;

import com.orecic.recommentationtrackapi.infrastructure.client.SpotifyClient;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyArtistResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpotifyClientTest {

    @Autowired
    SpotifyClient spotifyClient;

    @Test
    public void shouldGetArtistsByGenreMusic() {
        List<SpotifyArtistResponse> spotifyArtistResponses = spotifyClient.getArtistsByGenre("genre:rap");
        Assertions.assertTrue(spotifyArtistResponses.size() > 0);
    }

}
