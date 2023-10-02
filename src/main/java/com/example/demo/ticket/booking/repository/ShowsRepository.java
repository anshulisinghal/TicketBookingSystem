package com.example.demo.ticket.booking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.ticket.booking.model.entity.Shows;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Long> {

    @Query(value = "SELECT sh.* FROM Theater t, " +
        "Screen sc, Movie m, City c, Shows sh " +
        "WHERE m.id = :movieId AND c.id = :cityId AND DATE(sh.show_date) = DATE(:showDate) AND sh.start_time > :showDate " +
        "AND t.city_id = c.id AND sh.movie_id = m.id AND sh.screen_id = sc.id AND sc.theater_id = t.id ", nativeQuery = true)
    List<Shows> findShowsByMovieAndCity(int cityId, int movieId, String showDate);
}
