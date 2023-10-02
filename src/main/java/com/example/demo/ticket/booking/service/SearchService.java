package com.example.demo.ticket.booking.service;

import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;

public interface SearchService {

    TheaterShowsSearchResponse getShows(Integer cityId, Integer movieId, String showDate);
}
