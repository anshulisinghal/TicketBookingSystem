package com.example.demo.ticket.booking.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterCityResponse {

    private String city ;
    private Long cityId;
    private List<TheaterResponse> theaters;
}
