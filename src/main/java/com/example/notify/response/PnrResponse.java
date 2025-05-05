package com.example.notify.response;

import com.example.notify.entity.PnrData;
import lombok.Data;


import lombok.Data;

import java.util.List;

@Data
public class PnrResponse {
    private boolean success;
    private PnrData data;

}



