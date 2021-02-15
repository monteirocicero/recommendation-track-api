package com.orecic.recommentationtrackapi.domain.service;

import java.util.List;

public interface RecommendationTrackService {
    List<RecommendationTrack> getRecommendationByCityName(String city);
}
