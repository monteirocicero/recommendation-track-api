package com.orecic.recommentationtrackapi.domain.service;

public class RecommendationTrack {
    private String name;

    public RecommendationTrack(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
