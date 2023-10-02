package com.example.demo.ticket.booking.service.impl;

import static com.example.demo.ticket.booking.util.DateUtil.getCurrentDate;
import static com.example.demo.ticket.booking.util.DateUtil.parseDate;
import static com.example.demo.ticket.booking.util.DateUtil.validateInputDateFormat;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_CITY;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_MOVIE;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOWS;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOW_DATE;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_THEATER;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_CITY_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_CODE;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_MOVIE_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_SHOW_DATE_FORMAT_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.NOT_FOUND_CODE;
import static com.example.demo.ticket.booking.util.ErrorConstants.SHOWS_NOT_FOUND_MSG;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.demo.ticket.booking.exception.BadRequestException;
import com.example.demo.ticket.booking.exception.DataNotFoundException;
import com.example.demo.ticket.booking.mapper.SearchResponseMapper;
import com.example.demo.ticket.booking.model.CreateShowsRequest;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;
import com.example.demo.ticket.booking.model.UpdateShowsRequest;
import com.example.demo.ticket.booking.model.entity.Movie;
import com.example.demo.ticket.booking.model.entity.Screen;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.example.demo.ticket.booking.model.error.Error;
import com.example.demo.ticket.booking.repository.MovieRepository;
import com.example.demo.ticket.booking.repository.ScreenRepository;
import com.example.demo.ticket.booking.repository.ShowsRepository;
import com.example.demo.ticket.booking.service.SearchService;
import com.example.demo.ticket.booking.service.ShowsService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    private final ShowsRepository showsRepository;
    private final SearchResponseMapper searchResponseMapper;

    public SearchServiceImpl(ShowsRepository showsRepository, SearchResponseMapper searchResponseMapper) {
        this.showsRepository = showsRepository;
        this.searchResponseMapper = searchResponseMapper;
    }

    @Override
    public TheaterShowsSearchResponse getShows(Integer cityId, Integer movieId, String showDate) {

        if (isEmpty(showDate)) {
            showDate = getCurrentDate();
        }

        List<Error> errorList = validate(cityId, movieId, showDate);
        if (!errorList.isEmpty()) {
            throw new BadRequestException(errorList);
        }

        List<Shows> shows = showsRepository.findShowsByMovieAndCity(cityId, movieId, showDate);

        if (!isEmpty(shows)) {
            return searchResponseMapper.mapTheaterSearchResponse(shows);
        } else {
            throw new DataNotFoundException(
                List.of(new Error(FIELD_SHOWS, NOT_FOUND_CODE, SHOWS_NOT_FOUND_MSG)));
        }
    }

    private List<Error> validate(Integer cityId, Integer movieId, String showDate) {

        List<Error> errorList = new ArrayList<>();

        if (isEmpty(cityId)) {
            errorList.add(new Error(FIELD_CITY, INVALID_CODE, INVALID_CITY_MSG));
        }
        if (isEmpty(movieId)) {
            errorList.add(new Error(FIELD_MOVIE, INVALID_CODE, INVALID_MOVIE_MSG));
        }
        if (!isEmpty(showDate)) {
            if (validateInputDateFormat(showDate)) {
                errorList.add(new Error(FIELD_SHOW_DATE, INVALID_CODE, INVALID_SHOW_DATE_FORMAT_MSG));
            }
            /*if (isBeforeTodaysDate(showDate)) {
                errorList.add(new Error(FIELD_SHOW_DATE, INVALID_CODE, "Show date before current date"));
            }*/
        }
        return errorList;
    }

}
