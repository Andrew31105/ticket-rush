package service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final DefaultRedisScript<Long> deductInventoryScript;
    private final StringRedisTemplate redisTemplate;
    private final KafkaTemplate<String, String> KafkaTemplate;


    Public boolean reavese_ticket(Long )
}
