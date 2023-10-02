package com.example.demo.ticket.booking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.ticket.booking.model.BookingRequest;

@RestController
public class BookingController {

    @PostMapping("/api/v1/shows/{showId}/book")
    public ResponseEntity bookTicket(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
