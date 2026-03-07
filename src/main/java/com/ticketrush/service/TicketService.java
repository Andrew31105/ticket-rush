package com.ticketrush.service;


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
    public boolean bookTicket(String usserName, String eventId){

        String redisKey = "event_ticket:" + eventId;
        Long result = redisTemplate.execute(deductInventoryScript, Collections.singletonList(redisKey),"1");

        if(result != null && result >= 0){
            String message = usserName + ":" + eventId;
            KafkaTemplate.send(TOPIC,message);
            return true;
        }
        else{
            return false;
        }
    }
}
