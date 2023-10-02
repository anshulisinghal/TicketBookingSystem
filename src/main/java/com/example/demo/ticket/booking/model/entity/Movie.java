package com.example.demo.ticket.booking.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String movieName;
    private String genre;
    private String description;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "duration_in_minutes")
    private int durationInMinutes;
    @Column(name = "cast_details")
    private String castDetails;
    private String language;
    private String format;
    @Column(name = "Image_url")
    private String thumbNailUrl;

    // Constructors, getters, setters, and other methods...
}