package com.ticketrush.service;

import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.BookingRequest;

public interface ITicketService {
    public ResponeDTO bookTicket(BookingRequest bookingRequest);
    public ResponeDTO deleteTicket(BookingRequest bookingRequest);
}
