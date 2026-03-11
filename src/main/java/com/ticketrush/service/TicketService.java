package com.ticketrush.service;


import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.BookingRequest;
import com.ticketrush.model.resquest.DeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService{

    private final DefaultRedisScript<Long> deductInventoryScript;
    private final StringRedisTemplate redisTemplate;
    private final KafkaTemplate<String, String> KafkaTemplate;


    public static final String TOPIC = "booking_topic";
    public ResponeDTO bookTicket(BookingRequest bookingRequest) {

        String redisKey = "event_ticket:" + bookingRequest.getEventId();
        // ARGV[1] = "BOOK", ARGV[2] = "1"
        Long result = redisTemplate.execute(deductInventoryScript, Collections.singletonList(redisKey), "BOOK", "1");
        ResponeDTO responseDTO = new ResponeDTO();

        if (result != null && result >= 0) {
            String message = bookingRequest.getUserName() + ":" + bookingRequest.getEventId();
            KafkaTemplate.send(TOPIC, message);
            responseDTO.setMessage("Booking was sent successfully");
            responseDTO.setStatus("Success");
            return responseDTO;
        } else if (result != null && result == -1L) {
            responseDTO.setMessage("Event not found in cache");
            responseDTO.setStatus("Fail");
            return responseDTO;
        } else {
            // result == -2: sold out
            responseDTO.setMessage("Sold out");
            responseDTO.setStatus("Fail");
            return responseDTO;
        }
    }

    @Override
    public ResponeDTO deleteTicket(DeleteRequest deleteRequest) {

        String redisKey = "event_ticket:" + deleteRequest.getEventId();
        // ARGV[1] = "CANCEL", ARGV[2] = "1"
        Long result = redisTemplate.execute(deductInventoryScript, Collections.singletonList(redisKey), "CANCEL", "1");
        ResponeDTO responseDTO = new ResponeDTO();

        if (result != null && result >= 0) {
            String message = deleteRequest.getUserName() + ":" + deleteRequest.getEventId();
            KafkaTemplate.send(TOPIC,message);
            responseDTO.setMessage("Ticket cancelled and inventory restored");
            responseDTO.setStatus("Success");
            KafkaTemplate.send(TOPIC,message);
            return responseDTO;
        } else if (result != null && result == -1L) {
            responseDTO.setMessage("Event not found in cache");
            responseDTO.setStatus("Fail");
            return responseDTO;
        } else {
            responseDTO.setMessage("Cancel failed");
            responseDTO.setStatus("Fail");
            return responseDTO;
        }
    }
}
