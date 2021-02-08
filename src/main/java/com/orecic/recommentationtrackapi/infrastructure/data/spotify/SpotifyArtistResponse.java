package com.orecic.recommentationtrackapi.infrastructure.data.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpotifyArtistResponse {

    @JsonProperty("artists")
    private List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public boolean isValid() {
        return !artists.isEmpty();
    }
}
