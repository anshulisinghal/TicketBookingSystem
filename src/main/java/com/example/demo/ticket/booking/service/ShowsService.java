package com.example.demo.ticket.booking.service;

import com.example.demo.ticket.booking.model.CreateShowsRequest;
import com.example.demo.ticket.booking.model.UpdateShowsRequest;

public interface ShowsService {

    CreateShowsRequest createShows(CreateShowsRequest createShowsRequest);

    UpdateShowsRequest updateShow(Long showId, UpdateShowsRequest updateShowsRequest);

    void deleteShow(Long showId);

}
