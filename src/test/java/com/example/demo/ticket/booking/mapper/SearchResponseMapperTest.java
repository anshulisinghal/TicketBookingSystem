package com.example.demo.ticket.booking.mapper;

import static com.example.demo.ticket.booking.util.ErrorConstants.FIELD_SHOWS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.ticket.booking.exception.SystemException;
import com.example.demo.ticket.booking.model.TheaterShowsSearchResponse;
import com.example.demo.ticket.booking.model.entity.Shows;
import com.example.demo.ticket.booking.util.HelperUtils;

@ExtendWith(SpringExtension.class)
class SearchResponseMapperTest {

    @InjectMocks
    private SearchResponseMapper searchResponseMapper;

    @Test
    void mapShowsSearchResponse() throws IOException {

        List<Shows> showsList = HelperUtils.getMockShows("showsListEntityResponse.json");
        TheaterShowsSearchResponse response = searchResponseMapper.mapTheaterSearchResponse(showsList);

        assertThat(response).isNotNull();
        assertThat(response.getCity()).isEqualTo("Mumbai");
        assertThat(response.getMovieName()).isEqualTo("End Game");
        assertThat(response.getTheaters()).hasSize(2);
        assertThat(response.getTheaters().get(0).getTheaterName()).isEqualTo("Talkies");
        assertThat(response.getTheaters().get(1).getTheaterName()).isEqualTo("Star Cinema");
        assertThat(response.getTheaters().get(0).getShows()).hasSize(3);
        assertThat(response.getTheaters().get(1).getShows()).hasSize(3);
    }

    @Test
    void throw_exception_when_mandatory_fields_missing_in_showslist() throws IOException {

        List<Shows> showsList = HelperUtils.getMockShows("showsListEntityResponse.json");
        showsList.get(0).setTheater(null);

        SystemException exception = assertThrows(SystemException.class,
            () -> searchResponseMapper.mapTheaterSearchResponse(showsList));
        assertThat(exception.getErrorList()).hasSize(1);
        assertThat(exception.getErrorList().get(0).getErrorCode()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(exception.getErrorList().get(0).getField()).isEqualTo(FIELD_SHOWS);
    }
}