package com.example.demo.ticket.booking.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShowsRequest {

    @NotBlank(message = "TheaterId is mandatory")
    private Long theaterId;
    private ShowsRequest shows;

}
