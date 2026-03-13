package com.ticketrush.controller;


import com.ticketrush.model.resquest.AdminRequest;
import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/ticket")
    public ResponeDTO create_ticket(@RequestBody AdminRequest adminRequest){
        return adminService.Update_ticket(adminRequest);
    }

    @DeleteMapping("/ticket/{eventId}")
    public void delete_ticket(@PathVariable String eventId){
        adminService.delete_ticket(eventId);
    }



}
