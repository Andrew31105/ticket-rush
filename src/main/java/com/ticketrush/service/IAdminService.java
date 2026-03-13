package com.ticketrush.service;

import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.AdminRequest;


public interface IAdminService {
    public ResponeDTO Update_ticket(AdminRequest adminRequest);
    public void delete_ticket(String eventId);
}
