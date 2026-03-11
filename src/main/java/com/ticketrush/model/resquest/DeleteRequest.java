package com.ticketrush.model.resquest;

import lombok.Data;


@Data
public class DeleteRequest {
    private String UserName;
    private String eventId;
}
