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
import com.example.demo.ticket.booking.service.SearchService;
import com.example.demo.ticket.booking.service.ShowsService;
import com.example.demo.ticket.booking.service.TheaterService;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/api/v1/cities/{cityId}/movies/{movieId}/shows")
    public ResponseEntity<TheaterShowsSearchResponse> searchTheaterShows(@PathVariable Integer cityId,
        @PathVariable Integer movieId,
        @RequestParam(required = false) String showDate) {
        TheaterShowsSearchResponse theaterShowsSearchResponse = searchService.getShows(cityId, movieId, showDate);
        return ResponseEntity.ok().body(theaterShowsSearchResponse);
    }

}
