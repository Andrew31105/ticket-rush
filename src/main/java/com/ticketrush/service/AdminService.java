package com.ticketrush.service;

import com.ticketrush.entity.Event;
import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.AdminRequest;
import com.ticketrush.repository.IEventRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@Data
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final IEventRepository eventRepository;
    @Override
    public ResponeDTO Update_ticket(AdminRequest adminRequest) {
        long totalTicket = adminRequest.getTotalTicket();
        String eventId = adminRequest.getEventId();
        if (totalTicket <= 0) {
            ResponeDTO responseDTO = new ResponeDTO();
            responseDTO.setMessage("Total ticket must be greater than 0");
            responseDTO.setStatus("Fail");
            return responseDTO;
        }
        else{
            Event event = eventRepository.findById(Long.parseLong(eventId))
                    .orElseThrow(() -> new RuntimeException("Event not found: " + eventId));
            event.setTotalTickets(totalTicket);
            eventRepository.save(event);
            ResponeDTO responseDTO = new ResponeDTO();
            responseDTO.setMessage("Total ticket updated successfully");
            responseDTO.setStatus("Success");
            return responseDTO;
        }

    }

    @Override
    public void delete_ticket(String eventId) {
        Long Id = Long.parseLong(eventId);
        if (eventRepository.existsById(Id)) {
            eventRepository.deleteById(Id);
        }
    }
}
