package com.orecic.recommentationtrackapi.controller;

import com.orecic.recommentationtrackapi.domain.service.RecommendationTrack;
import com.orecic.recommentationtrackapi.domain.service.RecommendationTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationTrackAPI {

    @Autowired
    private RecommendationTrackService recommendationTrackService;

    @RequestMapping(value = "/track", method = RequestMethod.GET)
    public List<RecommendationTrack> getRecommendationTrackCity(@RequestParam(value = "city", required = false) String city, @RequestParam(value = "lat", required = false) String lat, @RequestParam(value = "lon", required = false) String lon) {
        if (city == null || city.isEmpty()) {
            recommendationTrackService.getRecommendationByCoordinates(lat, lon);
        }
        return recommendationTrackService.getRecommendationByCityName(city);
    }
}
