package com.example.demo.ticket.booking.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest {

    private String name;
    private String genre;
    private String description;
    private Date releaseDate;
    private int durationInMinutes;
    private String castDetails;
    private String language;
    private String format;
    private String thumbNailUrl;
}
