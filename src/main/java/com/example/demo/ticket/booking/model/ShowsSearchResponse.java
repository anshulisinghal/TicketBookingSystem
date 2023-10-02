package com.example.demo.ticket.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowsSearchResponse {

    private Long showId;
    private String showDate;
    private String showStartDateTime;
    private int totalSeats;
    private int availableSeats;
}
