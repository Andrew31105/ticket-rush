package com.ticketrush.service;


import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.BookingRequest;
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
    public ResponeDTO bookTicket(BookingRequest bookingRequest ){

        String redisKey = "event_ticket:" + bookingRequest.getEventId();
        Long result = redisTemplate.execute(deductInventoryScript, Collections.singletonList(redisKey),"1");
        ResponeDTO responseDTO = new ResponeDTO();

        if(result != null && result >= 0){
            String message = bookingRequest.getUserName() + ":" + bookingRequest.getEventId();
            KafkaTemplate.send(TOPIC,message);
            responseDTO.setMessage("booking was sended");
            responseDTO.setStatus("Sucess");
            return responseDTO;
        }
        else{
            responseDTO.setMessage("sold out");
            responseDTO.setStatus("Fail");
            return responseDTO;
        }
    }

    @Override
    public ResponeDTO deleteTicket(BookingRequest bookingRequest) {

        String redisKey = "event_ticket:" + bookingRequest.getEventId();
        Long result = redisTemplate.execute((deductInventoryScript, Collections.singletonList(redisKey),""))
    }
}
