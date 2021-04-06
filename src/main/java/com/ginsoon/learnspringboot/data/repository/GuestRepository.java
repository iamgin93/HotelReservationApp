package com.ginsoon.learnspringboot.data.repository;

import com.ginsoon.learnspringboot.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest,Long> {
}
