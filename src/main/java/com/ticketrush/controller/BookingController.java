package com.ticketrush.controller;


import com.ticketrush.service.TicketService;
import lombok.RequiredArgsConstructor;
import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.BookingRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.ticketrush.repository.IBookingRepository;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

        private final TicketService ticketService;

        @PostMapping("/create")
        public ResponeDTO createBooking(@RequestBody BookingRequest request){
            return ticketService.bookTicket(request);
        }

        @DeleteMapping("/cancle/{bookingId}")
        public ResponeDTO CancleTicket(@RequestBody BookingRequest){
            return null;
        }
}










































}
