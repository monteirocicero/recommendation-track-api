package com.orecic.recommentationtrackapi.infrastructure.client;

import com.orecic.recommentationtrackapi.infrastructure.data.spotify.SpotifyArtistResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${spotify.api.name}", url = "${spotify.api.url}")
public interface SpotifyClient {

   @RequestMapping(method = RequestMethod.GET, value = "/search", consumes = "application/json")
   List<SpotifyArtistResponse> getArtistsByGenre(@RequestParam("q") String rap);
}
