package com.orecic.recommentationtrackapi.domain.service;

import com.orecic.recommentationtrackapi.domain.service.data.GenreMusicEnum;
import com.orecic.recommentationtrackapi.infrastructure.client.SpotifyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
       var weatherInfoFuture = CompletableFuture.supplyAsync(() -> weatherService.getWeatherByCity(city));
        try {
            return buildRecommendation(weatherInfoFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Weather API failed!");
        }
    }

    private List<RecommendationTrack> buildRecommendation(Double weatherInfo) {
        String genreMusic = GenreMusicEnum.CLASSICAL.getDescripion();
        if (weatherInfo > 30) {
            genreMusic = GenreMusicEnum.PARTY.getDescripion();
        } else if (weatherInfo >= 15 && weatherInfo <= 30) {
            genreMusic = GenreMusicEnum.POP.getDescripion();
        } else if (weatherInfo >= 10 && weatherInfo < 15) {
            genreMusic = GenreMusicEnum.ROCK.getDescripion();
        }

        String finalGenreMusic = genreMusic;
        var tracks =
                CompletableFuture.supplyAsync(() -> spotifyClient.getArtistByGenreMusic(finalGenreMusic))
                        .thenCompose(s ->  CompletableFuture.supplyAsync(() -> spotifyClient.getTopTracksByArtist(s.getArtists().getItens().stream().findFirst().get().getId())));
        try {
            return tracks.get().getTracks().stream().map(track -> new RecommendationTrack(track.getName())).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Spotify API failed!");
        }

    }

    @Override
    public List<RecommendationTrack> getRecommendationByCoordinates(String lat, String lon) {
        var weatherInfo = weatherService.getWeatherByLatLon(lat, lon);
        return buildRecommendation(weatherInfo);
    }

}
