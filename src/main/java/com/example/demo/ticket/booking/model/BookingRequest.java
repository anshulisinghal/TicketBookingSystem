package com.example.demo.ticket.booking.model;

import java.util.List;
import com.example.demo.ticket.booking.enumeration.BookingStatus;
import com.example.demo.ticket.booking.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private int userId;
    private int totalSeatsBooked;
    private List<BookedSeat> bookedSeats;
    private PaymentMethod paymentMethod;
    private BookingStatus bookingStatus = BookingStatus.CREATED;
}
