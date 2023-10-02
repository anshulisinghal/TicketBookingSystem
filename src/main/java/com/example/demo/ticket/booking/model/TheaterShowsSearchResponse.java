package com.example.demo.ticket.booking.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheaterShowsSearchResponse {

    private String movieName;
    private Long movieId;
    private String city;
    private Long cityId;
    private String chosenDate;
    private String language;
    private String format;
    private List<TheaterSearchResponse> theaters;
}
