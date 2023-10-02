package com.example.demo.ticket.booking.mapper;

import static com.example.demo.ticket.booking.util.DateUtil.convertDateToString;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.example.demo.ticket.booking.exception.SystemException;
import com.example.demo.ticket.booking.model.ShowsSearchResponse;
import com.example.demo.ticket.booking.model.TheaterSearchResponse;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse.TheaterShowsSearchResponseBuilder;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.example.demo.ticket.booking.model.error.Error;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SearchResponseMapper {

    public TheaterShowsSearchResponse mapTheaterSearchResponse(List<Shows> showsList) {
        Shows show = showsList.get(0);

        try {
            TheaterShowsSearchResponseBuilder theaterShowsSearchResponseBuilder = TheaterShowsSearchResponse.builder()
                .movieId(show.getMovie().getId())
                .movieName(show.getMovie().getMovieName())
                .chosenDate(convertDateToString(show.getShowDate()))
                .language(show.getMovie().getLanguage())
                .format(show.getMovie().getFormat())
                .city(show.getScreen().getTheater().getCity().getCityName())
                .cityId(show.getScreen().getTheater().getCity().getId());

            List<TheaterSearchResponse> theaterSearchResponseList = getDistinctTheaterLists(showsList);

            Map<Long, List<Shows>> theaterShowsMap =
                showsList.stream().collect(Collectors.groupingBy(shows -> shows.getTheater().getId(), Collectors.toList()));

            List<TheaterSearchResponse> theaterListWithShows = theaterSearchResponseList.stream().map(
                theaterSearchResponse -> {
                    List<Shows> shows = theaterShowsMap.get(theaterSearchResponse.getTheaterId());
                    theaterSearchResponse.setShows(mapShows(shows));
                    return theaterSearchResponse;
                }
            ).collect(Collectors.toList());

            return theaterShowsSearchResponseBuilder.theaters(theaterListWithShows).build();
        } catch (Exception ex) {
            log.error("Unexpected exception occurred while mapping show list", ex);
            throw new SystemException(List.of(new Error("SHOWS", "INTERNAL_SERVER_ERROR", "Unexpected exception occured while fetching shows")));
        }
    }

    private List<TheaterSearchResponse> getDistinctTheaterLists(List<Shows> showsList) {
        Set<Long> theaterSet = new HashSet<>();
        return showsList.stream().map(shows ->
            {
                if (!theaterSet.contains(shows.getTheater().getId())) {
                    TheaterSearchResponse theaterSearchResponse = new TheaterSearchResponse();
                    theaterSearchResponse.setTheaterId(shows.getTheater().getId());
                    theaterSearchResponse.setTheaterName(shows.getTheater().getTheaterName());
                    theaterSet.add(shows.getTheater().getId());
                    return theaterSearchResponse;
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private List<ShowsSearchResponse> mapShows(List<Shows> shows) {
        return shows.stream().map(show -> {
            ShowsSearchResponse showsSearchResponse = new ShowsSearchResponse();
            showsSearchResponse.setShowId(show.getId());
            showsSearchResponse.setShowDate(convertDateToString(show.getShowDate()));
            showsSearchResponse.setShowStartDateTime(convertDateToString(show.getStartTime()));
            showsSearchResponse.setAvailableSeats(show.getAvailableSeats());
            showsSearchResponse.setTotalSeats(show.getTotalSeats());
            return showsSearchResponse;
        }).collect(Collectors.toList());
    }
}
