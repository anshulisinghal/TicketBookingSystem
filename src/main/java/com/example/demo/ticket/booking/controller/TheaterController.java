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
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.ticket.booking.model.CreateMovieRequest;
import com.example.demo.ticket.booking.model.CreateTheaterRequest;
import com.example.demo.ticket.booking.model.TheaterCityResponse;

@RestController
public class TheaterController {

    @GetMapping("/api/v1/cities/{cityId}/theaters")
    public ResponseEntity<List<TheaterCityResponse>> getTheatersByCity(@PathVariable int cityId) {
        return ResponseEntity.ok().body(List.of(new TheaterCityResponse()));
    }

    @GetMapping("/api/v1/cities/{cityId}/theaters/{theaterId}")
    public ResponseEntity<TheaterCityResponse> getTheaterById(@PathVariable int cityId, @PathVariable Long theaterId) {
        return ResponseEntity.ok().body(new TheaterCityResponse());
    }

    @PostMapping("/api/v1/cities/{cityId}/theaters")
    public ResponseEntity<CreateTheaterRequest> createTheater(@PathVariable int cityId, @RequestBody CreateTheaterRequest theater) {
        return ResponseEntity.ok().body(theater);
    }

    @PutMapping("/api/v1/theaters/{theaterId}")
    public ResponseEntity<CreateTheaterRequest> updateTheater(@PathVariable int theaterId, @RequestBody CreateTheaterRequest theater) {
        return ResponseEntity.ok().body(theater);
    }

    @DeleteMapping("/api/v1/theaters/{theaterId}")
    public ResponseEntity<Object> deleteTheater(@PathVariable Long theaterId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
