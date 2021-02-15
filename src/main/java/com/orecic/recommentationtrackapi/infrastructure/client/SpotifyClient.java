package com.orecic.recommentationtrackapi.infrastructure.client;

import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyAccessTokenResponse;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyArtistResponse;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyTrackResponse;

public interface SpotifyClient {

   SpotifyAccessTokenResponse getAccessToken();

   SpotifyArtistResponse getArtistByGenreMusic(String genre);

   SpotifyTrackResponse getTopTracksByArtist(String artistId);
}
