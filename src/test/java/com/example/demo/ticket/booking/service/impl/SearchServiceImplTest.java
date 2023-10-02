package com.example.demo.ticket.booking.service.impl;

import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_CITY;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_MOVIE;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOWS;
import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOW_DATE;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_CITY_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_CODE;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_MOVIE_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.INVALID_SHOW_DATE_FORMAT_MSG;
import static com.example.demo.ticket.booking.util.ErrorConstants.NOT_FOUND_CODE;
import static com.example.demo.ticket.booking.util.ErrorConstants.SHOWS_NOT_FOUND_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.ticket.booking.exception.BadRequestException;
import com.example.demo.ticket.booking.exception.DataNotFoundException;
import com.example.demo.ticket.booking.mapper.SearchResponseMapper;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.example.demo.ticket.booking.repository.ShowsRepository;
import com.example.demo.ticket.booking.util.HelperUtils;

@ExtendWith(SpringExtension.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;
    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private SearchResponseMapper searchResponseMapper;

    private final String showDate = "2023-09-26 00:00:00";

    @Test()
    @DisplayName("should successfully search shows in theaters for movie and city")
    void should_successfully_return_shows() throws IOException {

        List<Shows> showsList = HelperUtils.getMockShows("showsListEntityResponse.json");
        TheaterShowsSearchResponse theaterShowsSearchResponse =
            HelperUtils.getObjectFromRes(TheaterShowsSearchResponse.class, "theaterShowsResponse.json");

        when(showsRepository.findShowsByMovieAndCity(1, 1, showDate)).thenReturn(showsList);
        when(searchResponseMapper.mapTheaterSearchResponse(showsList)).thenReturn(theaterShowsSearchResponse);

        TheaterShowsSearchResponse response = searchService.getShows(1, 1, showDate);

        assertThat(response).isNotNull();
        assertThat(response.getCity()).isEqualTo("Mumbai");
        assertThat(response.getMovieName()).isEqualTo("End Game");
        assertThat(response.getTheaters()).hasSize(2);
        assertThat(response.getTheaters().get(0).getTheaterName()).isEqualTo("Talkies");
        assertThat(response.getTheaters().get(1).getTheaterName()).isEqualTo("Star Cinema");
        assertThat(response.getTheaters().get(0).getShows()).hasSize(3);
        assertThat(response.getTheaters().get(1).getShows()).hasSize(3);

        verify(showsRepository).findShowsByMovieAndCity(1, 1, showDate);
        verify(searchResponseMapper).mapTheaterSearchResponse(showsList);
    }

    @Test()
    @DisplayName("should throw DataNotFoundException when empty showList")
    void should_throw_not_found_exception_when_empty_showlist() throws IOException {

        when(showsRepository.findShowsByMovieAndCity(1, 1, showDate)).thenReturn(Collections.emptyList());

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
            () -> searchService.getShows(1, 1, showDate));

        assertThat(exception.getErrorList()).hasSize(1);
        assertThat(exception.getErrorList().get(0).getErrorCode()).isEqualTo(NOT_FOUND_CODE);
        assertThat(exception.getErrorList().get(0).getErrorMessage()).isEqualTo(SHOWS_NOT_FOUND_MSG);
        assertThat(exception.getErrorList().get(0).getField()).isEqualTo(FIELD_SHOWS);

        verify(showsRepository).findShowsByMovieAndCity(1, 1, showDate);
        verifyNoInteractions(searchResponseMapper);
    }

    @Test()
    @DisplayName("should throw BadRequestException when invalid city")
    void should_throw_bad_request_exception_when_invalid_city() throws IOException {

        BadRequestException exception = assertThrows(BadRequestException.class,
            () -> searchService.getShows(null, 1, showDate));

        assertThat(exception.getErrorList()).hasSize(1);
        assertThat(exception.getErrorList().get(0).getErrorCode()).isEqualTo(INVALID_CODE);
        assertThat(exception.getErrorList().get(0).getErrorMessage()).isEqualTo(INVALID_CITY_MSG);
        assertThat(exception.getErrorList().get(0).getField()).isEqualTo(FIELD_CITY);

        verifyNoInteractions(searchResponseMapper);
        verifyNoInteractions(showsRepository);
    }


    @Test()
    @DisplayName("should throw BadRequestException when invalid movie")
    void should_throw_bad_request_exception_when_invalid_movie() throws IOException {

        BadRequestException exception = assertThrows(BadRequestException.class,
            () -> searchService.getShows(1, null, showDate));

        assertThat(exception.getErrorList()).hasSize(1);
        assertThat(exception.getErrorList().get(0).getErrorCode()).isEqualTo(INVALID_CODE);
        assertThat(exception.getErrorList().get(0).getErrorMessage()).isEqualTo(INVALID_MOVIE_MSG);
        assertThat(exception.getErrorList().get(0).getField()).isEqualTo(FIELD_MOVIE);

        verifyNoInteractions(searchResponseMapper);
        verifyNoInteractions(showsRepository);
    }

    @Test()
    @DisplayName("should throw BadRequestException when invalid date format")
    void should_throw_bad_request_exception_when_invalid_date_format() throws IOException {

        BadRequestException exception = assertThrows(BadRequestException.class,
            () -> searchService.getShows(1, 1, "2023-09-23"));

        assertThat(exception.getErrorList()).hasSize(1);
        assertThat(exception.getErrorList().get(0).getErrorCode()).isEqualTo(INVALID_CODE);
        assertThat(exception.getErrorList().get(0).getErrorMessage()).isEqualTo(INVALID_SHOW_DATE_FORMAT_MSG);
        assertThat(exception.getErrorList().get(0).getField()).isEqualTo(FIELD_SHOW_DATE);

        verifyNoInteractions(searchResponseMapper);
        verifyNoInteractions(showsRepository);
    }
}