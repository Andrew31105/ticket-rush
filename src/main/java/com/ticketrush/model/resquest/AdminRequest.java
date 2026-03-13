package com.ticketrush.model.resquest;


import lombok.Data;

@Data
public class AdminRequest {
    private String UserName;
    private String eventId;
    private long totalTicket;
}
