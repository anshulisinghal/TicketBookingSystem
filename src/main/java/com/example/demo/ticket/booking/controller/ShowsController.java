package com.example.demo.ticket.booking.controller;

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
import com.example.demo.ticket.booking.model.CreateShowsRequest;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;
import com.example.demo.ticket.booking.model.UpdateShowsRequest;
import com.example.demo.ticket.booking.service.ShowsService;
import com.example.demo.ticket.booking.service.TheaterService;

@RestController
public class ShowsController {

    private final ShowsService showsService;

    public ShowsController(ShowsService showsService) {
        this.showsService = showsService;
    }

    @PostMapping("/api/v1/shows")
    public ResponseEntity<Object> addShows(@RequestBody CreateShowsRequest createShowsRequest) {
        showsService.createShows(createShowsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/api/v1/shows/{showId}")
    public ResponseEntity<Object> updateShows(@PathVariable Long showId, @RequestBody UpdateShowsRequest updateShowsRequest) {
        showsService.updateShow(showId, updateShowsRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/v1/shows/{showId}")
    public ResponseEntity<Object> updateShows(@PathVariable Long showId) {
        showsService.deleteShow(showId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
