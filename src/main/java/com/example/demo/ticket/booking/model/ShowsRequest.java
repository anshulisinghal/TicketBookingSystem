package com.example.demo.ticket.booking.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowsRequest {

    String showDate;
    @NotBlank(message = "showStartTime is mandatory")
    String showStartTime;
    @NotBlank(message = "showEndTime is mandatory")
    String showEndTime;
    @NotBlank(message = "movieId is mandatory")
    Long movieId;
    @NotBlank(message = "screenId is mandatory")
    Long screenId;

}
