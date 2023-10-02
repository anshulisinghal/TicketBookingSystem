package com.example.demo.ticket.booking.model;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateShowsRequest {

    @NotBlank(message = "TheaterId is mandatory")
    private Long theaterId;
    private List<ShowsRequest> shows;

}
