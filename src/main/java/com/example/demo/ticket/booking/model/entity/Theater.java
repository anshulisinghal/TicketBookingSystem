package com.example.demo.ticket.booking.model.entity;

import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String theaterName;

    @Column(name = "total_halls")
    private String totalNumberOfScreens;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @JsonBackReference
    private City city;

}