package controller;


import lombok.RequiredArgsConstructor;
import model.ResponeDTO;
import model.resquest.BookingRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.IBookingRepository;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

        private final IBookingRepository bookingRepository;

        @PostMapping
        public ResponeDTO createBooking(@RequestBody BookingRequest request, @AuthenticationPrincipal UserDeail userDeail){


            return new ResponeDTO();
        }

}










































}
