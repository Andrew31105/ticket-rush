package com.ticketrush.service;

import com.ticketrush.model.respone.ResponeDTO;
import com.ticketrush.model.resquest.AdminRequest;


public interface IAdminService {
    public ResponeDTO Add_ticket(AdminRequest adminRequest);
}
