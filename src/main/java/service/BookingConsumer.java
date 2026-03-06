package service;


import entity.Booking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import repository.BookingRepository;
import repository.IBookingRepository;
import repository.IUserRepository;
import repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingConsumer {

    private final IBookingRepository bookingRepository;
    private final IUserRepository userRepository;

    @KafkaListener(topics = "booking_topic" , groupId = "ticket_group")

    public void consume(String message){
        String[] parts = message.split(":");
        String userName = parts[0];
        String eventId = parts[1];

        log.info("Consumed message: " + message);
        Booking booking = new Booking();
        booking.setUserId(userRepository.findByUsername(userName).getId());
        booking.setEventId(Long.parseLong(eventId));
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

    }
}
