package com.example.demo.ticket.booking.service.impl;

import static com.example.demo.ticket.booking.util.DateUtil.parseDate;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOWS;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_THEATER;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_CODE;
import static com.example.demo.ticket.booking.util.ErrorConstants.NOT_FOUND_CODE;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.demo.ticket.booking.exception.BadRequestException;
import com.example.demo.ticket.booking.exception.DataNotFoundException;
import com.example.demo.ticket.booking.model.CreateShowsRequest;
import com.example.demo.ticket.booking.model.UpdateShowsRequest;
import com.example.demo.ticket.booking.model.entity.Movie;
import com.example.demo.ticket.booking.model.entity.Screen;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.example.demo.ticket.booking.model.error.Error;
import com.example.demo.ticket.booking.repository.MovieRepository;
import com.example.demo.ticket.booking.repository.ScreenRepository;
import com.example.demo.ticket.booking.repository.ShowsRepository;
import com.example.demo.ticket.booking.service.ShowsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShowsServiceImpl implements ShowsService {

    private final ShowsRepository showsRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;

    public ShowsServiceImpl(ShowsRepository showsRepository, ScreenRepository screenRepository, MovieRepository movieRepository) {
        this.showsRepository = showsRepository;
        this.screenRepository = screenRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public CreateShowsRequest createShows(CreateShowsRequest createShowsRequest) {
        if (isEmpty(createShowsRequest.getShows())) {
            throw new BadRequestException(List.of(new Error(FIELD_SHOWS, INVALID_CODE, "Empty shows in theater list")));
        }

        List<Shows> shows = createShowsRequest.getShows().stream().map(
            showsRequest -> {
                Optional<Screen> screen = screenRepository.findById(showsRequest.getScreenId());
                Optional<Movie> movie = movieRepository.findById(showsRequest.getMovieId());
                if (screen.isPresent() && movie.isPresent()) {
                    if (screen.get().getTheater().getId().equals(createShowsRequest.getTheaterId())) {
                        //TODO - Add validations to check overlapping show times, movie run time etc.
                        Shows showsEntity = new Shows();
                        showsEntity.setShowDate(parseDate(showsRequest.getShowDate()));
                        showsEntity.setStartTime(parseDate(showsRequest.getShowStartTime()));
                        showsEntity.setEndTime(parseDate(showsRequest.getShowEndTime()));
                        showsEntity.setScreen(screen.get());
                        showsEntity.setTheater(screen.get().getTheater());
                        showsEntity.setMovie(movie.get());
                        showsEntity.setTotalSeats(screen.get().getTotalSeats());
                        showsEntity.setAvailableSeats(screen.get().getTotalSeats());
                        return showsEntity;
                    } else {
                        throw new BadRequestException(
                            List.of(new Error(FIELD_THEATER, INVALID_CODE, "Invalid Theater and Screen combination")));
                    }
                } else {
                    throw new BadRequestException(List.of(new Error(FIELD_SHOWS, INVALID_CODE, "Invalid Movie or Screen")));
                }
            }
        ).collect(Collectors.toList());

        showsRepository.saveAll(shows);

        return createShowsRequest;
    }

    @Override
    public UpdateShowsRequest updateShow(Long showId, UpdateShowsRequest updateShowsRequest) {
        if (null == updateShowsRequest || isEmpty(updateShowsRequest.getShows()) || null == showId) {
            throw new BadRequestException(List.of(new Error(FIELD_SHOWS, INVALID_CODE, "Empty show in theater list")));
        }
        //TODO - Validation to check if show has any booked tickets, if yes, update should not be allowed
        //Before update we can check if there are already running show in same theater and screen for new provided show start and end time
        Optional<Shows> shows = showsRepository.findById(showId);
        if (shows.isPresent() && shows.get().getTheater().getId().equals(updateShowsRequest.getTheaterId())) {
            Shows showToUpdate = shows.get();
            Optional<Screen> screen = screenRepository.findById(updateShowsRequest.getShows().getScreenId());
            Optional<Movie> movie = movieRepository.findById(updateShowsRequest.getShows().getMovieId());
            if (screen.isPresent() && movie.isPresent()) {
                if (screen.get().getTheater().getId().equals(updateShowsRequest.getTheaterId())) {
                    showToUpdate.getScreen().setId(updateShowsRequest.getShows().getScreenId());
                    showToUpdate.setShowDate(parseDate(updateShowsRequest.getShows().getShowDate()));
                    showToUpdate.setStartTime(parseDate(updateShowsRequest.getShows().getShowStartTime()));
                    showToUpdate.setEndTime(parseDate(updateShowsRequest.getShows().getShowEndTime()));
                    showToUpdate.getMovie().setId(updateShowsRequest.getShows().getMovieId());
                    showsRepository.save(showToUpdate);
                    return updateShowsRequest;
                } else {
                    throw new BadRequestException(
                        List.of(new Error(FIELD_THEATER, INVALID_CODE, "Invalid Theater and Screen combination")));
                }
            } else {
                throw new BadRequestException(List.of(new Error(FIELD_SHOWS, INVALID_CODE, "Invalid Movie or Screen")));
            }
        } else {
            throw new DataNotFoundException(
                List.of(new Error(FIELD_SHOWS, NOT_FOUND_CODE, String.format("Not able to find show with id %s", showId))));
        }
    }

    @Override
    public void deleteShow(Long showId) {
        if (null == showId) {
            throw new BadRequestException(List.of(new Error(FIELD_SHOWS, INVALID_CODE, "Empty show id to delete")));
        }
        Optional<Shows> shows = showsRepository.findById(showId);
        //TODO - Validation to check if show has any booked tickets, if yes, deletion should not be allowed
        if(shows.isPresent()) {
            showsRepository.deleteById(showId);
        } else {
            throw new DataNotFoundException(
                List.of(new Error(FIELD_SHOWS, NOT_FOUND_CODE, String.format("Not able to find show with id %s", showId))));
        }
    }
}
