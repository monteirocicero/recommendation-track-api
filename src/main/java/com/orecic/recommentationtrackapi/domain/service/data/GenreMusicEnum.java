package com.orecic.recommentationtrackapi.domain.service.data;

public enum GenreMusicEnum {
    CLASSICAL("classical"), PARTY("party"), POP("pop"), ROCK("rock");
    private final String descripion;

    GenreMusicEnum(String description) {
        this.descripion = description;
    }

    public String getDescripion() {
        return descripion;
    }
}
