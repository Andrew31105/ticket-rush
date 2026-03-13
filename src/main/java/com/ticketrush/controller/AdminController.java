package com.ticketrush.controller;


import com.ticketrush.model.respone.ResponeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    public ResponeDTO create_ticket(RequestBody ){

    }
}
