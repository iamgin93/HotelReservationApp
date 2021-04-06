package com.ginsoon.learnspringboot.web;

import com.ginsoon.learnspringboot.business.domain.RoomReservation;
import com.ginsoon.learnspringboot.business.service.ReservationService;
import com.ginsoon.learnspringboot.data.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationWebController {
    private final ReservationService reservationService;

    @Autowired
    public RoomReservationWebController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model){

        List<RoomReservation> roomReservations = this.reservationService.getRoomReservationForDate(DateUtils.createDateFromString(dateString));
        model.addAttribute("roomReservations", roomReservations);
        return "reservations";
    }
}
