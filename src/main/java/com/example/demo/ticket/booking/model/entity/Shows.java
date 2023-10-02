package com.example.demo.ticket.booking.model.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shows")
public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(name = "Show_Date", columnDefinition = "TIMESTAMP")
    private LocalDateTime showDate;

    @Column(name = "Start_Time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "End_Time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", referencedColumnName = "id")
    @JsonBackReference
    private Theater theater;

    @Column(name = "Total_Seats")
    private int totalSeats;

    @Column(name = "Available_Seats")
    private int availableSeats;
}