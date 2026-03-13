package com.ticketrush.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "events")

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
