package com.ticketrush.repository;

import com.ticketrush.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Long> {

}
