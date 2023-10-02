package com.example.demo.ticket.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.ticket.booking.model.entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {

}
