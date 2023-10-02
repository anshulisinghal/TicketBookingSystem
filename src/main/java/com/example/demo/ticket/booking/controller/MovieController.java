package com.example.demo.ticket.booking.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.ticket.booking.model.CreateMovieRequest;
import com.example.demo.ticket.booking.model.CreateTheaterRequest;
import com.example.demo.ticket.booking.model.MovieCityResponse;

@RestController
public class MovieController {

    @GetMapping("/api/v1/cities/{cityId}/movies")
    public ResponseEntity<List<MovieCityResponse>> getMoviesByCity(@PathVariable int cityId, @RequestParam String language,
        @RequestParam String format, @RequestParam String genre) {
        return ResponseEntity.ok().body(List.of(new MovieCityResponse()));
    }

    @GetMapping("/api/v1/cities/{cityId}/movies/{movieId}")
    public ResponseEntity<MovieCityResponse> getMovieById(@PathVariable int cityId, @PathVariable Long movieId) {
        return ResponseEntity.ok().body(new MovieCityResponse());
    }

    @PostMapping("/api/v1/movies")
    public ResponseEntity<CreateMovieRequest> createMovie(@RequestBody CreateMovieRequest movie) {
        return ResponseEntity.ok().body(movie);
    }

    @PutMapping("/api/v1/movies/{movieId}")
    public ResponseEntity<CreateMovieRequest> updateMovie(@PathVariable int movieId, @RequestBody CreateMovieRequest movie) {
        return ResponseEntity.ok().body(movie);
    }

    @DeleteMapping("/api/v1/movies/{movieId}")
    public ResponseEntity<Object> deleteMovie(@PathVariable Long movieId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
