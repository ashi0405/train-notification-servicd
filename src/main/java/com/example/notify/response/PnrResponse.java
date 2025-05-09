package com.example.notify.response;

import com.example.notify.entity.PnrData;
import lombok.Data;

@Data
public class PnrResponse {
    private boolean success;
    private PnrData data;

}



