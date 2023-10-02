package com.example.demo.ticket.booking.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.ticket.booking.repository.MovieRepository;
import com.example.demo.ticket.booking.repository.ScreenRepository;
import com.example.demo.ticket.booking.repository.ShowsRepository;

@ExtendWith(SpringExtension.class)
class ShowsServiceImplTest {

    @InjectMocks
    private ShowsServiceImpl showsService;

    @Mock
    private ShowsRepository showsRepository;
    @Mock
    private ScreenRepository screenRepository;
    @Mock
    private MovieRepository movieRepository;

}