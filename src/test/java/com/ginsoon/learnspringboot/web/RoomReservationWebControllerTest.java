package com.ginsoon.learnspringboot.web;

import com.ginsoon.learnspringboot.business.domain.RoomReservation;
import com.ginsoon.learnspringboot.business.service.ReservationService;
import com.ginsoon.learnspringboot.data.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationWebController.class)
class RoomReservationWebControllerTest {
    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getReservations() throws Exception {
        String dateString = "2020-01-01";
        List<RoomReservation> roomReservationList = new ArrayList<>();
        RoomReservation roomReservation = new RoomReservation();
        Date date = DateUtils.createDateFromString(dateString);
        roomReservation.setDate(date);
        roomReservation.setFirstName("Peter");
        roomReservation.setLastName("Parker");
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(11);
        roomReservation.setRoomNo("102");
        roomReservation.setRoomName("Amazing");
        roomReservationList.add(roomReservation);

        given(reservationService.getRoomReservationForDate(date)).willReturn(roomReservationList);

        this.mockMvc.perform(get("/reservations?date=2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Parker, Peter")));

    }
}