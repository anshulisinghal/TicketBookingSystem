package com.example.demo.ticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.ticket.booking.model.entity.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

}
