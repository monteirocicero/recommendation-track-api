package com.orecic.recommentationtrackapi.domain.service;

import com.orecic.recommentationtrackapi.infrastructure.client.SpotifyClient;
import com.orecic.recommentationtrackapi.infrastructure.client.WeatherClient;
import com.orecic.recommentationtrackapi.infrastructure.data.spotify.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RecommendationTrackServiceTest {

    @Mock
    private WeatherService weatherService;

    @Mock
    private SpotifyClient spotifyClient;

    @InjectMocks
    private RecommendationTrackServiceImpl recommendationTrackService;

    @BeforeEach
    public void init() {
        recommendationTrackService = new RecommendationTrackServiceImpl(weatherService, spotifyClient);
    }

    @Test
    public void shouldGetRecommendationByCity() {
        // given
        var city = "campinas";
        var mockWeather = 27.62;
        var  mockArtist = new SpotifyArtistResponse();
        var artist = new Artist();
        var item = new Item();
        item.setId("1");
        artist.setItens(Arrays.asList(item));
        mockArtist.setArtists(artist);
        var mockTrack = new SpotifyTrackResponse();
        var track = new Track();
        track.setName("the number of the beast");
        mockTrack.setTracks(Collections.singletonList(track));

        // when
        Mockito.when(weatherService.getWeatherByCity(Mockito.anyString())).thenReturn(mockWeather);
        Mockito.when(spotifyClient.getArtistByGenreMusic(Mockito.anyString())).thenReturn(mockArtist);
        Mockito.when(spotifyClient.getTopTracksByArtist(Mockito.anyString())).thenReturn(mockTrack);

        // then
        var recommendations = recommendationTrackService.getRecommendationByCityName(city);
        Assertions.assertEquals(track.getName(), recommendations.stream().findFirst().get().getName());
    }

    @Test
    public void shouldGetRecommendationByLatLon() {
        // given
        var lat = "59.9127";
        var lon = "10.7461";
        var mockWeather = 27.62;
        var  mockArtist = new SpotifyArtistResponse();
        var artist = new Artist();
        var item = new Item();
        item.setId("1");
        artist.setItens(Arrays.asList(item));
        mockArtist.setArtists(artist);
        var mockTrack = new SpotifyTrackResponse();
        var track = new Track();
        track.setName("let it be");
        mockTrack.setTracks(Collections.singletonList(track));

        // when
        Mockito.when(weatherService.getWeatherByCity(Mockito.anyString())).thenReturn(mockWeather);
        Mockito.when(spotifyClient.getArtistByGenreMusic(Mockito.anyString())).thenReturn(mockArtist);
        Mockito.when(spotifyClient.getTopTracksByArtist(Mockito.anyString())).thenReturn(mockTrack);

        // then
       var recommendations = recommendationTrackService.getRecommendationByCoordinates(lat, lon);
       Assertions.assertEquals(track.getName(), recommendations.stream().findFirst().get().getName());
    }

}
