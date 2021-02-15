package com.orecic.recommentationtrackapi.domain.service;

import org.junit.jupiter.api.Test;

public class RecommendationTrackserviceTest {

    private RecommendationTrackService recommendationTrackService;

    @Test
    public void shouldGetRecommendationByCity() {
        String city = "campinas";
        var recommendations = recommendationTrackService.getRecommendationByCityName(city);
    }

}
