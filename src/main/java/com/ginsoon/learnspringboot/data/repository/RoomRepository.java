package com.ginsoon.learnspringboot.data.repository;

import com.ginsoon.learnspringboot.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

}
