package com.orecic.recommentationtrackapi.domain.service;

import com.orecic.recommentationtrackapi.domain.service.data.GenreMusicEnum;
import com.orecic.recommentationtrackapi.infrastructure.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationTrackServiceImpl implements RecommendationTrackService {

    private WeatherService weatherService;
    private SpotifyClient spotifyClient;

    @Autowired
    public RecommendationTrackServiceImpl(WeatherService weatherService, SpotifyClient spotifyClient) {
        this.weatherService = weatherService;
        this.spotifyClient = spotifyClient;
    }

    @Override
    public List<RecommendationTrack> getRecommendationByCityName(String city) {

        var weatherInfo = weatherService.getWeatherByCity(city);

        String genreMusic = GenreMusicEnum.CLASSICAL.getDescripion();
        if (weatherInfo > 30) {
            genreMusic = GenreMusicEnum.PARTY.getDescripion();
        } else if (weatherInfo >= 15 && weatherInfo <= 30) {
            genreMusic = GenreMusicEnum.POP.getDescripion();
        } else if (weatherInfo >= 10 && weatherInfo < 15) {
            genreMusic = GenreMusicEnum.ROCK.getDescripion();
        }

        var artist = spotifyClient.getArtistByGenreMusic(genreMusic);
        var tracks = spotifyClient.getTopTracksByArtist(artist.getArtists().getItens().stream().findFirst().get().getId());

        return tracks.getTracks().stream().map(track -> new RecommendationTrack(track.getName())).collect(Collectors.toList());
    }
}
