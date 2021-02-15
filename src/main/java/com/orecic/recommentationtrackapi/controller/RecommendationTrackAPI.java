package com.orecic.recommentationtrackapi.controller;

import com.orecic.recommentationtrackapi.domain.service.RecommendationTrack;
import com.orecic.recommentationtrackapi.domain.service.RecommendationTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationTrackAPI {

    @Autowired
    private RecommendationTrackService recommendationTrackService;

    @RequestMapping(value = "/track")
    public List<RecommendationTrack> getRecommendationTrack(@RequestParam("city") String city) {
        return recommendationTrackService.getRecommendationByCityName(city);
    }

}
