package com.ginsoon.learnspringboot.business.service;

import com.ginsoon.learnspringboot.business.domain.RoomReservation;
import com.ginsoon.learnspringboot.data.entity.Guest;
import com.ginsoon.learnspringboot.data.entity.Reservation;
import com.ginsoon.learnspringboot.data.entity.Room;
import com.ginsoon.learnspringboot.data.repository.GuestRepository;
import com.ginsoon.learnspringboot.data.repository.ReservationRepository;
import com.ginsoon.learnspringboot.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long,RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomNo(room.getRoomNumber());
            roomReservation.setRoomName(room.getRoomName());
            roomReservationMap.put(room.getRoomId(),roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());

        });
        List<RoomReservation> roomReservations = new ArrayList<>();
        for (Long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }

        return roomReservations;
    }
}
